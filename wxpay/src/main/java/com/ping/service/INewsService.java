package com.ping.service;

import java.util.List;

import com.ping.vo.NewsVo;

/**
 * 前台新闻service
 * <p>Title: INewsService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月22日上午10:07:11
 * @version 1.0
 */
public interface INewsService {

	/**
	 * 查询全部新闻公告列表
	 * <p>Title: getNewsList</p>
	 * <p>Description: </p>
	 * @return List<NewsVo>
	 */
	public List<NewsVo> getNewsList() throws Exception;
	
	/**
	 * 查询num个商品
	 * <p>Title: getNewsList</p>
	 * <p>Description: </p>
	 * @param num
	 * @return
	 */
	public List<NewsVo> getNewsList(Integer num) throws Exception;
	
	/**
	 * 根据新闻id查询新闻详细信息
	 * <p>Title: getNews</p>
	 * <p>Description: </p>
	 * @param newsId 新闻id
	 * @return
	 * @throws Exception
	 */
	public NewsVo getNews(Long newsId) throws Exception;
	
}
