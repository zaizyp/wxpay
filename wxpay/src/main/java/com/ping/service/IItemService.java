package com.ping.service;

import java.util.List;

import com.ping.pojo.WxItem;
import com.ping.vo.ItemSkuVo;
import com.ping.vo.ItemVo;

/**
 * 前台商品Service
 * <p>Title: IItemService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月15日下午1:23:18
 * @version 1.0
 */
public interface IItemService {
	
	/**
	 * 根据分类查询商品列表
	 * <p>Title: itemList</p>
	 * <p>Description: </p>
	 * @param cid	分类id
	 * @return
	 */
	public List<WxItem> itemList(Long cid);
	
	/**
	 * 根据itemId查询商品
	 * <p>Title: selectItem</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	public ItemVo selectItem(Long id);	
	
	/**
	 * 根据SKUId查询商品
	 * <p>Title: selectItemBySkuId</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	public ItemSkuVo selectItemBySkuId(Long id);
}
