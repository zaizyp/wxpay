package com.ping.vo;

import java.io.Serializable;

/**
* <p>Title: WXMessage</p>
* <p>Description:微信发送模版消息</p>
* @author  周运平
* @date    2018年10月31日 下午7:21:12
* @version 1.0
*/
public class WXMessage implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户openid 必须
	 */
	private String toUser;
	/**
	 * 模版id 必须
	 */
	private String templateId;
	/**
	 * 模板跳转链接 否
	 */
	private String url;
	/** 
	 * miniprogram	否	跳小程序所需数据，不需跳小程序可不用传该数据
	 * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
	 */
	private String appId;
	/**
	 * miniprogram	否	跳小程序所需数据，不需跳小程序可不用传该数据
	 * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
	 */
	private String pagePath;
	/**
	 * 模板数据 必须
	 */
	private String[] message;
	/**
	 * color	否	模板内容字体颜色，不填默认为黑色  必须与模版消息长度一致
	 */
	
	private String[] color;
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	
	
}
