package com.ping.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ping.pojo.WxUser;
import com.ping.service.IOrderService;
import com.ping.service.IShopingCartService;
import com.ping.service.IUserService;

/**
 * 微信商城用户controller
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月21日下午12:57:53
 * @version 1.0
 */
@Controller
@RequestMapping("mine")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IShopingCartService shopingCartService;
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping()
	public String mine(HttpSession session,Model model) throws Exception{
		//获取用户信息
		Long userId = (Long) session.getAttribute("user_id");
		WxUser user = userService.selectUser(userId);
		//获取购物车数量
		Integer shopCartCount = shopingCartService.getShopingCartCount(userId);
		model.addAttribute("user", user);
		model.addAttribute("shopCartCount", shopCartCount);
		//获取订单各个状态的数量
		Map<String, Integer> orderNum = orderService.selectOrderNum(userId);
		model.addAttribute("orderNum", orderNum);
		
		return "index/mine";
	}
}
