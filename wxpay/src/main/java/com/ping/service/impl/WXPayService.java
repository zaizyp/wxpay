package com.ping.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ping.util.HttpClient;
import com.ping.util.JsonUtils;
import com.ping.util.WXResult;
import com.ping.vo.AccessToken;
import com.ping.wxpay.WXPayConstants.SignType;
import com.ping.wxpay.WXPayUtil;
import com.ping.wxpay.JSSign;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.ping.mapper.WxUserMapper;
import com.ping.pojo.WxOrder;
import com.ping.pojo.WxUser;
import com.ping.pojo.WxUserExample;
import com.ping.pojo.WxUserExample.Criteria;
import com.ping.service.IWXPayService;

/**
 * <p>
 * Title: WeiPay
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 周运平
 * @date 2018年10月25日 下午6:59:19
 * @version 1.0
 */
@Service
public class WXPayService implements IWXPayService {

	//交易类型
	private static final String JSAPI = "JSAPI";
	
	//微信公众号的appip
	@Value("${WX.APPID}")
	private String WX_APPID;

	//微信公众号的授权秘钥
	@Value("${WX.APPSECRET}")
	private String WX_APPSECRET;

	//微信商户的账号
	@Value("${WX.PARTNER}")
	private String WX_PARTNER;

	//微信商户的密码
	@Value("${WX.PARTNERKEY}")
	private String WX_PARTNERKEY;

	//支付成功的回调地址
	@Value("${WX.NOTIFYURL}")
	private String WX_NOTIFYURL;
	
	//微信JSSDK根据code获取access_token的url
	@Value("${WX.TOKEN.URL}")
	private String WX_TOKEN_URL;
	
	//微信JSSDK根据access_token获取用户信息的url
	@Value("${WX.INFO.URL}")
	private String WX_INFO_URL;
	
	//微信支付统一下单地址
	@Value("${WX.UNIFIED.ORDER.URL}")
	private String WX_UNIFIED_ORDER_URL;

	@Autowired
	private WxUserMapper wxUserMapper;

	private static Logger log = Logger.getLogger(WXPayService.class);

	//获取微信js网页Access_token
	@Override
	public Map<String, String> getAccessTokenByCode(String code)
			throws ClientProtocolException, IOException, ParseException {

		String url = WX_TOKEN_URL+"?appid=" + WX_APPID + "&secret=" + WX_APPSECRET
				+ "&code=" + code + "&grant_type=authorization_code";
		HttpClient client = new HttpClient(url);
		client.setHttps(true);
		client.get();
		// 通过code获取access_token
		Map<String, String> map = new HashMap<>();
		map = JsonUtils.jsonToPojo(client.getContent(), Map.class);
		// 判断是否获取到正确的值
		if (map.get("openid") != null) {
			//根据access_token获取用户信息
			String InfoUrl = WX_INFO_URL+"?access_token=" + map.get("access_token") + 
					"&openid=" + map.get("openid") + "&lang=zh_CN";
			HttpClient client2 = new HttpClient(InfoUrl);
			client2.setHttps(true);
			client2.get();
			Map<String, String> map2 = new HashMap<>();
			map2 = JsonUtils.jsonToPojo(client2.getContent(), Map.class);

			// 查询数据库中是否已经有该openid，如果有则更新该access_token，没有则插入
			WxUserExample example = new WxUserExample();
			Criteria criteria = example.createCriteria();
			criteria.andOpenidEqualTo(map.get("openid"));
			List<WxUser> result = wxUserMapper.selectByExample(example);

			if (result.size() > 0) {
				// 更新用户数据
				WxUser user = result.get(0);
				user.setCity(map2.get("city"));
				user.setUpdated(new Date());
				wxUserMapper.updateByPrimaryKey(user);
			} else {
				// 插入新用户
				WxUser wxUser = new WxUser();
				wxUser.setOpenid(map.get("openid"));
				wxUserMapper.insert(wxUser);
			}

		}
		return map;
	}

