package com.ping.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.ping.pojo.WxOrder;
import com.ping.util.WXResult;
import com.ping.wxpay.WXPayConstants.SignType;

/**
* <p>Title: IWeiPay</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月25日 下午7:01:01
* @version 1.0
*/
public interface IWXPayService {
	
	/**
	 * 获取微信js网页Access_token
	 * <p>Title: getAccessTokenByCode</p>
	 * <p>Description: Access_token为网页授权 
	 * 1、引导用户进入授权页面同意授权，获取code
	 * 2、通过code换取网页授权access_token（与基础支持中的access_token不同）</p>
	 * @param code 授权码，用于换取access_token
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public Map<String,String> getAccessTokenByCode(String code) throws IOException, ParseException;

	/**
	 * 获取当前页面的wxconfig参数
	 * @Title: getSignature 
	 * @Description: 用于调用了JSSDk的页面初始化
	 * @param url 当前页面地址
	 * @return 通过noncestr（随机字符串）、 有效的jsapi_ticket、 timestamp（时间戳）、 url进行签名并返回相关参数
	 */
	public WXResult getWXconfig(String url) throws ClientProtocolException, IOException, ParseException;
	
	/**
	 * 微信统一下单接口
	 * @Title: unifiedOrder 
	 * @Description: 
	 * @param good_id
	 * @return
	 * @throws Exception 
	 */
	public WXResult unifiedOrder(String good_id,String openid,SignType type) throws Exception;

	/**
	 * 微信统一下单接口：多个订单合并支付
	 * <p>Title: unifiedOrder</p>
	 * <p>Description: </p>
	 * @param unifiedOrderParam 由订单系统生成的微信统一参数
	 * @param openid 用户openid
	 * @param type 签名类型
	 * @return
	 * @throws Exception
	 */
	public WXResult unifiedOrder(Map<String, String> unifiedOrderParam, String ip,
			String openid,SignType type) throws Exception;
	
	/**
	 * 微信支付后回调处理
	 * @Title: PayNotify 
	 * @Description: 
	 * @param xml
	 * @return
	 * @throws Exception 
	 */
	public WXResult payNotify(String xml) throws Exception;
	
	/**
	 * 订单查询
	 * @Title: orderQuery 
	 * @Description: 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public WXResult orderQuery(String orderId) throws Exception;
	
	/**
	 * 测试
	 * @Title: test 
	 * @Description: 
	 * @return
	 */
	public int test();
	
	
}
