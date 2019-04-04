package com.wrlus.seciot.fw.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wrlus.seciot.util.Status;

@Controller
@RequestMapping("/fw")
public class FwController {

	@RequestMapping("/analysis")
	public Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
			path = path.replace("file:", "");
			path = path.replace("WEB-INF/classes/", "attach/uploads/firmware"+UUID.randomUUID().toString().toUpperCase()+"/");
			File targetFile = this.resolveUploadFile((MultipartHttpServletRequest) request, path);
			data.put("Status", Status.SUCCESS);
		} catch (NullPointerException e) {
			data.put("Status", Status.FILE_UPD_ERROR);
			data.put("reason", "文件上传失败，错误代码："+Status.FILE_UPD_ERROR);
			e.printStackTrace();
		} catch (IOException e) {
			data.put("Status", Status.FILE_UPD_ERROR);
			data.put("reason", "上传路径读写失败，错误代码："+Status.FILE_UPD_ERROR);
			e.printStackTrace();
		}
		return data;
	}
	
	private File resolveUploadFile(MultipartHttpServletRequest multipartRequest, String path) throws NullPointerException, IOException{
		MultipartFile multipartFile = multipartRequest.getFile("file");
		new File(path).mkdirs();
		File targetFile = new File(path + multipartFile.getOriginalFilename());
		multipartFile.transferTo(targetFile);
		return targetFile;
	}

}
