package com.ping.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ping.service.IWXMessageService;
import com.ping.util.HttpClient;
import com.ping.util.JsonUtils;
import com.ping.util.WXResult;
import com.ping.vo.AccessToken;
import com.ping.vo.WXMessage;

/**
* <p>Title: WXMessageService</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月31日 下午11:49:08
* @version 1.0
*/
@Service
public class WXMessageService implements IWXMessageService {

	private static Logger log = Logger.getLogger(WXMessageService.class);
	
	@Override
	public WXResult sentMessage(WXMessage message) throws IOException, ParseException {
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+AccessToken.getAccessToken().getToken();
		HttpClient client =new HttpClient(url);
		client.setHttps(true);
		String json = JsonUtils.getTemplateJson(message);
		log.info(json);
		client.setJsonParam(json);
		client.post();
		String content = client.getContent();
		Map<String,String> result = JsonUtils.jsonToPojo(content, Map.class);
		log.info(result);
		if(result.get("errmsg").equals("ok"))
			return WXResult.ok();
		return WXResult.build(500, result.get("errmsg"));
	}

}
