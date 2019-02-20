package com.ping.vo;

import java.io.Serializable;
import java.util.List;

import com.ping.pojo.WxItemCat;

/**
* <p>Title: ItemCat</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年11月4日 下午9:17:26
* @version 1.0
*/
public class ItemCat extends WxItemCat implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private List<WxItemCat> children;
	private String state;
	
	
	public List<WxItemCat> getChildren() {
		return children;
	}

	public void setChildren(List<WxItemCat> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
