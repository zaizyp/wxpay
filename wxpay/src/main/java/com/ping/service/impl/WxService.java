package com.ping.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ping.pojo.WxUser;
import com.ping.pojo.WxUserExample;
import com.ping.pojo.WxUserExample.Criteria;
import com.ping.service.IWxService;
import com.ping.util.HttpClient;
import com.ping.util.JsonUtils;
import com.ping.util.WXResult;
import com.ping.vo.AccessToken;
import com.ping.wxpay.WXPayUtil;

/**
 * 微信公众号相关Service
 * <p>Title: WxService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月28日下午2:55:25
 * @version 1.0
 */
@Service
public class WxService implements IWxService {
	
	//微信公众号的appip
	@Value("${WX.APPID}")
	private String WX_APPID;

	//微信公众号的授权秘钥
	@Value("${WX.APPSECRET}")
	private String WX_APPSECRET;
	
	//微信JSSDK根据code获取access_token的url
	@Value("${WX.TOKEN.URL}")
	private String WX_TOKEN_URL;
	
	//微信JSSDK根据code获取access_token的url
	@Value("${WX.REFRESH.TOKEN.URL}")
	private String WX_REFRESH_TOKEN_URL;
	
	//微信JSSDK根据access_token获取用户信息的url
	@Value("${WX.INFO.URL}")
	private String WX_INFO_URL;

	//日志
	private static Logger log = Logger.getLogger(WxService.class);
	
	//获取微信js网页Access_token，有效期为2个小时
	@Override
	public Map<String, String> getAccessTokenByCode(String code, String scope) throws Exception{
		Map<String, String> map = new HashMap<>();
		String url = WX_TOKEN_URL+"?appid=" + WX_APPID + "&secret=" + WX_APPSECRET
				+ "&code=" + code + "&grant_type=authorization_code";
		HttpClient client = new HttpClient(url);
		client.setHttps(true);
		client.get();
		map = JsonUtils.jsonToPojo(client.getContent(), Map.class);
		// 判断是否获取到正确的值
		if (map.get("openid") != null) {
			log.info(map.get("openid")+":"+map.get("access_token"));
			return map;
		}
		return null;
	}
	
	//根据上一步获取到的refresh_token刷新access_token
	public Map<String, String> refreshAccessToken(String refresh_token) throws Exception{
		Map<String, String> map = new HashMap<>();
		String url = WX_REFRESH_TOKEN_URL+"?appid=" + WX_APPID + 
				"&grant_type=refresh_token&refresh_token=" +refresh_token;
		HttpClient client = new HttpClient(url);
		client.setHttps(true);
		client.get();
		map = JsonUtils.jsonToPojo(client.getContent(), Map.class);
		// 判断是否获取到正确的值
		if (map.get("openid") != null) {
			return map;
		}
		return null;
	}
	
	//通过access_token获取用户信息
	public WxUser getUserInfoByAccessToken(String accessTkoen,String openid) throws Exception{
		Map<String, ?> map = new HashMap<>();
		String InfoUrl = WX_INFO_URL+"?access_token=" + accessTkoen + 
				"&openid=" + openid + "&lang=zh_CN";
		HttpClient client = new HttpClient(InfoUrl);
		client.setHttps(true);
		client.get();
		log.info("xml用户信息："+client.getContent());
		map = JsonUtils.jsonToPojo(client.getContent(), Map.class);
		log.info("map用户信息："+map);
		if(map.get("openid")!=null&&map.get("openid")!=""){
			WxUser user = new WxUser();
			user.setCity(map.get("city").toString());
			user.setCountry(map.get("country").toString());
			user.setHeadimgurl(map.get("headimgurl").toString());
			user.setNickname(map.get("nickname").toString());
			user.setPrivilege(JsonUtils.objectToJson(map.get("privilege")));
			user.setProvince(map.get("province").toString());
			user.setSex(((Integer)map.get("sex")).byteValue());
			user.setOpenid(map.get("openid").toString());
			return user;
		}
		return null;
	}
	
	//获取当前页面的wxconfig参数
	public WXResult getWXconfig(String url) throws Exception {
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

}
