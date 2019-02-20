package com.ping.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ping.service.IPictureService;

/**
 * 
 * <p>Title: PictureController</p>
 * <p>Description: 图片相关控制类</p>
 * @author	周运平
 * @date	2018年11月6日下午11:06:11
 * @version 1.0
 */

@Controller
@RequestMapping("pic")
public class PictureController {

	@Autowired
	IPictureService picService;
	
	private static Logger Log = Logger.getLogger(PictureController.class);
	
	@RequestMapping("upload")
	@ResponseBody
	public String uploadPicture(HttpServletRequest request, MultipartFile uploadFile){
		String path = request.getRealPath("/");
		JsonObject json = new JsonObject();
		Log.info(path);
		try {
			String url = picService.uploadPicture(uploadFile, path);
			json.addProperty("error", 0);
			json.addProperty("url", request.getContextPath()+url);
		} catch (IOException e) {
			Log.error(e.getMessage());
			json.addProperty("error", 1);
			json.addProperty("message", "上传失败+++"+e.getMessage());
		}
		Log.info(json);
		return json.toString();
	}
	
}
