package com.ping.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.mapper.WxCollectionMapper;
import com.ping.mapper.WxItemMapper;
import com.ping.pojo.WxCollection;
import com.ping.pojo.WxCollectionExample;
import com.ping.pojo.WxCollectionExample.Criteria;
import com.ping.pojo.WxItem;
import com.ping.service.ICollectionService;
import com.ping.util.WXResult;
import com.ping.vo.CollectionVo;

/**
 * 用户收藏商品Service
 * <p>Title: CollectionService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月21日下午9:20:48
 * @version 1.0
 */
@Service
public class CollectionService implements ICollectionService {

	@Autowired
	private WxCollectionMapper collectionMapper;
	@Autowired 
	private WxItemMapper itemMapper;
	
	//查询用户所有收藏商品
	@Override
	public List<CollectionVo> getCollectionList(Long userId) throws Exception {
		//查询收藏表
		WxCollectionExample example = new WxCollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<WxCollection> list = collectionMapper.selectByExample(example);
		List<CollectionVo> resultList = new ArrayList<>();
		//根据收藏表商品id查询商品表
		for (WxCollection collection : list) {
			CollectionVo collectionVo = new CollectionVo();
			BeanUtils.copyProperties(collection, collectionVo);
			WxItem item = itemMapper.selectByPrimaryKey(collection.getItemId());
			collectionVo.setTitle(item.getTitle());
			collectionVo.setLowPrice(item.getLowPrice());
			collectionVo.setImage(item.getImage().split(",")[0]);
			resultList.add(collectionVo);
		}
		
		return resultList;
	}

	//根据id查询收藏
	@Override
	public CollectionVo getCollection(Long collId, Long userId) throws Exception {
		WxCollectionExample example = new WxCollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIdEqualTo(collId);
		List<WxCollection> list = collectionMapper.selectByExample(example);
		if (list.size()>0){
			CollectionVo collectionVo = new CollectionVo();
			WxCollection collection = list.get(0);
			BeanUtils.copyProperties(collection, collectionVo);
			WxItem item = itemMapper.selectByPrimaryKey(collection.getItemId());
			collectionVo.setTitle(item.getTitle());
			collectionVo.setLowPrice(item.getLowPrice());
			collectionVo.setImage(item.getImage().split(",")[0]);
			return collectionVo;
		}
		return null;
	}

	//将商品加入收藏
	@Override
	public WXResult addCollection(WxCollection coll, Long userId) throws Exception {
		coll.setUserId(userId);
		coll.setCreated(new Date());
		coll.setUpdated(new Date());
		int count = collectionMapper.insert(coll);
		if(count>0)
			return WXResult.ok();
		return WXResult.build(500, "插入失败");
	}

	//将商品从收藏中删除
	@Override
	public WXResult deleteCollection(Long collId, Long userId) throws Exception {
		WxCollectionExample example = new WxCollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(collId);
		criteria.andUserIdEqualTo(userId);
		int count = collectionMapper.deleteByExample(example);
		if(count>0)
			return WXResult.ok();
		return WXResult.build(500, "删除失败");
	}

}
