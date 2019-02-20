package com.ping.vo;

import java.io.Serializable;

import com.ping.pojo.WxAd;

/**
 * 广告商品VO
 * <p>Title: AdItemVo</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月22日下午8:19:30
 * @version 1.0
 */
public class AdItemVo extends WxAd implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private String itemTitle;
	private String image;
	private Long lowPrice;
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
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
