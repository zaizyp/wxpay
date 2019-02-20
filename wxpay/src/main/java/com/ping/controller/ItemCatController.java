package com.ping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ping.service.IAdminItemCatService;
import com.ping.vo.ItemCat;

/**
 * 前台商品分类Controller
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月14日下午7:59:10
 * @version 1.0
 */
@Controller
@RequestMapping("item/cat")
public class ItemCatController {
	
	@Autowired
	private IAdminItemCatService itemCatService;
	
	@RequestMapping("/list")
	public String itemList(Model model){
		List<ItemCat> catList = itemCatService.getItemList();
		model.addAttribute("catList", catList);
		return "index/classify";
	}
	
}
