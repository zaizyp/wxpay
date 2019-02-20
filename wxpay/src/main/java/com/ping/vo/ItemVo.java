package com.ping.vo;

import java.io.Serializable;
import java.util.List;

import com.ping.pojo.WxBrand;
import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemDesc;
import com.ping.pojo.WxItemSpec;
import com.ping.pojo.WxShop;

/**
 * 前台商品详情VO
 * <p>Title: ItemVo</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月15日下午2:25:45
 * @version 1.0
 */
public class ItemVo extends WxItem implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private WxShop shop;//店铺
	private WxBrand brand;//品牌
	private WxItemDesc itemDesc;//商品详情
	private WxItemSpec itemSpec;//商品规格
	private List<ItemSku> itemSkuList;//商品sku
	
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
	public List<ItemSku> getItemSkuList() {
		return itemSkuList;
	}
	public void setItemSkuList(List<ItemSku> itemSkuList) {
		this.itemSkuList = itemSkuList;
	}
	@Override
	public String toString() {
		return "ItemVo [shop=" + shop + ", brand=" + brand + ", itemDesc=" + itemDesc + ", itemSpec=" + itemSpec
				+ ", itemSkuList=" + itemSkuList + "]";
	}
	
	
}
