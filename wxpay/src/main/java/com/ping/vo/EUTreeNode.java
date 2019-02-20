package com.ping.vo;

import java.io.Serializable;

/**
* <p>Title: EUTreeNode</p>
* <p>Description:EasyUI 树形节点</p>
* @author  周运平
* @date    2018年11月4日 下午4:11:04
* @version 1.0
*/
public class EUTreeNode implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String text;
	private String state;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
