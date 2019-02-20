package com.ping.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.mapper.WxItemMapper;
import com.ping.mapper.WxItemSkuMapper;
import com.ping.mapper.WxShopMapper;
import com.ping.mapper.WxShopingCartMapper;
import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemSku;
import com.ping.pojo.WxShop;
import com.ping.pojo.WxShopingCart;
import com.ping.pojo.WxShopingCartExample;
import com.ping.pojo.WxShopingCartExample.Criteria;
import com.ping.service.IShopingCartService;
import com.ping.util.JsonUtils;
import com.ping.util.WXResult;
import com.ping.vo.CartVo;
import com.ping.vo.ItemSku;
import com.ping.vo.ItemSpecValue;
import com.ping.vo.ItemVo;

/**
 * 前台购物车相关
 * <p>Title: ShopingCartService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月19日上午9:46:50
 * @version 1.0
 */
@Service
public class ShopingCartService implements IShopingCartService {

	@Autowired
	private WxShopingCartMapper shopingCartMapper;
	@Autowired
	private WxItemMapper itemMapper;
	@Autowired
	private WxItemSkuMapper itemSkuMapper;
	@Autowired
	private WxShopMapper shopMapper;
	
	//加入购物车
	@Override
	public WXResult addCart(WxShopingCart shopingCart) {
		
		//判断是否已经存在该商品
		WxShopingCartExample example = new WxShopingCartExample();
		Criteria criteria = example.createCriteria();
		criteria.andSkuIdEqualTo(shopingCart.getSkuId());
		List<WxShopingCart> list = shopingCartMapper.selectByExample(example);
		if(list.size()>0){
			list.get(0).setNum(list.get(0).getNum()+shopingCart.getNum());
			list.get(0).setUpdated(new Date());
			shopingCartMapper.updateByPrimaryKeySelective(list.get(0));
		}else{
			shopingCart.setUpdated(new Date());
			shopingCart.setCreated(new Date());
			shopingCartMapper.insertSelective(shopingCart);
		}
		return WXResult.ok();
	}

	//从购物车中删除
	@Override
	public WXResult deleteCart(Long userId, Long cartId) {
		
		WxShopingCartExample example = new WxShopingCartExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(cartId);
		criteria.andUserIdEqualTo(userId);
		int count = shopingCartMapper.deleteByExample(example);
		if(count>0)
			return WXResult.ok();
		return WXResult.build(500, "删除失败");
	}

	//更新购物车商品数量
	public WXResult updateCart(Long cartId,Integer num,Long userId){
		//创建更新对象
		WxShopingCart cart = new WxShopingCart();
		cart.setId(cartId);
		cart.setNum(num);
		cart.setUpdated(new Date());
		//创建更新条件
		WxShopingCartExample example = new WxShopingCartExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(cartId);
		criteria.andUserIdEqualTo(userId);
		int count = shopingCartMapper.updateByExample(cart, example);
		if(count>0)
			return WXResult.ok();
		return WXResult.build(500, "更新失败");
	}
	
	//获取该用户购物车中的商品
	@Override
	public List<CartVo> getShopingCartList(Long userId) {

		WxShopingCartExample example = new WxShopingCartExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<WxShopingCart> list = shopingCartMapper.selectByExample(example);
		List<CartVo> result = new ArrayList<>();
		for (WxShopingCart shopingCart : list) {
			WxItemSku wxItemSku = itemSkuMapper.selectByPrimaryKey(shopingCart.getSkuId());
			WxItem item = itemMapper.selectByPrimaryKey(wxItemSku.getItemId());
			WxShop shop = shopMapper.selectByPrimaryKey(shopingCart.getShopId());
			
			//添加商品信息
			CartVo cartVo = new CartVo();
			BeanUtils.copyProperties(shopingCart, cartVo);
			cartVo.setItem(item);
			//添加店铺信息
			cartVo.setShop(shop);
			//添加商品SKU信息 
			ItemSku itemSku = new ItemSku();
			itemSku.setId(wxItemSku.getId());
			itemSku.setItemId(wxItemSku.getItemId());
			itemSku.setImage(wxItemSku.getImage());
			itemSku.setPrice(wxItemSku.getPrice());
			itemSku.setNum(wxItemSku.getNum());
			itemSku.setItemSpecValue(JsonUtils.jsonToList(wxItemSku.getItemSpecValue(), ItemSpecValue.class));
			cartVo.setItemSku(itemSku);
			result.add(cartVo);
		}
		
		return result;
	}
	
	//查询用户购物车商品数量
	public Integer getShopingCartCount(Long userId){
		WxShopingCartExample example = new WxShopingCartExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		int count = shopingCartMapper.countByExample(example);
		
		return count;
	}

}
