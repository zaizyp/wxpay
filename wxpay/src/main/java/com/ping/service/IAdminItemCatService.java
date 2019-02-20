package com.ping.service;

import java.util.List;

import com.ping.pojo.WxItemCat;
import com.ping.util.WXResult;
import com.ping.vo.EUTreeNode;
import com.ping.vo.ItemCat;

/**
* <p>Title: IAdminItemService</p>
* <p>Description:后台管理系统商品相关接口</p>
* @author  周运平
* @date    2018年11月4日 下午2:59:56
* @version 1.0
*/
public interface IAdminItemCatService {
	
	/**
	 * 根据id查询分类
	 * <p>Title: getItemCat</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	public WxItemCat getItemCat(Long id);
	
	/**
	 * 获取分类列表
	 * @Title: getItemCatList 
	 * @Description: 
	 * @param parentId 父分类id
	 * @return 
	 */
	public List<EUTreeNode> getItemCatList(long parentId);
	
	/**
	 * 获取分类列表详情
	 * <p>Title: getItemCatListDetails</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	public List<ItemCat> getItemCatListDetails(long parentId);
	
	public List<ItemCat> getItemList();
	
	/**
	 * 创建分类
	 * <p>Title: creatItemCat</p>
	 * <p>Description: </p>
	 * @param itemCat
	 * @return
	 */
	public WXResult creatItemCat(WxItemCat itemCat);
	
	/**
	 * 更新分类
	 * <p>Title: updateItemCat</p>
	 * <p>Description: </p>
	 * @param itemCat
	 * @return
	 */
	public WXResult updateItemCat(WxItemCat itemCat);
	
	/**
	 * 创建分类
	 * <p>Title: deleteItemCat</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	public WXResult deleteItemCat(Long id);
	
}
