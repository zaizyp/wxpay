package com.ping.service;

import java.util.List;

import com.ping.pojo.WxAd;
import com.ping.pojo.WxItem;
import com.ping.vo.AdItemVo;

/**
 * 前台首页广告
 * <p>Title: IAdService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月22日下午8:08:58
 * @version 1.0
 */
public interface IAdService {

	/**
	 * 根据分类查询广告
	 * <p>Title: getAd</p>
	 * <p>Description: </p>
	 * @param catId 广告分类id
	 * @return
	 */
	public List<WxAd> getAdList(Long catId) throws Exception;
	
	/**
	 * 根据广告id查询广告
	 * <p>Title: getAd</p>
	 * <p>Description: </p>
	 * @param adId 广告id
	 * @return
	 */
	public WxAd getAd(Long adId) throws Exception;
	
	/**
	 * 查询广告商品列表
	 * <p>Title: getAdItemList</p>
	 * <p>Description: </p>
	 * @param catId 广告分类id
	 * @param num 查询数量
	 * @return adItemVoList
	 * @throws Exception
	 */
	public List<AdItemVo> getAdItemList(Long catId,Integer num) throws Exception;
	
	/**
	 * 查询广告商品列表
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param catId
	 * @param page 页数
	 * @param num 每页的数量
	 * @return ItemList
	 * @throws Exception
	 */
	public List<WxItem> getItemList(Long catId,Integer page,Integer num) throws Exception;
}
