package com.ping.vo;

import java.io.Serializable;

import com.ping.pojo.WxCollection;

/**
 * 收藏商品Vo
 * <p>Title: CollectionVo</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月21日下午10:32:16
 * @version 1.0
 */
public class CollectionVo extends WxCollection implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private String title;//商品标题
	private String image;//商品主图
	private Long lowPrice;//商品最低价格

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Long lowPrice) {
		this.lowPrice = lowPrice;
	}
	
}
