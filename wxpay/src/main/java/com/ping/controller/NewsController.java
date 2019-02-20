package com.ping.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ping.service.INewsService;
import com.ping.vo.NewsVo;

/**
 * 前台新闻公告Controller
 * <p>Title: NewsController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月22日上午10:38:53
 * @version 1.0
 */
@Controller
@RequestMapping("news")
public class NewsController {

	@Autowired
	private INewsService newsService;
	
	@RequestMapping("/list")
	public String newsList(Model model) throws Exception{
		List<NewsVo> newsVoList = newsService.getNewsList();
		model.addAttribute("newsVoList", newsVoList);
		return "index/news_list";
	}
	
	@RequestMapping("/{newsId}")
	public String getNews(@PathVariable Long newsId,Model model) throws Exception{
		NewsVo newsVo = newsService.getNews(newsId);
		if(newsVo!=null){
			model.addAttribute("newsVo", newsVo);
			return "index/news_info";
		}
		return "redirect:/news/list";
	}
	
}
