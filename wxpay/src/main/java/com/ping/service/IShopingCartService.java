package com.ping.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ping.pojo.WxShopingCart;
import com.ping.util.WXResult;
import com.ping.vo.CartVo;
import com.ping.vo.ItemVo;

/**
 * 前台购物车Service
 * <p>Title: IShopingCartService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月19日上午9:43:30
 * @version 1.0
 */
public interface IShopingCartService {

	/**
	 * 加入购物车
	 * <p>Title: addCart</p>
	 * <p>Description: </p>
	 * @param shopingCart
	 * @return
	 */
	public WXResult addCart(WxShopingCart shopingCart);
	
	/**
	 * 删除购物车
	 * <p>Title: deleteCart</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @param cartId 购物车项id
	 * @return
	 */
	public WXResult deleteCart(Long userId,Long cartId);
	
	/**
	 * 更新购物车商品数量
	 * <p>Title: updateCart</p>
	 * <p>Description: </p>
	 * @param cartId
	 * @param num
	 * @return
	 */
	public WXResult updateCart(Long cartId,Integer num,Long userId);
	
	/**
	 * 获取该用户购物车列表
	 * <p>Title: shopingCartList</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 */
	public List<CartVo> getShopingCartList(Long userId);
	
	/**
	 * 查询用户购物车商品数量
	 * <p>Title: getShopingCartNum</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @return
	 */
	public Integer getShopingCartCount(Long userId);
	
	
}
