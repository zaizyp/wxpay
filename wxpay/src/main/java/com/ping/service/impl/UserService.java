package com.ping.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.mapper.WxUserMapper;
import com.ping.pojo.WxUser;
import com.ping.pojo.WxUserExample;
import com.ping.pojo.WxUserExample.Criteria;
import com.ping.service.IUserService;
import com.ping.util.WXResult;

/**
 * 商城用户
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月20日下午3:16:11
 * @version 1.0
 */
@Service
public class UserService implements IUserService{

	@Autowired
	private WxUserMapper userMapper;

	//根据userId查询用户信息
	@Override
	public WxUser selectUser(Long userId) throws Exception {
		return userMapper.selectByPrimaryKey(userId);
	}

	//根据openId查询用户信息
	@Override
	public WxUser selectUser(String openId) throws Exception {
		WxUserExample example = new WxUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openId);
		List<WxUser> list = userMapper.selectByExample(example);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	//新增用户
	@Override
	public WXResult addUser(WxUser user) throws Exception {
		if (user.getOpenid()!=null&&user.getOpenid().length()>0) {
			user.setCreated(new Date());
			user.setUpdated(new Date());
			int count = userMapper.insertSelective(user);
			if (count==1) {
				return WXResult.ok();
			}
		}
		return WXResult.build(500, "新增失败");
	}

	//根据userId更新用户信息
	@Override
	public WXResult updateUser(WxUser user,Long userId) throws Exception {
		user.setUpdated(new Date());
		user.setId(userId);
		user.setOpenid(null);//清除openId信息，防止用户修改openId
		int count = userMapper.updateByPrimaryKeySelective(user);
		if(count>0)
			return WXResult.ok();
		return WXResult.build(500, "更新失败");
	}
	
	//根据openId更新用户信息
	@Override
	public WXResult updateUser(WxUser user,String openId) throws Exception {
		WxUserExample example = new WxUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openId);
		user.setId(null);//清除userId信息，防止用户修改userId
		int count = userMapper.updateByExampleSelective(user, example);
		if(count>0)
			return WXResult.ok();
		return WXResult.build(500, "更新失败");
	}

	//根据userId删除用户
	public WXResult deleteUser(Long userId) throws Exception{
		int count = userMapper.deleteByPrimaryKey(userId);
		if (count>0)
			return WXResult.ok();
		return WXResult.build(500, "删除失败");
	}

	//根据openId删除用户
	@Override
	public WXResult deleteUser(String openId) throws Exception {
		WxUserExample example = new WxUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openId);
		int count = userMapper.deleteByExample(example);
		if (count>0)
			return WXResult.ok();
		return WXResult.build(500, "删除失败");
	}

}
