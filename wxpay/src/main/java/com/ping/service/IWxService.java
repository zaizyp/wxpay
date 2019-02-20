package com.ping.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import com.ping.pojo.WxUser;
import com.ping.util.WXResult;

/**
 * 微信公众号相关Service
 * <p>Title: IWxService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月28日下午2:52:39
 * @version 1.0
 */
public interface IWxService {

	/**
	 * 获取微信js网页Access_token
	 * <p>Title: getAccessTokenByCode</p>
	 * <p>Description: Access_token为网页授权 
	 * 1、引导用户进入授权页面同意授权，获取code
	 * 2、通过code换取网页授权access_token（与基础支持中的access_token不同）</p>
	 * @param code 授权码，用于换取access_token
	 * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
	 * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
	 * @return Map<String, String>
	 *	参数	描述
	 *	access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 *	expires_in	access_token接口调用凭证超时时间，单位（秒）
	 *	refresh_token	用户刷新access_token
	 *	openid	用户唯一标识
	 *	scope	用户授权的作用域，使用逗号（,）分隔
	 */
	public Map<String,String> getAccessTokenByCode(String code,String scope) throws Exception;

	/**
	 * 
	 * <p>Title: refreshAccessToken</p>
	 * <p>Description: </p>
	 * @param refresh_token 刷新access_token的值，在上一步获取
	 * @return Map<String, String>
	 *	参数	描述
	 *	access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 *	expires_in	access_token接口调用凭证超时时间，单位（秒）
	 *	refresh_token	用户刷新access_token
	 *	openid	用户唯一标识
	 *	scope	用户授权的作用域，使用逗号（,）分隔
	 */
	public Map<String, String> refreshAccessToken(String refresh_token) throws Exception;

	/**
	 * 通过access_token获取用户信息
	 * <p>Title: getUserInfoByAccessToken</p>
	 * <p>Description: </p>
	 * @param accessTkoen
	 * @param openid
	 * @return User 用户信息
	 */
	public WxUser getUserInfoByAccessToken(String accessTkoen,String openid) throws Exception;

	/**
	 * 获取当前页面的wxconfig参数
	 * <p>Title: getWXconfig</p>
	 * <p>Description: </p>
	 * @param url 页面url
	 * @return
	 * @throws Exception
	 */
	public WXResult getWXconfig(String url) throws Exception;
}
