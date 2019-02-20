package com.ping.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxItemSku implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long itemId;

    private Long itemSpecId;

    private String itemSpecValue;

    private Long price;

    private Integer num;

    private String image;

    private Date created;

    private Date updated;

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

    public String getItemSpecValue() {
        return itemSpecValue;
    }

    public void setItemSpecValue(String itemSpecValue) {
        this.itemSpecValue = itemSpecValue == null ? null : itemSpecValue.trim();
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
        this.image = image == null ? null : image.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}