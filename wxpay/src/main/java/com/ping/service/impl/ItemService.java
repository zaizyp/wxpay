package com.ping.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.ping.mapper.WxItemDescMapper;
import com.ping.mapper.WxItemMapper;
import com.ping.mapper.WxItemSkuMapper;
import com.ping.mapper.WxItemSpecMapper;
import com.ping.mapper.WxShopMapper;
import com.ping.pojo.WxItem;
import com.ping.pojo.WxItemDesc;
import com.ping.pojo.WxItemDescExample;
import com.ping.pojo.WxItemExample;
import com.ping.pojo.WxItemExample.Criteria;
import com.ping.pojo.WxItemSku;
import com.ping.pojo.WxItemSkuExample;
import com.ping.pojo.WxItemSpec;
import com.ping.pojo.WxItemSpecExample;
import com.ping.pojo.WxShop;
import com.ping.service.IItemService;
import com.ping.util.JsonUtils;
import com.ping.vo.ItemSku;
import com.ping.vo.ItemSkuVo;
import com.ping.vo.ItemSpecValue;
import com.ping.vo.ItemVo;

/**
 * 前台商品Service实现类
 * <p>Title: ItemService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月15日下午1:26:07
 * @version 1.0
 */
@Service
public class ItemService implements IItemService {

	@Autowired
	private WxItemMapper itemMapper;
	@Autowired
	private WxItemDescMapper itemDescMapper;
	@Autowired
	private WxItemSpecMapper itemSpecMapper;
	@Autowired
	private WxItemSkuMapper itemSkuMapper;
	@Autowired
	private WxShopMapper shopMapper;
	
	//根据分类id查询商品列表
	@Override
	public List<WxItem> itemList(Long cid) {
		//创建查询条件
		WxItemExample example = new WxItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid);		
		List<WxItem> list = itemMapper.selectByExample(example);
		
		return list;
	}

	//根据商品商品id查询信息
	@Override
	public ItemVo selectItem(Long id) {
		//1 查询商品信息
		WxItem item = itemMapper.selectByPrimaryKey(id);
		ItemVo itemVo = new ItemVo();
		BeanUtils.copyProperties(item, itemVo);
		
		//2 查询商品详情
		WxItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
		itemVo.setItemDesc(itemDesc);
		
		//3 查询商品规格
		WxItemSpecExample specExample = new WxItemSpecExample();
		com.ping.pojo.WxItemSpecExample.Criteria specCriteria = specExample.createCriteria();
		specCriteria.andItemIdEqualTo(id);
		List<WxItemSpec> itemSpecs = itemSpecMapper.selectByExample(specExample);
		itemVo.setItemSpec(itemSpecs.get(0));
		
		//4 查询商品SKU
		
		WxItemSkuExample skuExample = new WxItemSkuExample();
		com.ping.pojo.WxItemSkuExample.Criteria skuCriteria = skuExample.createCriteria();
		skuCriteria.andItemIdEqualTo(id);
		List<WxItemSku> itemSkus = itemSkuMapper.selectByExample(skuExample);
		List<ItemSku> itemSkuList = new ArrayList<>();
		for(WxItemSku wxSku: itemSkus){
			ItemSku itemSku = new ItemSku();
			itemSku.setId(wxSku.getId());
			itemSku.setItemId(wxSku.getItemId());
			itemSku.setImage(wxSku.getImage());
			itemSku.setNum(wxSku.getNum());
			itemSku.setPrice(wxSku.getPrice());
			itemSku.setItemSpecValue(JsonUtils.jsonToList(wxSku.getItemSpecValue(), ItemSpecValue.class));
			itemSkuList.add(itemSku);
		}
		itemVo.setItemSkuList(itemSkuList);
		
		return itemVo;
	}

	//根据SkuId查询商品信息
	@Override
	public ItemSkuVo selectItemBySkuId(Long id) {
		//查询sku信息
		WxItemSku wxItemSku = itemSkuMapper.selectByPrimaryKey(id);
		ItemSkuVo itemSkuVo = new ItemSkuVo();
		BeanUtils.copyProperties(wxItemSku, itemSkuVo);
		itemSkuVo.setItemSpecValueList(JsonUtils.jsonToList(itemSkuVo.getItemSpecValue(), ItemSpecValue.class));
		itemSkuVo.setItemSpecValue(null);
		//查询商品信息
		WxItem item = itemMapper.selectByPrimaryKey(itemSkuVo.getItemId());
		itemSkuVo.setItem(item);
		//查询商品店铺信息
		WxShop shop = shopMapper.selectByPrimaryKey(item.getShopId());
		itemSkuVo.setShop(shop);
		
		return itemSkuVo;
	}
	
}	
