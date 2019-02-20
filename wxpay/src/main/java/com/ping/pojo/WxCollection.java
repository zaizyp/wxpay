package com.ping.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxCollection implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long userId;

    private Long itemId;

    private Date updated;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}