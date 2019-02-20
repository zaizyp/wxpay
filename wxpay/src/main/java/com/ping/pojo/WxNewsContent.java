package com.ping.pojo;

import java.io.Serializable;

public class WxNewsContent implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
    private Long newsId;

    private String content;

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}