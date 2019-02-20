package com.ping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxDeliveryAddress;
import com.ping.pojo.WxShop;
import com.ping.redis.RedisUtil;
import com.ping.service.IAddressService;
import com.ping.service.IItemService;
import com.ping.service.IOrderService;
import com.ping.service.IWXPayService;
import com.ping.service.IWxService;
import com.ping.util.WXResult;
import com.ping.vo.ItemSkuVo;
import com.ping.vo.OrderVo;
import com.ping.wxpay.WXPayConstants.SignType;

/**
 * <p>
 * Title: OrderController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 周运平
 * @date 2018年10月29日 下午8:13:48
 * @version 1.0
 */
@Controller
@RequestMapping("order")
public class OrderController {

	private static Logger log = Logger.getLogger(OrderController.class);

	@Autowired
	private IWXPayService wxPayService;//微信支付

	
/*	@RequestMapping("/pay")
	public String Pay(HttpServletRequest request, Model model, String orderId) {
		// 1 配置页面wxconfig参数
		String url = request.getRequestURL().toString();
		String[] split = url.split("#");// 去除URL后面的#部分
		try {
			WXResult config = wxPayService.getWXconfig(split[0]);
			if (config.getStatus() != 200)
				log.error(config.getMsg());
			model.addAttribute("config", (Map<String, String>) config.getData());
		} catch (IOException | ParseException e) {
			log.error(e.getMessage());
		}
		model.addAttribute("orderId", orderId);

		// 2 配置页面支付参数
		try {
			WXResult data = wxPayService.unifiedOrder(orderId, request.getSession().getAttribute("openid").toString(),
					SignType.MD5);
			if (data.getStatus() != 200)
				log.error(data.getMsg());
			model.addAttribute("data", (Map<String, String>) data.getData());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "order/order_pay";
	}*/

/*	@RequestMapping("/test")
	public String test(HttpServletRequest request, Model model, String orderId) {
		// 1 配置页面wxconfig参数
		String url = request.getRequestURL().toString();
		String[] split = url.split("#");// 去除URL后面的#部分
		try {
			WXResult config = wxPayService.getWXconfig(split[0]);
			if (config.getStatus() != 200)
				log.error(config.getMsg());
			model.addAttribute("config", (Map<String, String>) config.getData());
		} catch (IOException | ParseException e) {
			log.error(e.getMessage());
		}
		// 2 配置页面支付参数
		try {
			WXResult data = wxPayService.unifiedOrder(orderId, request.getSession().getAttribute("openid").toString(),
					SignType.MD5);
			if (data.getStatus() != 200)
				log.error(data.getMsg());
			model.addAttribute("data", (Map<String, String>) data.getData());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "order/test_pay";
	}*/
	
	@RequestMapping("/success")
	public String success(){
		return "order/success";
	}
	
	/*******************上面为微信支付相关接口********************/
	/************************分隔线***************************/
	/*******************下面为商城订单相关接口********************/
	@Autowired
	private IOrderService orderService;//订单service	
	@Autowired
	private IAddressService addressService;//收货地址Service
	@Autowired
	private IItemService itemService;//商品Service
	@Autowired IWxService wxService;
	@Autowired
	private RedisUtil redis;
	
	//从商品页面中下单
	@RequestMapping("/pay")
	public String confirmOrder(HttpSession session, Model model,HttpServletRequest request,
			Long skuId,Integer num) throws Exception{
		Long userId = (Long) session.getAttribute("user_id");
		
		//收货地址
		WxDeliveryAddress defaultAddress = addressService.getDefaultAddress(userId);
		model.addAttribute("defaultAddress", defaultAddress);
		//商品详情
		//创建一个map按照不同的店铺来存储商品信息
		Map<WxShop, List<ItemSkuVo>> itemSkuVoMap = new HashMap<>();
		ItemSkuVo itemSkuVo = itemService.selectItemBySkuId(skuId);
		itemSkuVo.setNum(num);
		//计算总金额
		Long totalPrice = itemSkuVo.getNum() * itemSkuVo.getPrice();
		ArrayList<ItemSkuVo> list = new ArrayList<>();
		list.add(itemSkuVo);
		itemSkuVoMap.put(itemSkuVo.getShop(), list);
		
		model.addAttribute("itemSkuVoMap", itemSkuVoMap);
		
		//配送方式以及运费
		String deliveriesType = "快递";//暂时固定写为快递，以后系统升级再做更改
		model.addAttribute("deliveriesType", deliveriesType);
		Long postFee = 1000L;//暂时固定为10块， 以后系统升级再做更改
		totalPrice+=postFee;
		model.addAttribute("postFee", postFee);
		model.addAttribute("totalPrice", totalPrice);
		//生成一个数字标识当前订单
		Long currentOrderId = (System.currentTimeMillis()/1000)%1000;
		model.addAttribute("currentOrderId", currentOrderId);
		
		//将数据缓存到Redis中
		redis.setMapExp("order_map_"+currentOrderId+"_"+userId, itemSkuVoMap, 60*10);
		redis.setString("order_totalPrice_"+currentOrderId+"_"+userId, totalPrice.toString(), 60*10);
		return "index/order_info";
	}
	
	//订单支付
	@RequestMapping("/payparam")
	@ResponseBody
	public WXResult orderPay(HttpSession session,Map<Long, String> message,String deliveryAddress,
			String currentOrderId,HttpServletRequest request) throws Exception{
		log.info("留言信息"+message.toString());
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		}else {
			ip = request.getHeader("x-forwarded-for");
		}
		Long userId = (Long)session.getAttribute("user_id");
		String openid = session.getAttribute("openid").toString();
		//调用订单service生成订单
		Map<String, String> unifiedOrderParam = orderService.addOrder(userId, message, deliveryAddress, currentOrderId);
		log.info(unifiedOrderParam.toString());
		//调用微信支付service生成支付相关参数
		WXResult unifiedOrder = wxPayService.unifiedOrder(unifiedOrderParam, ip, openid, SignType.MD5);
		log.info(unifiedOrder.toString());
		return unifiedOrder;
	}
	
	//用户端订单列表
	@RequestMapping
	public String orderList(HttpSession session, Model model) {
		Long userId = (Long)session.getAttribute("user_id");
		List<OrderVo> orders = orderService.selectOrder(userId);
		model.addAttribute("orders",orders);
		return "index/all_orders";
	}
	
	
	

}
