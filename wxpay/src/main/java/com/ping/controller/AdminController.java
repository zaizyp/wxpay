package com.ping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* <p>Title: AdminController</p>
* <p>Description:后台界面</p>
* @author  周运平
* @date    2018年11月4日 下午2:14:24
* @version 1.0
*/
@Controller
@RequestMapping("admin")
public class AdminController {
	
	/**
	 * 后台页面
	 * <p>Title: page</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page){
		return "admin/"+page;
	}
	
}