	//获取当前页面的wxconfig参数
	public WXResult getWXconfig(String url) throws ClientProtocolException, IOException, ParseException {
		// 如果获取失败则返回null
		if (AccessToken.getAccessToken().getJsapiTicket() == null) {
			log.error("jsapi_ticket获取失败");
			return WXResult.build(500, "jsapi_ticket获取失败");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", WX_APPID);
		map.put("noncestr", WXPayUtil.generateNonceStr());
		map.put("timestamp", "" +WXPayUtil.getCurrentTimestamp());
		// 进行签名算法
		String str = "jsapi_ticket=" + AccessToken.getAccessToken().getJsapiTicket() + "&noncestr="
				+ map.get("noncestr") + "&timestamp=" + map.get("timestamp") + "&url=" + url;
		String signature = DigestUtils.shaHex(str);
		map.put("signature", signature);
		map.put("string1", str);

		return WXResult.ok(map);
	}

	//微信统一下单接口
	@Override
	public WXResult unifiedOrder(String good_id, String openid, SignType type) throws Exception {

		// 1 编写下单参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("appid", WX_APPID);// 公众号id
		param.put("mch_id", WX_PARTNER); // 商户的账号
		param.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串
		param.put("body", good_id); // 商品描述
		String orderId = WXPayUtil.generateNonceStr(); // 商户订单号
		log.info("商户订单号："+orderId);
		param.put("out_trade_no", orderId);
		param.put("total_fee", "1"); // 支付的金额 , 单位是分
		param.put("spbill_create_ip", "127.0.0.1"); // ip地址
		param.put("notify_url", WX_NOTIFYURL); // 通知地址
		param.put("trade_type", "JSAPI"); // 交易类型
		param.put("openid", openid); // 用户openid
		log.info("参与签名的参数："+param.toString());

		// 2 把参数转换为xml格式并生成签名
		String xml = WXPayUtil.generateSignedXml(param, WX_PARTNERKEY);
		log.info("签名完成的xml数据："+xml);
		// 3 调用微信统一下单接口
		String url = WX_UNIFIED_ORDER_URL;
		HttpClient client = new HttpClient(url);
		client.setHttps(true);
		client.setXmlParam(xml);
		client.post();

		// 4 获取微信支付后台返回的数据
		String content = client.getContent();
		log.info("微信统一下单返回的数据："+content);
		Map<String, String> resultMap = WXPayUtil.xmlToMap(content);// 把返回的数据转换成map集合
		log.info("转换成map的数据"+resultMap.toString());
		// 5 根据微信返回的数据判断是否下单成功
		if (resultMap.get("return_code").equals("SUCCESS")) {

			if (resultMap.get("result_code").equals("FAIL")) {
				return WXResult.build(500,
						"err_code:" + resultMap.get("err_code") + "err_code_des:" + resultMap.get("err_code_des"));
			}

			// 6 生成前端JS-SDk调用支付接口所需参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("appId", WX_APPID);
			map.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
			map.put("nonceStr", WXPayUtil.generateNonceStr());
			map.put("package", "prepay_id=" + resultMap.get("prepay_id"));
			map.put("signType", type.toString());
			log.info("参与公众号支付接口签名的数据："+map.toString());
			String generateSignature = WXPayUtil.generateSignature(map, WX_PARTNERKEY, type);
			log.info("支付签名："+generateSignature);
			map.put("paysign", generateSignature);
			//因为package在java中是关键字，所以用在生成签名后再用Pagkage存储一次其值。
			map.put("Pagkage", "prepay_id=" + resultMap.get("prepay_id"));
			map.put("orderId", orderId);
			return WXResult.ok(map);
		} else {
			return WXResult.build(500, resultMap.get("return_msg"));
		}
	}

	//多个订单合并支付
	@Override
	public WXResult unifiedOrder(Map<String, String> unifiedOrderParam, String ip, String openid, SignType type) throws Exception {
		// 1 编写下单参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("appid", WX_APPID);// 公众号id
		param.put("mch_id", WX_PARTNER); // 商户的账号
		param.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串
		String body = unifiedOrderParam.get("body");// 商品描述
		param.put("body", body); 
		String outTradeNo = unifiedOrderParam.get("out_trade_no");//获取微信支付的商户订单id
		log.info("商户订单号："+outTradeNo);
		param.put("out_trade_no", outTradeNo);
		String totalFee = unifiedOrderParam.get("total_fee");//获取支付金额
		param.put("total_fee", totalFee.toString()); // 支付的金额 , 单位是分
		param.put("spbill_create_ip", ip); // ip地址
		param.put("notify_url", WX_NOTIFYURL); // 通知地址
		param.put("trade_type", JSAPI); // 交易类型
		param.put("openid", openid); // 用户openid
		log.info("参与签名的参数："+param.toString());

		// 2 把参数转换为xml格式并生成签名
		String xml = WXPayUtil.generateSignedXml(param, WX_PARTNERKEY);
		log.info("签名完成的xml数据："+xml);
		// 3 调用微信统一下单接口
		HttpClient client = new HttpClient(WX_UNIFIED_ORDER_URL);
		client.setHttps(true);
		client.setXmlParam(xml);
		client.post();

		// 4 获取微信支付后台返回的数据
		String content = client.getContent();
		log.info("微信统一下单返回的数据："+content);
		Map<String, String> resultMap = WXPayUtil.xmlToMap(content);// 把返回的数据转换成map集合
		log.info("转换成map的数据"+resultMap.toString());
		// 5 根据微信返回的数据判断是否下单成功
		if (resultMap.get("return_code").equals("SUCCESS")) {

			if (resultMap.get("result_code").equals("FAIL")) {
				return WXResult.build(500,
						"err_code:" + resultMap.get("err_code") + "err_code_des:" + resultMap.get("err_code_des"));
			}

			// 6 生成前端JS-SDk调用支付接口所需参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("appId", WX_APPID);
			map.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
			map.put("nonceStr", WXPayUtil.generateNonceStr());
			map.put("package", "prepay_id=" + resultMap.get("prepay_id"));
			map.put("signType", type.toString());
			log.info("参与公众号支付接口签名的数据："+map.toString());
			String generateSignature = WXPayUtil.generateSignature(map, WX_PARTNERKEY, type);
			map.put("paySign", generateSignature);
			//因为package在java中是关键字，所以应在生成签名后再用Pagkage存储一次其值。
			map.put("Pagkage", "prepay_id=" + resultMap.get("prepay_id"));
			map.put("orderId", outTradeNo);
			log.info("完成签名后的数据："+map.toString());
			return WXResult.ok(map);
		} else {
			return WXResult.build(500, resultMap.get("return_msg"));
		}
	}
	
	//微信支付后回调处理
	@Override
	public WXResult payNotify(String xml) throws Exception {
		// 解析xml数据并判断支付是否成功
		Map<String, String> map = WXPayUtil.xmlToMap(xml);
		log.info("微信支付通知"+map.toString());
		if (map.get("return_code").equals("SUCCESS")) {
			log.info("支付成功,商户订单号为："+map.get("out_trade_no"));
		}

		Map<String, String> result = new HashMap<String, String>();
		result.put("return_code", "SUCCESS");
		result.put("return_msg", "OK");

		return WXResult.ok(WXPayUtil.mapToXml(result));
	}

	//订单查询
	@Override
	public WXResult orderQuery(String orderId) throws Exception {
		//1 向微信发送查询订单的请求
		String url = "https://api.mch.weixin.qq.com/pay/orderquery";
		HttpClient client = new HttpClient(url);
		client.setHttps(true);
		//2 拼接参数
		Map<String,String> map = new HashMap<String,String>();
		map.put("appid", WX_APPID);
		map.put("mch_id", WX_PARTNER);
		//map.put("transaction_id", orderId);//微信订单号
		map.put("out_trade_no", orderId);//商户订单号  二选一
		map.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
		log.info("查询订单的参数"+map.toString());
		String xml = WXPayUtil.generateSignedXml(map, WX_PARTNERKEY);//签名并转为xml数据
		log.info("查询订单发送的xml："+xml);
		client.setXmlParam(xml);
		client.post();
		String content = client.getContent();
		log.info("查询订单获取的xml："+content);
		//3 分析微信返回的xml数据
			//return_msg  当return_code为FAIL时返回信息为错误原因 ，例如 
			//签名失败 
			//参数格式校验错误
		Map<String,String> result = WXPayUtil.xmlToMap(content);
		if(result.get("return_code").equals("SUCCESS")){
			return WXResult.ok(result);
		}
		return WXResult.build(500, result.get("return_msg"));
	}
	//测试
	@Override
	public int test() {
//		WxUser user = new WxUser();
//		user.setOpenid("125445dsefr");
//		return wxUserMapper.insert(user);
		return 0;
	}

}
