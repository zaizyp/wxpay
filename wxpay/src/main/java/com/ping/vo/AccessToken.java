package com.ping.vo;

import java.io.Serializable;

/**
* <p>Title: AccessToken</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月28日 下午10:32:35
* @version 1.0
*/
public class AccessToken implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private int expiresIn;
	private String token = null;
	private String jsapiTicket = null;
	
	private static AccessToken accessToken = null;
	
	private AccessToken(){
		
	}
	
	public static AccessToken getAccessToken(){
		if(accessToken==null){
			accessToken = new AccessToken();
		}
		return accessToken;
	}
	
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

}
