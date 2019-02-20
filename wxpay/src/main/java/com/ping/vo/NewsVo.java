package com.ping.vo;

import java.io.Serializable;

import com.ping.pojo.WxNews;

/**
 * 新闻公告Vo
 * <p>Title: NewsVo</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月22日上午10:15:32
 * @version 1.0
 */
public class NewsVo extends WxNews implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
