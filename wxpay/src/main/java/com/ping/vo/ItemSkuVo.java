package com.ping.vo;

import java.io.Serializable;
import java.util.List;

import com.ping.pojo.WxBrand;
import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemDesc;
import com.ping.pojo.WxItemSku;
import com.ping.pojo.WxItemSpec;
import com.ping.pojo.WxShop;

public class ItemSkuVo extends WxItemSku implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private List<ItemSpecValue> itemSpecValueList;//商品规格值列表
	private WxShop shop;//店铺
	private WxBrand brand;//品牌
	private WxItemDesc itemDesc;//商品详情
	private WxItemSpec itemSpec;//商品规格
	private WxItem item;//商品信息

	public WxItemDesc getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(WxItemDesc itemDesc) {
		this.itemDesc = itemDesc;
	}
	public WxItemSpec getItemSpec() {
		return itemSpec;
	}
	public void setItemSpec(WxItemSpec itemSpec) {
		this.itemSpec = itemSpec;
	}
	public WxItem getItem() {
		return item;
	}
	public void setItem(WxItem item) {
		this.item = item;
	}
	public WxShop getShop() {
		return shop;
	}
	public void setShop(WxShop shop) {
		this.shop = shop;
	}
	public WxBrand getBrand() {
		return brand;
	}
	public void setBrand(WxBrand brand) {
		this.brand = brand;
	}
	public List<ItemSpecValue> getItemSpecValueList() {
		return itemSpecValueList;
	}
	public void setItemSpecValueList(List<ItemSpecValue> itemSpecValueList) {
		this.itemSpecValueList = itemSpecValueList;
	}

	@Override
	public String toString() {
		return "ItemSku [itemSpecValueList=" + itemSpecValueList + ", toString()=" + super.toString() + "]";
	}

}