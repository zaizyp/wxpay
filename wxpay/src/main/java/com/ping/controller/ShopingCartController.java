package com.ping.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxShopingCart;
import com.ping.service.IShopingCartService;
import com.ping.util.JsonUtils;
import com.ping.util.WXResult;
import com.ping.vo.CartVo;

/**
 * 前台购物车相关
 * <p>Title: ShopingCartController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月19日上午9:54:06
 * @version 1.0
 */
@Controller
@RequestMapping("shopingCart")
public class ShopingCartController {

	@Autowired
	private IShopingCartService shopingCartService;
	
	//购物车页面
	@RequestMapping("/list")
	public String cartList(Model model,HttpSession session){
		
		Long userId = (Long) session.getAttribute("user_id");
		List<CartVo> cartList = shopingCartService.getShopingCartList(userId);
		model.addAttribute("cartList", JsonUtils.objectToJson(cartList));
		return "index/shopcart";
	}
	
	//加入购物车
	@RequestMapping("/add")
	@ResponseBody
	public WXResult addShopingCart(
			WxShopingCart shopingCart,
			HttpSession session){
		
		shopingCart.setUserId((Long) session.getAttribute("user_id"));
		WXResult wxResult = shopingCartService.addCart(shopingCart);
		
		return wxResult;
	}
	
	//更新购物车中商品数量
	@RequestMapping("/update")
	@ResponseBody
	public WXResult updateCart(HttpSession session,Long cartId,Integer num){
		Long userId = (Long) session.getAttribute("user_id");
		WXResult result = shopingCartService.updateCart(cartId, num, userId);
		
		return result;
	}
	
	//从购物车中删除
	@RequestMapping("/delete")
	@ResponseBody
	public WXResult deleteShopingCart(HttpSession session,Long cartId){
		Long userId = (Long) session.getAttribute("user_id");
		WXResult result = shopingCartService.deleteCart(userId, cartId);
		return result;
	}
	
	//查询用户购物车商品数量
	@RequestMapping("/count")
	@ResponseBody
	public void getCount(HttpSession session,HttpServletResponse response){
		Long userId = (Long) session.getAttribute("user_id");
		Integer count = shopingCartService.getShopingCartCount(userId);
		
		try {
			response.getWriter().write(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
