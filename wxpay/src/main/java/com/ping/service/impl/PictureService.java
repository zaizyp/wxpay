package com.ping.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ping.service.IPictureService;
import com.ping.util.CommonUtils;

@Service
public class PictureService implements IPictureService {

	@Override
	public String uploadPicture(MultipartFile uploadFile,String imgPath) throws IOException {
		if (uploadFile.isEmpty()) 
			return null;
		//生成存放路径
		String filePath = "upload/"
				+ new SimpleDateFormat("yyyy").format(new Date())
				+ "/" + new SimpleDateFormat("MM").format(new Date())
				+ "/" + new SimpleDateFormat("dd").format(new Date());
		String originalFilename = uploadFile.getOriginalFilename();
		String newFileName = CommonUtils.getImageName()+
				originalFilename.substring(originalFilename.lastIndexOf("."));
		
		File fileDir = new File(imgPath+filePath);
		if(!fileDir.exists()) fileDir.mkdirs();
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imgPath+filePath+"/"+newFileName));
		BufferedInputStream bis = new BufferedInputStream(uploadFile.getInputStream());
		byte[] buf = new byte[1024];
		while(bis.read(buf)!=-1){
			bos.write(buf);
			bos.flush();
		}
		bos.close();
		bis.close();
		
		return "/"+filePath+"/"+newFileName;
	}

}
