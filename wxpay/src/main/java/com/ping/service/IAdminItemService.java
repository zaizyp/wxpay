package com.ping.service;

import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemDesc;
import com.ping.pojo.WxItemSpec;
import com.ping.util.WXResult;
import com.ping.vo.PageData;

/**
 * 商品Service
 * <p>Title: IAdminItemService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月13日下午6:47:45
 * @version 1.0
 */
public interface IAdminItemService {
	
	/**
	 * 新增商品
	 * <p>Title: addItem</p>
	 * <p>Description: </p>
	 * @param item	商品
	 * @param itemDesc	商品描述
	 * @param itemSpec	商品规格
	 * @param itemSkuList	商品SKU 
	 * @return
	 */
	public WXResult addItem(WxItem item,WxItemDesc itemDesc,WxItemSpec itemSpec,String itemSkuList);
	
	/**
	 * 查询商品列表
	 * <p>Title: itemList</p>
	 * <p>Description: </p>
	 * @param page	页码
	 * @param rows	每页显示的数量
	 * @return
	 */
	public PageData itemList(Integer page,Integer rows);
	
	/**
	 * 删除商品
	 * <p>Title: deleteItem</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	public WXResult deleteItem(Long id);
	
}
