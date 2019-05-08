package com.wrlus.seciot.mobile.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wrlus.seciot.util.exception.FileUploadException;

@Controller
@RequestMapping("/appleios")
public class AppleiOSController {
	
	public File resolveUploadFile(MultipartHttpServletRequest multipartRequest, String path) throws FileUploadException {
		try {
			MultipartFile multipartFile = multipartRequest.getFile("file");
			new File(path).mkdirs();
			File targetFile = new File(path + multipartFile.getOriginalFilename());
			multipartFile.transferTo(targetFile);
			return targetFile;
		} catch (Exception e) {
			throw new FileUploadException(e);
		}
	}
	
	public void cleanUploadFile(String path) {
		new File(path).delete();
	}
}
