package com.ping.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.mapper.WxDeliveryAddressMapper;
import com.ping.pojo.WxDeliveryAddress;
import com.ping.pojo.WxDeliveryAddressExample;
import com.ping.pojo.WxDeliveryAddressExample.Criteria;
import com.ping.util.WXResult;

/**
 * 用户收货地址
 * <p>Title: AddressService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月20日下午3:26:32
 * @version 1.0
 */
@Service
public class AddressService implements IAddressService {

	@Autowired
	private WxDeliveryAddressMapper addressMapper;
	
	//查询用户收货地址列表
	@Override
	public List<WxDeliveryAddress> getAddressList(Long userId) {
		WxDeliveryAddressExample example = new WxDeliveryAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<WxDeliveryAddress> list = addressMapper.selectByExample(example);
		return list;
	}

	//查询用户默认收货地址
	@Override
	public WxDeliveryAddress getDefaultAddress(Long userId) {
		WxDeliveryAddressExample example = new WxDeliveryAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDefaultEqualTo(true);
		List<WxDeliveryAddress> list = addressMapper.selectByExample(example);
		return list.get(0);
	}
	//查询用户收货地址
	@Override
	public WxDeliveryAddress getAddress(Long addressId,Long userId){
		WxDeliveryAddressExample example = new WxDeliveryAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIdEqualTo(addressId);
		List<WxDeliveryAddress> list = addressMapper.selectByExample(example);
		if(list.size()==0)
			return null;
		return list.get(0);
	}
	
	//添加收货地址
	@Override
	public WXResult addAddress(WxDeliveryAddress address, Long userId) {
		address.setUserId(userId);
		address.setCreated(new Date());
		address.setUpdated(new Date());
		//判断是否设置为默认地址，如果是则需更改原来的默认地址
		if(address.getIsDefault()){
			WxDeliveryAddressExample example = new WxDeliveryAddressExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			criteria.andIsDefaultEqualTo(true);
			WxDeliveryAddress record = new WxDeliveryAddress();
			record.setIsDefault(false);
			addressMapper.updateByExampleSelective(record, example);
		}
		addressMapper.insertSelective(address);
		return WXResult.ok();
	}

	//更新收货地址
	@Override
	public WXResult updateAddress(WxDeliveryAddress address, Long userId) {
		//创建更新条件
		WxDeliveryAddressExample example = new WxDeliveryAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(address.getId());
		criteria.andUserIdEqualTo(userId);
		//设置用户id
		address.setUserId(userId);
		//判断是否设置为默认地址，如果是则需更改原来的默认地址
		if(address.getIsDefault()){
			WxDeliveryAddressExample example2 = new WxDeliveryAddressExample();
			Criteria criteria2 = example2.createCriteria();
			criteria2.andUserIdEqualTo(userId);
			criteria2.andIsDefaultEqualTo(true);
			WxDeliveryAddress record = new WxDeliveryAddress();
			record.setIsDefault(false);
			addressMapper.updateByExampleSelective(record, example2);
		}
		int result = addressMapper.updateByExample(address, example);
		if(result>0)
			return WXResult.ok();
		return WXResult.build(500, "操作失败");
	}

	//删除收货地址
	@Override
	public WXResult deleteAddress(Long addressId, Long userId) {
		WxDeliveryAddressExample example = new WxDeliveryAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(addressId);
		criteria.andUserIdEqualTo(userId);
		int result = addressMapper.deleteByExample(example);
		if(result>0)
			return WXResult.ok();
		return WXResult.build(500, "删除失败");
	}

}
