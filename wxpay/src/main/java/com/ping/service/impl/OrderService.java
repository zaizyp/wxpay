package com.ping.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.mapper.WxItemMapper;
import com.ping.mapper.WxItemSkuMapper;
import com.ping.mapper.WxOrderItemMapper;
import com.ping.mapper.WxOrderMapper;
import com.ping.mapper.WxOrderNoMapper;
import com.ping.mapper.WxShopMapper;
import com.ping.mapper.WxUserMapper;
import com.ping.pojo.WxOrder;
import com.ping.pojo.WxOrderExample;
import com.ping.pojo.WxOrderExample.Criteria;
import com.ping.pojo.WxOrderItem;
import com.ping.pojo.WxOrderItemExample;
import com.ping.pojo.WxOrderNo;
import com.ping.pojo.WxShop;
import com.ping.pojo.WxUser;
import com.ping.redis.RedisUtil;
import com.ping.service.IOrderService;
import com.ping.util.JsonUtils;
import com.ping.util.OrderIdUtils;
import com.ping.util.WXResult;
import com.ping.vo.ItemSkuVo;
import com.ping.vo.OrderVo;
import com.ping.wxpay.WXPayUtil;
/**
 * 前台订单相关
 * <p>Title: OrderService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月19日上午9:47:21
 * @version 1.0
 */
@Service
public class OrderService implements IOrderService{
	
	//'支付类型，1、在线支付Online payments，2、货到付款'Cash on delivery
	/**
	 * 在线支付
	 */
	private static final Integer ONLINE_PAYMENTS = 1;
	
	/**
	 * 货到付款
	 */
	private static final Integer CASH_ON_DELIVERY = 2;
	
	//'状态：1、未付款Unpaid，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭，7、订单删除',
	
	/**
	 * 未付款
	 */
	private static final Integer UNPAID = 1;
	/**
	 * 已付款paid
	 */
	private static final Integer PAID = 2;
	/**
	 * 未发货Not shipped
	 */
	private static final Integer NOT_SHIPPED = 3;
	/**
	 * 已发货Shipped
	 */
	private static final Integer SHIPPED = 4;
	/**
	 * 交易成功Successful transaction
	 */
	private static final Integer SUCCESSFUL_TRANSACTION = 5;
	/**
	 * 交易关闭transaction closed
	 */
	private static final Integer TRANSACTION_CLOSED = 6;
	/**
	 * 订单删除 Order deletion
	 */
	private static final Integer ORDER_DELETION = 7;
	
	//买家是否已经评价（0未评价，1已评价）
	/**
	 * 未评价Not evaluated
	 */
	private static final Byte NOT_EVALUATED = 0;
	/**
	 * 已评价EVALUATED
	 */
	private static final Byte EVALUATED = 1;
	
	@Autowired
	private WxOrderMapper orderMapper;
	@Autowired
	private WxOrderItemMapper orderItemMapper;
	@Autowired
	private WxItemMapper itemMapper;
	@Autowired
	private WxItemSkuMapper itemSkuMapper;
	@Autowired
	private WxShopMapper shopMapper;
	@Autowired
	private WxUserMapper userMapper;
	@Autowired
	private RedisUtil redis;
	@Autowired
	private WxOrderNoMapper orderNoMapper;
	
