package com.ping.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ping.exception.WxException;
import com.ping.pojo.WxUser;
import com.ping.service.IUserService;
import com.ping.service.IWXPayService;
import com.ping.service.IWxService;
import com.ping.util.WXConstant;

/**
 * 前台微信商品登录controller
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月28日下午1:19:00
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	IWxService wxService;
	@Autowired
	IUserService userService;
	
	//微信登录接口
	@RequestMapping()
	public String getAccess_token(@RequestParam(required = false)String code,
			@RequestParam(required = false)String state,
			Model model,HttpSession session
			) throws Exception{
		if(code!=null&&code!=""){
			//1、获取access_token
			Map<String, String> accessTokenMap = wxService.getAccessTokenByCode(code, WXConstant.snsapi_userinfo);
			if (accessTokenMap!=null) {
				//2、获取用户信息
				WxUser user = wxService.getUserInfoByAccessToken(accessTokenMap.get("access_token"), accessTokenMap.get("openid"));
				if (user!=null) {
					//3、更新或插入用户信息
					WxUser wxUser = userService.selectUser(user.getOpenid());
					if (wxUser!=null) {
						user.setUpdated(new Date());
						userService.updateUser(user, wxUser.getId());
					}else {
						user.setCreated(new Date());
						user.setUpdated(new Date());
						userService.addUser(user);
					}
					//3、保存登录状态
					session.setAttribute("user_id", user.getId());
					session.setAttribute("openid", accessTokenMap.get("openid"));
					return "redirect:index";
				}
			}
		}
		throw new WxException("登录失败");
	}
	
}
