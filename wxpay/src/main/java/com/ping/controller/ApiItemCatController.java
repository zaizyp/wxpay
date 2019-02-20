package com.ping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.service.IAdminItemCatService;
import com.ping.vo.ItemCat;

/**
 * 前端商品目录api
 * <p>Title: ApiItemCatController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月16日下午4:26:23
 * @version 1.0
 */
@Controller
@RequestMapping("api/item/cat/")
public class ApiItemCatController {

	@Autowired
	private IAdminItemCatService itemCatService;
	
	@RequestMapping("list")
	@ResponseBody
	public List<ItemCat> ItemCatList(){
		
		List<ItemCat> itemList = itemCatService.getItemList();
		
		return itemList;
	}
	
	
	
}
