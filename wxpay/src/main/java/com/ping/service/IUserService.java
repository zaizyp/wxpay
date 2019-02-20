package com.ping.service;

import com.ping.pojo.WxUser;
import com.ping.util.WXResult;

/**
 * 商城用户
 * <p>Title: IUserService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月20日下午3:16:44
 * @version 1.0
 */
public interface IUserService{
	
	/**
	 * 根据id获取用户信息
	 * <p>Title: selectUser</p>
	 * <p>Description: </p>
	 * @param userId 用户id
	 * @return
	 */
	public WxUser selectUser(Long userId) throws Exception;
	
	/**
	 * 根据openId获取用户信息
	 * <p>Title: selectUser</p>
	 * <p>Description: </p>
	 * @param openId openId
	 * @return
	 */
	public WxUser selectUser(String openId) throws Exception;
	
	/**
	 * 新增用户
	 * <p>Title: addUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @return
	 */
	public WXResult addUser(WxUser user) throws Exception;
	
	/**
	 * 根据userId更新用户信息
	 * <p>Title: updateUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @param userId
	 * @return
	 */
	public WXResult updateUser(WxUser user, Long userId) throws Exception;
	
	/**
	 * 根据openId更新用户信息
	 * <p>Title: updateUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @param openId
	 * @return
	 */
	public WXResult updateUser(WxUser user, String openId) throws Exception;
	
	/**
	 * 根据userId删除用户
	 * <p>Title: deleteUser</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 */
	public WXResult deleteUser(Long userId) throws Exception;
	
	/**
	 * 根据openId删除用户
	 * <p>Title: deleteUser</p>
	 * <p>Description: </p>
	 * @param openID
	 * @return
	 * @throws Exception
	 */
	public WXResult deleteUser(String openID) throws Exception;
}
