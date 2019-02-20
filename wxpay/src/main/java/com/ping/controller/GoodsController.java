package com.ping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* <p>Title: GoodsController</p>
* <p>Description:</p>
* @author  周运平
* @date    2018年10月29日 下午8:09:02
* @version 1.0
*/
@Controller
@RequestMapping("goods")
public class GoodsController {
	
	@RequestMapping("/list")
	public String List(){
		return "goods/goods_list";
	}

}
