package com.ping.vo;

import java.io.Serializable;

import com.ping.pojo.WxItem;
import com.ping.pojo.WxShop;
import com.ping.pojo.WxShopingCart;

/**
 * 购物车相关数据
 * <p>Title: CartVo</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月19日下午4:42:18
 * @version 1.0
 */
public class CartVo extends WxShopingCart implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private WxItem item;
	private ItemSku itemSku;
	private WxShop shop;
	
	public WxItem getItem() {
		return item;
	}
	public void setItem(WxItem item) {
		this.item = item;
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
