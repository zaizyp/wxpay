package com.ping.service;

import java.util.List;
import java.util.Map;

import com.ping.util.WXResult;
import com.ping.vo.OrderItemVo;
import com.ping.vo.OrderVo;

/**
 * 前台订单处理
 * <p>Title: IOrderService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月19日上午9:39:31
 * @version 1.0
 */
public interface IOrderService {
	
	/**
	 * 提交订单
	 * <p>Title: addOrder</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @param message 留言信息 map<Long,String> 键为店铺id，值为留言信息
	 * @param currentOrderId 存入Redis中订单信息的标识
	 * @return Map<String, String>微信统一下单参数
	 * 	有三个键:total_fee,out_trade_no,body
	 */
	public Map<String, String> addOrder(Long userId,Map<Long, String> message, String deliveryAddress,
			String currentOrderId) throws Exception;
	
	public WXResult upDateOrderPay() throws Exception;
	
	/**
	 * 查询用户订单
	 * <p>Title: selectOrder</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @return
	 */
	public List<OrderVo> selectOrder(Long userId);
	
	/**
	 * 查询用户订单数量
	 * <p>Title: selectOrderNum</p>
	 * <p>Description: </p>
	 * @param userId	用户ID
	 * @return
	 */
	public Map<String, Integer> selectOrderNum(Long userId);
	
}
