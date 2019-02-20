package com.ping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.service.IWXPayService;
import com.ping.util.WXResult;
import com.ping.wxpay.WXPayConstants.SignType;

/**
* <p>Title: WeiXinController</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月25日 下午8:25:03
* @version 1.0
*/
@Controller
@RequestMapping("wx")
public class WXPayController {
	
	private static Logger log = Logger.getLogger(WXPayController.class);
	
	@Autowired
	IWXPayService weiXin;
	
	//微信登录接口
	@RequestMapping("/login")
	public String getAccess_token(@RequestParam(required = false)String code,
			@RequestParam(required = false)String state,
			Model model,HttpSession session
			){
		
		if(code!=null&&code!=""){
			try {
				Map<String, String> map = weiXin.getAccessTokenByCode(code);
				if(map.get("openid") != null){
					session.setAttribute("openid", map.get("openid"));
					model.addAttribute("openid", map.get("openid"));
					model.addAttribute("access_token", map.get("access_token"));
					model.addAttribute("jsapi_ticket", map.get("jsapi_ticket"));
				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		return "goods/goods_list";
	}
	
	//获取jssdk页面参数
	@RequestMapping("/wxconfig")
	@ResponseBody
	public WXResult getWXconfig(HttpServletRequest request, String url){
		try {
			WXResult result = weiXin.getWXconfig(url);
			return result;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return WXResult.build(500, "签名获取失败");
	}
	
	//统一下单
	@RequestMapping("/unifiedOrder")
	@ResponseBody
	public WXResult unifiedOrder(String goodId,HttpSession session){
		WXResult result = new WXResult();
		try {
			result = weiXin.unifiedOrder(goodId, session.getAttribute("openid").toString(), SignType.MD5);
		} catch (Exception e) {
			log.error(e);
			return WXResult.build(500, e.getMessage());
		}		
		return result;		
	}
	
	//支付回调通知
	@RequestMapping("/payNotify")
	public void payNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(request.getInputStream(), "UTF-8")
		    );
		String line = null;
	    StringBuffer buffer = new StringBuffer();
	    while ((line = rd.readLine()) != null) {
	        buffer.append(line);
	    }
	    rd.close();
	    WXResult result = weiXin.payNotify(buffer.toString());
		
		response.setHeader("Content-Type", "text/xml;charset=utf-8");
		response.getWriter().write(result.getData().toString());
	}
	
	//订单查询
	@RequestMapping("/orderQuery")
	@ResponseBody
	public WXResult orderQuery(String orderId){
		try {
			WXResult result = weiXin.orderQuery(orderId);
			Map<String,String> map = (Map<String, String>) result.getData();
			if(map.get("result_code").equals("SUCCESS")){
				return WXResult.ok(map.get("trade_state"));
			}
			return WXResult.build(500,map.get("err_code")+map.get("err_code_des"));
		} catch (Exception e) {
			log.error("查询出错{}",e);
			return WXResult.build(500, "查询出错"+e.getMessage());
		}
	}
	
	//微信测试接口
	@RequestMapping("/test")
	@ResponseBody
	public String debugTest(String info){
		int test = weiXin.test();
		
		return "测试成功"+test;
	}
}
