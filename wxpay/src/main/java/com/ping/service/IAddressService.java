package com.ping.service;

import java.util.List;

import com.ping.pojo.WxDeliveryAddress;
import com.ping.util.WXResult;

/**
 * 收货地址Service
 * <p>Title: IAddressService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月20日下午3:18:27
 * @version 1.0
 */
public interface IAddressService {

	/**
	 * 查询收货地址列表
	 * <p>Title: addressList</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @return
	 */
	public List<WxDeliveryAddress> getAddressList(Long userId);
	
	/**
	 * 查询用户默认的收货地址
	 * <p>Title: defaultAddress</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @return
	 */
	public WxDeliveryAddress getDefaultAddress(Long userId);
	
	/**
	 * 根据id查询收货地址
	 * <p>Title: getAddress</p>
	 * <p>Description: </p>
	 * @param addressId 收货地址id
	 * @param userId 用户id
	 * @return
	 */
	public WxDeliveryAddress getAddress(Long addressId,Long userId);
	
	/**
	 * 添加收货地址
	 * <p>Title: addAddress</p>
	 * <p>Description: </p>
	 * @param address 收货地址
	 * @param userId 用户id
	 * @return
	 */
	public WXResult addAddress(WxDeliveryAddress address,Long userId);
	
	/**
	 * 更新收货地址
	 * <p>Title: updateAddress</p>
	 * <p>Description: </p>
	 * @param address 收货地址
	 * @param userId 用户id
	 * @return
	 */
	public WXResult updateAddress(WxDeliveryAddress address,Long userId);
	
	/**
	 * 删除收货地址
	 * <p>Title: deleteAddress</p>
	 * <p>Description: </p>
	 * @param addressId 收货地址id
	 * @param userId 用户id
	 * @return
	 */
	public WXResult deleteAddress(Long addressId,Long userId);
	
}
