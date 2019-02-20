package com.ping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxItemCat;
import com.ping.service.IAdminItemCatService;
import com.ping.util.WXResult;
import com.ping.vo.EUTreeNode;
import com.ping.vo.ItemCat;

/**
* <p>Title: AdminItemCatController</p>
* <p>Description:商品类目</p>
* @author  周运平
* @date    2018年11月4日 下午3:16:54
* @version 1.0
*/
@Controller
@RequestMapping("admin/item/cat")
public class AdminItemCatController {
	
	@Autowired
	private IAdminItemCatService itemCatService;
	
	//查询分类列表
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> categoryList(@RequestParam(value="id", defaultValue="0") Long parentId){
		
		List<EUTreeNode> catList = itemCatService.getItemCatList(parentId);

		return catList;
	}
	//查询分类列表详情
	@RequestMapping("/detailsList")
	@ResponseBody
	public List<ItemCat> categoryDetailsList(@RequestParam(value="id", defaultValue="0") Long parentId){
		
		List<ItemCat> catList = itemCatService.getItemCatListDetails(parentId);
		return catList;
	}
	//新建分类
	@RequestMapping("/create")
	@ResponseBody
	public WXResult createItemCat(WxItemCat itemCat){
		if (itemCat.getName().isEmpty()) {
			return WXResult.build(500, "参数不正确");
		} else {
			itemCatService.creatItemCat(itemCat);			
		}
		return WXResult.ok();
	}
	//更新分类
	@RequestMapping("/update")
	@ResponseBody
	public WXResult updateItemCat(WxItemCat itemCat,Long id){
		itemCat.setId(id);
		WXResult result = itemCatService.updateItemCat(itemCat);
		return result;
	}
	//删除分类
	@RequestMapping("/delete")
	@ResponseBody
	public WXResult deleteItemCat(Long id){
		return itemCatService.deleteItemCat(id);
	}
}
