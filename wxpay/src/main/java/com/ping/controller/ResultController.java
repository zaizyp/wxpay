package com.ping.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
* <p>Title: ResultController</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月29日 下午8:51:06
* @version 1.0
*/
@Controller
public class ResultController {
	
	private static Logger log = Logger.getLogger(ResultController.class);
	
	@RequestMapping("/wx")
	public void resWX(String signature,String timestamp,
		String nonce,String echostr,HttpServletResponse response) throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		//map.put("nonce", nonce);
		//map.put("timestamp", timestamp);
		//map.put("token", "TOKEN");
		//String sign = JSSign.sign(map);
		//if(sign.equals(signature)){
			log.info("微信配置成功："+echostr);
			response.getWriter().write(echostr);
		//}
		//response.getWriter().write("错误"+sign);
	}
	
}
