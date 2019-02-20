package com.ping.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 订单id生成工具
 * <p>Title: OrderIdUtils</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月30日下午2:44:39
 * @version 1.0
 */
public class OrderIdUtils {

    private static final Random RANDOM = new SecureRandom();
	
	public static String getOrderId(){
	
		String orderId = "";
		//13位的时间戳
		Long currentTimeMillis = System.currentTimeMillis();
		
		//7位的随机数
		Integer nextInt = RANDOM.nextInt(10000000);
		String str = nextInt.toString();
		if (str.length()<7) {
			for(int i = 0; i < (7-str.length()); i++)
				str = "0"+str;
		}
		orderId = currentTimeMillis.toString()+str;
		
		return orderId;
	}
	
	public static String getOutTradeNo(){
		
		String outTradeNo = "out";
		//13位的时间戳
		Long currentTimeMillis = System.currentTimeMillis();
		
		//7位的随机数
		Integer nextInt = RANDOM.nextInt(10000000);
		String str = nextInt.toString();
		if (str.length()<7) {
			for(int i = 0; i < (7-str.length()); i++)
				str = "0"+str;
		}
		outTradeNo += currentTimeMillis.toString()+str;
		
		return outTradeNo;
	}
}
