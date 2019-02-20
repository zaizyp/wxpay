package com.ping.controller;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.service.IWXMessageService;
import com.ping.util.WXResult;
import com.ping.vo.WXMessage;

/**
* <p>Title: WXMessageController</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年11月1日 下午5:21:37
* @version 1.0
*/
@Controller
@RequestMapping("message")
public class WXMessageController {
	
	private static Logger log = Logger.getLogger(WXMessageController.class);
	
	@Autowired
	private IWXMessageService messageService;
	
	@RequestMapping("sent")
	@ResponseBody
	public WXResult sent(){
		
		WXMessage message = new WXMessage();
		message.setAppId(null);
		message.setPagePath(null);
		message.setTemplateId("XN0LtyspkdIkmoIBbZISNHohu0WM4zbSCwk7tjokXlg");
		message.setToUser("orajBwVMxNr7IjFjKv-C1_56iOAI");
		message.setUrl("www.baidu.com");
		String[] data = {"您好，您有新订单。","核桃1斤","2014年7月21日 18:36","嘉辰时代公寓","18539271688","已付款","开心 甜蜜 健康 富足"};
		String[] color = {"#173177","#173177","#173177","#173177","#173177","#173177","#173177"};
		message.setMessage(data);
		message.setColor(color);
		log.info("模版消息");
		try {
			WXResult result = messageService.sentMessage(message);
			return result;
		} catch (IOException | ParseException e) {
			log.info(e);
			return WXResult.build(500, e.getMessage());
		}
	}

}
