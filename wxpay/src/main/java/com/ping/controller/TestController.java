package com.ping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.ping.redis.RedisUtil;
import com.ping.vo.ItemSpecValue;

/**
 * 测试类
 * <p>Title: TestController</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月29日下午7:46:44
 * @version 1.0
 */
@Controller
@RequestMapping("test")
public class TestController {

	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping("redis")
	@ResponseBody
	public String redisTest(){
		
		//redisUtil.setString("kk", "vv", 20);
//		String str = redisUtil.getString("kk");
//		Long expire = redisUtil.getExpire("kk");
		
//		Map<String, String> map = new HashMap<>();
//		map.put("name", "小刘");
//		map.put("age", "23");
////		redisUtil.setMap("map", map);
//		List<Map<String, String>> list = new ArrayList<>();
//		list.add(map);
//		redisUtil.setList("list", list);
////		Map map2 = redisUtil.getMap("map");
//		List<Map<String, String>> list2 = redisUtil.getList("list");
		
		//测试存储带有对象的map
		Map<String, ItemSpecValue> map = new HashMap<>();
		ItemSpecValue value = new ItemSpecValue();
		value.setName("cc");
		value.setValue("dd223");
		map.put("key", value);
		redisUtil.setMap("mapkey1", map);
		
		
		Map<String,ItemSpecValue> map2 = redisUtil.getMap("mapkey1");
		ItemSpecValue itemSpecValue = map2.get("key");
		
		return itemSpecValue.getName()+itemSpecValue.getValue();
	}
	
	@RequestMapping("/springMVC")
	public ModelAndView springmvcTest(ModelAndView modelAndView) {
		modelAndView.addObject("aa", "ss");
		modelAndView.getModel();
		modelAndView.setViewName("test");
		View view = new View() {
			
			@Override
			public void render(Map<String, ?> arg0, HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
			}
			
			@Override
			public String getContentType() {
				
				return null;
			}
		};
		modelAndView.setView(view);
		return modelAndView;
	}
	@RequestMapping("taglib")
	public String taglib(){
		
		return "";
	}
	
}
