package com.ping.vo;

import java.io.Serializable;
import java.util.List;

import com.ping.pojo.WxOrder;
import com.ping.pojo.WxOrderItem;

/**
 * 订单数据显示VO
 * <p>Title: OrderVo</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年12月17日下午12:31:12
 * @version 1.0
 */
public class OrderVo implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	//订单基本信息
	private WxOrder order;
	//订单详细商品信息
	private List<WxOrderItem> orderItemList;
	//微信商户订单
	private String outTradeNo;
	
	public WxOrder getOrder() {
		return order;
	}
	public void setOrder(WxOrder order) {
		this.order = order;
	}
	public List<WxOrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<WxOrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
}