	//提交订单
	@Override
	public Map<String, String> addOrder(Long userId,Map<Long, String> message, String deliveryAddress,
			String currentOrderId) throws Exception{
		//判断Redis缓存中是否有订单信息，如果没有则返回空，如果有则取出相关信息并将Redis缓存清空
		Map<WxShop, List<ItemSkuVo>> itemSkuVoMap = redis.getMap("order_map_"+currentOrderId+"_"+userId);
		Long totalPrice = Long.parseLong(redis.getString("order_totalPrice_"+currentOrderId+"_"+userId));
		if(itemSkuVoMap==null||itemSkuVoMap.size()<1||totalPrice==null){
			return null;
		}
		//将Redis缓存过期时间更改为60秒
		redis.setExp("order_map_"+currentOrderId+"_"+userId, 60);
		redis.setExp("order_totalPrice_"+currentOrderId+"_"+userId, 60);
		//生成微信支付统一下单参数
		Map<String, String> unifiedOrderParam = new HashMap<>();
		unifiedOrderParam.put("total_fee", totalPrice.toString());
		unifiedOrderParam.put("out_trade_no", WXPayUtil.generateNonceStr());
		String body = "";
		//生成订单信息
		for (Entry<WxShop, List<ItemSkuVo>> item : itemSkuVoMap.entrySet()) {
			WxShop shop = item.getKey();
			List<ItemSkuVo> itemSkuVoList = item.getValue();
			
			WxOrder order = new WxOrder();
			//设置订单id
			order.setOrderId(OrderIdUtils.getOrderId());
			//设置支付金额
			order.setPayment(totalPrice);
			//设置优惠金额（暂时为0元）
			order.setDiscountAmount(0L);
			order.setCreateTime(new Date());
			//'支付类型，1、在线支付，2、货到付款'
			order.setPaymentType(ONLINE_PAYMENTS);
			//设置邮费（暂时为0元）
			order.setPostFee(0L);
			//设置支付状态('状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭，7、订单删除',)
			order.setStatus(UNPAID);
			//设置下单时间和更新时间
			order.setCreateTime(new Date());
			order.setUpdateTime(new Date());
			//设置店铺id和用户id
			order.setShopId(shop.getId());
			order.setUserId(userId);
			//买家留言
			order.setBuyerMessage(message.get(shop.getId()));
			//设置买家昵称
			WxUser user = userMapper.selectByPrimaryKey(userId);
			order.setBuyerNick(user.getNickname());
			//设置收货地址
			order.setDeliveryAddress(deliveryAddress);
			//买家是否已经评价
			order.setBuyerRate(NOT_EVALUATED);
			//插入订单表
			orderMapper.insertSelective(order);
			//生成订单明细列表
			for (ItemSkuVo itemSkuVo : itemSkuVoList) {
				WxOrderItem orderItem = new WxOrderItem();
				orderItem.setItemId(itemSkuVo.getItemId());
				orderItem.setOrderId(order.getOrderId());
				orderItem.setTitle(itemSkuVo.getItem().getTitle());
				orderItem.setItemSpecValue(JsonUtils.objectToJson(itemSkuVo.getItemSpecValueList()));
				orderItem.setNum(itemSkuVo.getNum());
				orderItem.setPrice(itemSkuVo.getPrice());
				orderItem.setTotalFee(itemSkuVo.getNum()*itemSkuVo.getPrice());
				orderItem.setPicPath(itemSkuVo.getItem().getImage().split(",")[0]);
				orderItemMapper.insertSelective(orderItem);
			}
			//生成合并支付订单号和微信支付订单号关联表
			WxOrderNo orderNo = new WxOrderNo();
			orderNo.setOrderId(order.getOrderId());
			orderNo.setOutTradeNo(unifiedOrderParam.get("out_trade_no"));
			orderNoMapper.insertSelective(orderNo);
			//生成商品描述，由店铺名称组成
			body += shop.getShopName()+",";
		}
		unifiedOrderParam.put("body",body);
		
		return unifiedOrderParam;
	}

	//订单支付
	@Override
	public WXResult upDateOrderPay() {
		// TODO Auto-generated method stub
		return null;
	}

	//查询所有订单列表
	@Override
	public List<OrderVo> selectOrder(Long userId) {
		//1、查询商品订单
		WxOrderExample orderExample = new WxOrderExample();
		orderExample.setOrderByClause("update_time DESC");
		Criteria orderCriteria = orderExample.createCriteria();
		orderCriteria.andUserIdEqualTo(userId);
		List<WxOrder> orderList = orderMapper.selectByExample(orderExample);
		List<OrderVo> result = new ArrayList<>();
		//2、根据订单id查询订单明细
		for (WxOrder wxOrder : orderList) {
			OrderVo orderVo = new OrderVo();
			orderVo.setOrder(wxOrder);
			WxOrderItemExample orderItemExample = new WxOrderItemExample();
			com.ping.pojo.WxOrderItemExample.Criteria orderItemCriteria = orderItemExample.createCriteria();
			orderItemCriteria.andOrderIdEqualTo(wxOrder.getOrderId());
			List<WxOrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
			orderVo.setOrderItemList(orderItemList);
			result.add(orderVo);
		}
		return result;
	}

	//查询订单各个状态的数量
	@Override
	public Map<String, Integer> selectOrderNum(Long userId) {
		Map<String, Integer> result = new HashMap<>();
		//查询订单各个状态的数量

		//未付款订单数量
		WxOrderExample orderExample = new WxOrderExample();
		Criteria orderCriteria1 = orderExample.createCriteria();
		orderCriteria1.andUserIdEqualTo(userId);
		orderCriteria1.andStatusEqualTo(UNPAID);
		int count1 = orderMapper.countByExample(orderExample);
		result.put("unPaid", count1);
		//待发货订单数量
		orderExample.clear();
		Criteria orderCriteria2 = orderExample.createCriteria();
		orderCriteria2.andUserIdEqualTo(userId);
		orderCriteria2.andStatusEqualTo(NOT_SHIPPED);
		int count2 = orderMapper.countByExample(orderExample);
		result.put("notShipped", count2);
		//待收货订单数量
		orderExample.clear();
		Criteria orderCriteria3 = orderExample.createCriteria();
		orderCriteria3.andUserIdEqualTo(userId);
		orderCriteria3.andStatusEqualTo(SHIPPED);
		int count3 = orderMapper.countByExample(orderExample);
		result.put("shipped", count3);
		//未评价订单数量
		orderExample.clear();
		Criteria orderCriteria4 = orderExample.createCriteria();
		orderCriteria4.andUserIdEqualTo(userId);
		orderCriteria4.andStatusEqualTo(SUCCESSFUL_TRANSACTION);
		orderCriteria4.andBuyerRateEqualTo(NOT_EVALUATED);
		int count4 = orderMapper.countByExample(orderExample);
		result.put("notEvaluated", count4);
		
		return result;
	}

}
