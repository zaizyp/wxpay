package com.ping.service;

import java.util.List;

import com.ping.pojo.WxCollection;
import com.ping.util.WXResult;
import com.ping.vo.CollectionVo;

/**
 * 用户收藏商品
 * <p>Title: ICollectionService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月21日下午9:11:34
 * @version 1.0
 */
public interface ICollectionService {

	/**
	 * 查询用户所有收藏商品
	 * <p>Title: getCollectionList</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public List<CollectionVo> getCollectionList(Long userId) throws Exception;
	
	/**
	 * 根据id查询用户收藏商品
	 * <p>Title: getCollection</p>
	 * <p>Description: </p>
	 * @param collId 收藏id
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public CollectionVo getCollection(Long collId,Long userId) throws Exception;
	
	/**
	 * 添加商品到收藏夹
	 * <p>Title: addCollection</p>
	 * <p>Description: </p>
	 * @param coll 收藏商品
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public WXResult addCollection(WxCollection coll,Long userId) throws Exception;
	
	/**
	 * 从收藏中删除商品
	 * <p>Title: deleteCollection</p>
	 * <p>Description: </p>
	 * @param collId 收藏id
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public WXResult deleteCollection(Long collId,Long userId) throws Exception;
	
}
