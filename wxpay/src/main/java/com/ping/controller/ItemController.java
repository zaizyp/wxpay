package com.ping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxItem;
import com.ping.service.IAdService;
import com.ping.service.IItemService;
import com.ping.service.IShopingCartService;
import com.ping.util.JsonUtils;
import com.ping.vo.ItemVo;

/**
 * 前台商品Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月14日下午7:57:08
 * @version 1.0
 */
@Controller
@RequestMapping("item")
public class ItemController {

	@Autowired
	private IItemService itemService;
	@Autowired
	private IShopingCartService shopingCartService;
	@Autowired
	private IAdService adService;
	
	//商品详情
	@RequestMapping("/{id}")
	public String itemDetail(@PathVariable Long id,
			Model model,HttpSession session){
		ItemVo itemVo = itemService.selectItem(id);
		Long userId = (Long)session.getAttribute("user_id");
		Integer shopingCartCount = shopingCartService.getShopingCartCount(userId);
		model.addAttribute("itemVo",itemVo);
		model.addAttribute("shopingCartCount",shopingCartCount);
		model.addAttribute("itemSkuList",JsonUtils.objectToJson(itemVo.getItemSkuList()));
		
		return "index/pro_info";
	}
	
	//商品分类列表
	@RequestMapping("/list/{cid}")
	public String itemList(@PathVariable Long cid,Model model){
		List<WxItem> itemList = itemService.itemList(cid);
		model.addAttribute("itemList", itemList);
		return "index/pro_list";
	}
	
	//猜你喜欢列表
	@RequestMapping("/list/like")
	public String ItemLikeList(Model model) throws Exception{
		List<WxItem> itemList = adService.getItemList(4L, 1, 10);
		model.addAttribute("itemList", itemList);
		return "index/pro_list";
	}
	
}
