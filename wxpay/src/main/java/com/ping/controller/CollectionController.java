package com.ping.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxCollection;
import com.ping.service.ICollectionService;
import com.ping.service.impl.CollectionService;
import com.ping.util.WXResult;
import com.ping.vo.CollectionVo;

/**
 * 商品收藏Controller
 * <p>Title: CollectionController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月21日下午9:31:31
 * @version 1.0
 */
@Controller
@RequestMapping("collection")
public class CollectionController {

	@Autowired
	private ICollectionService collectionService;
	
	@RequestMapping("/list")
	public String collectionList(HttpSession session,Model model) throws Exception{
		Long userId = (Long)session.getAttribute("user_id");
		List<CollectionVo> list = collectionService.getCollectionList(userId);
		model.addAttribute("collectionVoList",list);
		
		return "index/shoucang";
	}
	
	//从收藏中删除
	@RequestMapping("/delete")
	@ResponseBody
	public WXResult deleteCollection(HttpSession session,Long collId) throws Exception{
		Long userId = (Long)session.getAttribute("user_id");
		WXResult result = collectionService.deleteCollection(collId, userId);
		return result;
	}
	
	//加入收藏
	@RequestMapping("/add")
	@ResponseBody
	public WXResult addCollection(HttpSession session,WxCollection collection) throws Exception{
		if(collection.getItemId()!=null&&collection.getItemId()>0){
			Long userId = (Long)session.getAttribute("user_id");
			WXResult result = collectionService.addCollection(collection, userId);
			return result;
		}
		return WXResult.build(500, "加入收藏失败");
	}
	
}
