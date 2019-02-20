package com.ping.util;

import java.util.Random;

/**
 * 公共工具类
 * <p>Title: CommonUtils</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月6日下午9:32:58
 * @version 1.0
 */
public class CommonUtils {

	/**
	 * 图片名称生成工具
	 * <p>Title: genImageName</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static String getImageName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		
		return str;
	}

}
