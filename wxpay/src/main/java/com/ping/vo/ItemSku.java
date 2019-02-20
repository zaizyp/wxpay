package com.ping.vo;

import java.io.Serializable;
import java.util.List;

public class ItemSku implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
    private Long id;//skuId
    private Long itemId;//商品id
    private Long itemSpecId;//商品规格id
    private Long price;//商品价格
    private Integer num;//商品库存
    private String image;//商品图片
	private List<ItemSpecValue> itemSpecValue;//商品规格值列表
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getItemSpecId() {
		return itemSpecId;
	}
	public void setItemSpecId(Long itemSpecId) {
		this.itemSpecId = itemSpecId;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<ItemSpecValue> getItemSpecValue() {
		return itemSpecValue;
	}
	public void setItemSpecValue(List<ItemSpecValue> itemSpecValue) {
		this.itemSpecValue = itemSpecValue;
	}
	
}