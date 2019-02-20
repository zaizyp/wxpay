package com.ping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.WxAd;
import com.ping.service.IAdService;
import com.ping.service.INewsService;
import com.ping.service.IShopingCartService;
import com.ping.vo.AdItemVo;
import com.ping.vo.NewsVo;

/**
* 微信商城主页
* <p>Title: IndexController</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月25日 下午4:35:45
* @version 1.0
*/
@Controller
@RequestMapping("index")
public class IndexController {

	@Autowired
	private IShopingCartService shopingCartService;
	@Autowired
	private INewsService newsService;
	@Autowired
	private IAdService adService;
	
	@RequestMapping()
	public String index(HttpSession session,Model model) throws Exception{
		
		Long userId = (Long)session.getAttribute("user_id");
		//获取购物车商品数量
		Integer shopCartCount = shopingCartService.getShopingCartCount(userId);
		model.addAttribute("shopCartCount", shopCartCount);
		//获取新闻公告列表
		List<NewsVo> newsVoList = newsService.getNewsList(5);
		model.addAttribute("newsVoList", newsVoList);
		/* 广告分类id分别为
		 *  adId	adTitle
		 *  1		首页轮播图
		 *  2		精选推荐1
		 *  3		精选推荐2
		 *  4		猜你喜欢 
		 */
		//获取首页轮播图广告
		List<WxAd> adImageList = adService.getAdList(1L);
		model.addAttribute("adImageList", adImageList);
		//获取精选推荐1
		List<AdItemVo> adItemVoList1 = adService.getAdItemList(2L,6);
		model.addAttribute("adItemVoList1", adItemVoList1);
		//获取精选推荐2
		List<AdItemVo> adItemVoList2 = adService.getAdItemList(3L,12);
		model.addAttribute("adItemVoList2", adItemVoList2);
		//获取猜你喜欢
		List<AdItemVo> adItemVoList3 = adService.getAdItemList(4L,6);
		model.addAttribute("adItemVoList3", adItemVoList3);
		return "index/index";
	}
}