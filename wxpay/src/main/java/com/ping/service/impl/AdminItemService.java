package com.ping.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ping.mapper.WxItemDescMapper;
import com.ping.mapper.WxItemMapper;
import com.ping.mapper.WxItemSkuMapper;
import com.ping.mapper.WxItemSpecMapper;
import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemDesc;
import com.ping.pojo.WxItemDescExample;
import com.ping.pojo.WxItemExample;
import com.ping.pojo.WxItemExample.Criteria;
import com.ping.pojo.WxItemSku;
import com.ping.pojo.WxItemSkuExample;
import com.ping.pojo.WxItemSpec;
import com.ping.pojo.WxItemSpecExample;
import com.ping.service.IAdminItemService;
import com.ping.util.JsonUtils;
import com.ping.util.WXResult;
import com.ping.vo.PageData;
import com.ping.vo.ItemSku;

/**
 * 商品Service
 * <p>Title: AdminItemService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月13日下午6:53:01
 * @version 1.0
 */
@Service
public class AdminItemService implements IAdminItemService {

	@Autowired
	private WxItemMapper itemMapper;
	@Autowired
	private WxItemDescMapper itemDescMapper;
	@Autowired
	private WxItemSpecMapper itemSpecMapper;
	@Autowired
	private WxItemSkuMapper itemSkuMapper;
	
	//新增商品
	@Override
	public WXResult addItem(WxItem item,WxItemDesc itemDesc,WxItemSpec itemSpec,String itemSkuList) {
		//取出最低价格
		List<ItemSku> jsonToList = JsonUtils.jsonToList(itemSkuList, ItemSku.class);
		Long lowPrice = 0L;
		for (ItemSku sku : jsonToList) {
			if(sku.getPrice()<lowPrice){
				lowPrice = sku.getPrice();
			}
		}
		
		//1 插入item表并返回item_id
		item.setLowPrice(lowPrice);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		item.setStatus((byte) 1);
		itemMapper.insert(item);
		
		//2 插入item_desc表
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
		//3 插入item_spec表并返回item_spec_id
		itemSpec.setItemId(item.getId());
		itemSpec.setCreated(new Date());
		itemSpec.setUpdated(new Date());
		itemSpecMapper.insert(itemSpec);
		
		//4 插入item_sku表
		
		for (ItemSku sku : jsonToList) {
			WxItemSku itemSku = new WxItemSku();
			itemSku.setItemId(item.getId());
			itemSku.setItemSpecId(itemSpec.getId());
			itemSku.setItemSpecValue(JsonUtils.objectToJson(sku.getItemSpecValue()));
			itemSku.setNum(sku.getNum());
			itemSku.setPrice(sku.getPrice());
			itemSku.setCreated(new Date());
			itemSku.setUpdated(new Date());
			itemSkuMapper.insert(itemSku);
		}
		return WXResult.ok();
	}
	
	//获取商品列表
	@Override
	public PageData itemList(Integer page, Integer rows) {
		//开启分页
		PageHelper.startPage(page, rows);
		WxItemExample example = new WxItemExample();
		List<WxItem> itemList = itemMapper.selectByExample(example);
		
		PageInfo<WxItem> pageInfo = new PageInfo<>(itemList);
		
		PageData<WxItem> pageData = new PageData<>();
		pageData.setTotal(pageInfo.getTotal());
		pageData.setRows(itemList);
		
		return pageData;
	}

	@Override
	public WXResult deleteItem(Long id) {
		//1 删除商品
		itemMapper.deleteByPrimaryKey(id);
		//2 删除商品描述
		WxItemDescExample descExample = new WxItemDescExample();
		com.ping.pojo.WxItemDescExample.Criteria descCriteria = descExample.createCriteria();
		descCriteria.andItemIdEqualTo(id);
		itemDescMapper.deleteByExample(descExample);
		//3 删除商品规格
		WxItemSpecExample specExample = new WxItemSpecExample();
		com.ping.pojo.WxItemSpecExample.Criteria specCriteria = specExample.createCriteria();
		specCriteria.andItemIdEqualTo(id);
		itemSpecMapper.deleteByExample(specExample);
		//4 删除商品SKU
		WxItemSkuExample skuExample = new WxItemSkuExample();
		com.ping.pojo.WxItemSkuExample.Criteria skuCriteria = skuExample.createCriteria();
		skuCriteria.andItemIdEqualTo(id);
		itemSkuMapper.deleteByExample(skuExample);
		return null;
	}

}
