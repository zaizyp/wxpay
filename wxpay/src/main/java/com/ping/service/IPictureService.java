package com.ping.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传组件
 * <p>Title: IPictureService</p>
 * <p>Description: </p>
 * @author	周运平
 * @date	2018年11月6日下午4:48:06
 * @version 1.0
 */
public interface IPictureService {
	
	public String uploadPicture(MultipartFile uploadFile,String imgPath) throws IOException;
}
