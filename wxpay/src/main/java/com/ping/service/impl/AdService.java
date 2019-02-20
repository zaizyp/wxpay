package com.ping.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ping.mapper.WxAdMapper;
import com.ping.mapper.WxItemMapper;
import com.ping.pojo.WxAd;
import com.ping.pojo.WxAdExample;
import com.ping.pojo.WxAdExample.Criteria;
import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemExample;
import com.ping.service.IAdService;
import com.ping.vo.AdItemVo;

/**
 * 前台广告Service
 * <p>Title: AdService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月22日下午8:12:04
 * @version 1.0
 */
@Service
public class AdService implements IAdService {

	@Autowired
	private WxAdMapper adMapper;
	@Autowired
	private WxItemMapper itemMapper;
	
	//根据分类查询广告
	@Override
	public List<WxAd> getAdList(Long catId) throws Exception{
		WxAdExample example = new WxAdExample();
		Criteria criteria = example.createCriteria();
		criteria.andCatIdEqualTo(catId);
		List<WxAd> list = adMapper.selectByExample(example);
		return list;
	}

	//根据id查询广告
	@Override
	public WxAd getAd(Long adId) throws Exception{
		return adMapper.selectByPrimaryKey(adId);
	}

	//查询广告商品列表
	@Override
	public List<AdItemVo> getAdItemList(Long catId,Integer num) throws Exception {
		//查询广告信息
		WxAdExample example = new WxAdExample();
		example.setOrderByClause("updated DESC");
		Criteria criteria = example.createCriteria();
		criteria.andCatIdEqualTo(catId);
		PageHelper.startPage(1, num);
		List<WxAd> adList = adMapper.selectByExample(example);
		List<AdItemVo> adItemVoList = new ArrayList<>();
		//查询商品信息
		for (WxAd ad : adList) {
			AdItemVo adItemVo = new AdItemVo();
			BeanUtils.copyProperties(ad, adItemVo);
			WxItem item = itemMapper.selectByPrimaryKey(ad.getItemId());
			adItemVo.setItemTitle(item.getTitle());
			adItemVo.setImage(item.getImage());
			adItemVo.setLowPrice(item.getLowPrice());
			adItemVoList.add(adItemVo);
		}
		return adItemVoList;
	}
	
	//查询广告商品列表
	public List<WxItem> getItemList(Long catId,Integer page,Integer num) throws Exception{
		//查询广告信息
		WxAdExample example = new WxAdExample();
		example.setOrderByClause("updated DESC");
		Criteria criteria = example.createCriteria();
		criteria.andCatIdEqualTo(catId);
		PageHelper.startPage(1, num);
		List<WxAd> adList = adMapper.selectByExample(example);
		//获取商品id列表
		List<Long> itemIdList = new ArrayList<>();
		for (WxAd wxAd : adList) {
			Long itemId = wxAd.getItemId();
			itemIdList.add(itemId);
		}
		WxItemExample example1 = new WxItemExample();
		Criteria criteria2 = example.createCriteria();
		criteria2.andIdIn(itemIdList);
		PageHelper.startPage(1, num);
		List<WxItem> itemList = itemMapper.selectByExample(example1);
		return itemList;
		
	}

}
