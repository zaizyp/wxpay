package com.ping.service;

import java.io.IOException;
import java.text.ParseException;

import com.ping.util.WXResult;
import com.ping.vo.WXMessage;

/**
* <p>Title: WXMessage</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月31日 下午7:08:37
* @version 1.0
*/
public interface IWXMessageService {

	public WXResult sentMessage(WXMessage message) throws IOException, ParseException;
}
