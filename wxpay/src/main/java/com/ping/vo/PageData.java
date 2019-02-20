package com.ping.vo;

import java.io.Serializable;
import java.util.List;

/**
 * easyUI使用的分页数据格式
 * <p>Title: PageData</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月14日下午5:32:22
 * @version 1.0
 * @param <T>
 */
public class PageData<T> implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private Long total;
	private List<T> rows;
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
