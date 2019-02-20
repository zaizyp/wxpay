package com.ping.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ping.mapper.WxNewsContentMapper;
import com.ping.mapper.WxNewsMapper;
import com.ping.pojo.WxNews;
import com.ping.pojo.WxNewsContent;
import com.ping.pojo.WxNewsExample;
import com.ping.pojo.WxNewsExample.Criteria;
import com.ping.service.INewsService;
import com.ping.vo.NewsVo;

/**
 * 前台新闻公告service
 * <p>Title: NewsService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月22日上午10:18:07
 * @version 1.0
 */
@Service
public class NewsService implements INewsService{

	@Autowired
	private WxNewsMapper newsMapper;
	@Autowired
	private WxNewsContentMapper newsContentMapper;
	
	//查询新闻公告列表
	@Override
	public List<NewsVo> getNewsList() throws Exception{
		//查询新闻公告
		WxNewsExample example = new WxNewsExample();
		example.setOrderByClause("sort_order DESC, updated DESC");
		List<WxNews> newsList = newsMapper.selectByExample(example);
		List<NewsVo> newsVoList = new ArrayList<>();
		//查询新闻公告内容
		for (WxNews news : newsList) {
			WxNewsContent newsContent = newsContentMapper.selectByPrimaryKey(news.getId());
			NewsVo newsVo = new NewsVo();
			BeanUtils.copyProperties(news, newsVo);
			newsVo.setContent(newsContent.getContent());
			newsVoList.add(newsVo);
		}
		return newsVoList;
	}

	//查询num条新闻公告
	@Override
	public List<NewsVo> getNewsList(Integer num) throws Exception{
		//查询新闻公告
		WxNewsExample example = new WxNewsExample();
		Criteria criteria = example.createCriteria();
		//设置置顶查询条件
		criteria.andSortOrderEqualTo(0);
		example.setOrderByClause("updated DESC");
		PageHelper.startPage(1, num);
		List<WxNews> newsList = newsMapper.selectByExample(example);
		//判断查询到的置顶新闻是否有num条，如果多了num则删掉多余部分，如果不足则再查询普通新闻补足
		if(newsList.size()>num){
			for(int i = num;i<newsList.size();i++){
				newsList.remove(i);
			}
		}else if (newsList.size()<num) {
			WxNewsExample example2 = new WxNewsExample();
			example2.setOrderByClause("sort_order DESC,updated DESC");
			Criteria criteria2 = example2.createCriteria();
			criteria2.andSortOrderGreaterThan(0);
			//设置查询数目
			PageHelper.startPage(1, num-newsList.size());
			List<WxNews> list2 = newsMapper.selectByExample(example2);
			newsList.addAll(list2);
		}
		List<NewsVo> newsVoList = new ArrayList<>();
		//查询新闻公告内容
		for (WxNews news : newsList) {
			WxNewsContent newsContent = newsContentMapper.selectByPrimaryKey(news.getId());
			NewsVo newsVo = new NewsVo();
			BeanUtils.copyProperties(news, newsVo);
			newsVo.setContent(newsContent.getContent());
			newsVoList.add(newsVo);
		}
		return newsVoList;
	}
	
	//根据新闻id查询新闻详细信息
	public NewsVo getNews(Long newsId) throws Exception{
		WxNews news = newsMapper.selectByPrimaryKey(newsId);
		WxNewsContent newsContent = newsContentMapper.selectByPrimaryKey(newsId);
		NewsVo newsVo = new NewsVo();
		BeanUtils.copyProperties(news, newsVo);
		newsVo.setContent(newsContent.getContent());
		
		return newsVo;
	}
}
