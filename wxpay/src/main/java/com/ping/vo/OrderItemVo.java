package com.ping.vo;

import java.io.Serializable;

import com.ping.pojo.WxItem;
import com.ping.pojo.WxShop;

/**
 * 提交订单的商品详情
 * <p>Title: OrderItemVo</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月23日下午8:52:00
 * @version 1.0
 */
public class OrderItemVo implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private WxItem item;//商品基本信息
	private ItemSku itemSku;//商品sku
	private WxShop shop;//店铺
	private Long postFee;//邮费
	
	public WxItem getItem() {
		return item;
	}
	public void setItem(WxItem item) {
		this.item = item;
	}
	public Long getPostFee() {
		return postFee;
	}
	public void setPostFee(Long postFee) {
		this.postFee = postFee;
	}
	public ItemSku getItemSku() {
		return itemSku;
	}
	public void setItemSku(ItemSku itemSku) {
		this.itemSku = itemSku;
	}
	public WxShop getShop() {
		return shop;
	}
	public void setShop(WxShop shop) {
		this.shop = shop;
	}

}
