package com.ping.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.service.IWxService;
import com.ping.util.WXResult;

/**
 * 微信公众号相关Controller
 * <p>Title: WxController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月28日下午4:59:40
 * @version 1.0
 */
@Controller
@RequestMapping("wx")
public class WxController {

	private static Logger log = Logger.getLogger(WxController.class);
	
	@Autowired
	private IWxService wxService;
	
	//获取使用JSSDK页面所需的WXConfig参数
	@RequestMapping("/config")
	@ResponseBody
	public WXResult getWxConfig(HttpServletRequest request) throws Exception{
		log.info("进入wx/config");
		// 1 配置页面wxconfig参数
		String url = request.getRequestURL().toString();
		String[] split = url.split("#");// 去除URL后面的#部分
		WXResult wxconfig = wxService.getWXconfig(url);
		return wxconfig;
	}
	
}
