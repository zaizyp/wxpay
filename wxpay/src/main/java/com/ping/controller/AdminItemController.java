package com.ping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemDesc;
import com.ping.pojo.WxItemSku;
import com.ping.pojo.WxItemSpec;
import com.ping.service.IAdminItemService;
import com.ping.util.JsonUtils;
import com.ping.util.WXResult;
import com.ping.vo.PageData;
import com.ping.vo.ItemSku;

/**
 * 商品控制类
 * <p>Title: AdminItemController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月13日下午7:59:54
 * @version 1.0
 */
@Controller
@RequestMapping("admin/item")
public class AdminItemController {
	
	@Autowired
	private IAdminItemService itemService;
	
	@RequestMapping("/save")
	@ResponseBody
	public WXResult addItem(WxItem item,WxItemDesc itemDesc,WxItemSpec itemSpec,String itemSkuList){
		
		WXResult result = itemService.addItem(item, itemDesc, itemSpec, itemSkuList);		
		return result;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageData<WxItem> itemList(Integer page,Integer rows){
		PageData<WxItem> itemList = itemService.itemList(page, rows);
		return itemList;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public WXResult deleteItem(String ids){
		String[] split = ids.split(",");
		for (String id : split) {
			itemService.deleteItem(Long.valueOf(id));
		}
		return WXResult.ok();
	}
}
