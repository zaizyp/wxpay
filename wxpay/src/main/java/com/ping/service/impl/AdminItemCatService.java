package com.ping.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.mapper.WxItemCatMapper;
import com.ping.pojo.WxItemCat;
import com.ping.pojo.WxItemCatExample;
import com.ping.pojo.WxItemCatExample.Criteria;
import com.ping.service.IAdminItemCatService;
import com.ping.util.WXResult;
import com.ping.vo.EUTreeNode;
import com.ping.vo.ItemCat;

/**
* 商品分类
* <p>Title: AdminItemService</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年11月4日 下午3:07:57
* @version 1.0
*/
@Service
public class AdminItemCatService implements IAdminItemCatService {

	@Autowired
	private WxItemCatMapper itemCatMapper;
	
	//根据id获取分类
	@Override
	public WxItemCat getItemCat(Long id) {
		WxItemCat itemCat = itemCatMapper.selectByPrimaryKey(id);
		return itemCat;
	}
	//前台查询所有状态为正常的分类
	@Override
	public List<ItemCat> getItemList() {
		//创建查询条件，查询分类
		WxItemCatExample example = new WxItemCatExample();
		example.setOrderByClause("sort_order desc");
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(0L);
		criteria.andStatusEqualTo(true);//设置查询条件分类状态为正常
		List<WxItemCat> itemCatList = itemCatMapper.selectByExample(example);
		
		//根据查询结果生成树形结构
		List<ItemCat> catList = new ArrayList<>();
		for(WxItemCat itemCat : itemCatList){
			ItemCat item = new ItemCat();
			item.setId(itemCat.getId());
			item.setName(itemCat.getName());
			item.setSortOrder(itemCat.getSortOrder());
			
			//查询该父类分类下的所有子分类
			WxItemCatExample example2 = new WxItemCatExample();
			Criteria criteria2 = example2.createCriteria();
			example2.setOrderByClause("sort_order desc");//设置排序
			criteria2.andParentIdEqualTo(itemCat.getId());
			criteria2.andStatusEqualTo(true);//设置查询条件分类状态为正常
			List<WxItemCat> children = itemCatMapper.selectByExample(example2);
			item.setChildren(children);
			
			//将该分类添加到返回的结果集
			catList.add(item);
		}
		return catList;
	}
	//根据父类id查询一级分类列表
	@Override
	public List<EUTreeNode> getItemCatList(long parentId) {
		//创建返回结果
		List<EUTreeNode> catList = new ArrayList<>();
		//判断是否为只需要查询一级父分类
		if(parentId==-1){
			parentId = 0L;
			EUTreeNode node = new EUTreeNode();
			node.setId(0L);
			node.setText("一级分类");
			node.setState("open");
			catList.add(node);
		}
		//创建查询条件，根据父类目的id查询
		WxItemCatExample example = new WxItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<WxItemCat> itemCatList = itemCatMapper.selectByExample(example);
		
		//根据查询结果生成树形结构
		for(WxItemCat itemCat : itemCatList){
			EUTreeNode node = new EUTreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent()? "closed":"open");
			
			catList.add(node);
		}
		return catList;
	}
	//根据父类id查询一级分类详情列表
	@Override
	public List<ItemCat> getItemCatListDetails(long parentId) {
		//创建查询条件，查询分类
		WxItemCatExample example = new WxItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<WxItemCat> itemCatList = itemCatMapper.selectByExample(example);
		
		//根据查询结果生成树形结构
		List<ItemCat> catList = new ArrayList<>();
		for(WxItemCat itemCat : itemCatList){
			ItemCat item = new ItemCat();
			item.setId(itemCat.getId());
			item.setName(itemCat.getName());
			item.setParentId(itemCat.getParentId());
			item.setSortOrder(itemCat.getSortOrder());
			item.setStatus(itemCat.getStatus());
			item.setImage(itemCat.getImage());
			item.setState(parentId==0L? "closed":"open");
			//将该分类添加到返回的结果集
			catList.add(item);
		}
		return catList;
	}
	//新建分类
	@Override
	public WXResult creatItemCat(WxItemCat itemCat) {
		itemCat.setCreated(new Date());
		itemCat.setUpdated(new Date());
		if (itemCat.getParentId()==0) {
			itemCat.setIsParent(true);
		}else {
			itemCat.setIsParent(false);
		}
		itemCatMapper.insert(itemCat);
		return WXResult.ok();
	}
	//更新分类
	@Override
	public WXResult updateItemCat(WxItemCat itemCat) {
		//判断分类是否存在
		WxItemCat itemCat2 = getItemCat(itemCat.getId());
		if (itemCat2==null) {
			return WXResult.build(500, "该分类不存在");
		}
		itemCat.setUpdated(new Date());
		itemCatMapper.updateByPrimaryKeySelective(itemCat);
		return WXResult.ok();
	}
	//删除分类
	@Override
	public WXResult deleteItemCat(Long id) {
		//判断分类是否存在
		WxItemCat itemCat = getItemCat(id);
		if (itemCat==null) {
			return WXResult.build(500, "该分类不存在");
		}
		itemCatMapper.deleteByPrimaryKey(id);
		return WXResult.ok();
	}
}
