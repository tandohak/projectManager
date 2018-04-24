package com.dgit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.util.MediaUtils;
import com.dgit.util.UploadFileUtils;

@Controller 
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	private String innerUploadPath = "resources/upload";

	@Resource(name = "uploadPath")
	private String outUploadPath;

	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> displayFile(String filename) {
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		logger.info("[displayFile]:" + filename);

		try {
			String formatName = filename.substring(filename.lastIndexOf(".") + 1);
			MediaType type = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);

			in = new FileInputStream(outUploadPath + "/" + filename);

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadDrag", method = RequestMethod.POST)
	public ResponseEntity<List<String>> uploadDragResult(HttpServletRequest request, String test, List<MultipartFile> files) {
		logger.info("[uploadDrag] Result POST");
		logger.info(test);
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		File dirPath = new File(root_path + "/" + innerUploadPath);
		 
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		 
		ResponseEntity<List<String>> entity = null;
		List<String> list = new ArrayList();
		try {
			for (MultipartFile file : files) {
				UUID uid = UUID.randomUUID();// 중복방지를 위한 랜덤값 생성
				String savedName = uid.toString() + "_" + file.getOriginalFilename();
				File target = new File(root_path + "/" + innerUploadPath, savedName);

				FileCopyUtils.copy(file.getBytes(), target);
				list.add(innerUploadPath + "/" + savedName);
			} 
			entity = new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} catch (IOException e) {
			entity = new ResponseEntity<List<String>>(list, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return entity;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/uploadDrag", method = RequestMethod.POST)
	public ResponseEntity<List<String>> uploadDragResult(String test, List<MultipartFile> files) {
		logger.info("[uploadDrag] Result POST");
		logger.info(test);

		ResponseEntity<List<String>> entity = null;
		List<String> list = new ArrayList();
		try {
			for (MultipartFile file : files) {
				File dirPath = new File(outUploadPath);
				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}
				
				String savedName = UploadFileUtils.uploadFile(outUploadPath, file.getOriginalFilename(),
						file.getBytes());

				list.add(savedName);
			}
			entity = new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} catch (IOException e) {
			entity = new ResponseEntity<List<String>>(list, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return entity;
	}*/

	// deleteFile
	@ResponseBody
	@RequestMapping(value = "deleteFile", method = RequestMethod.GET)
	public ResponseEntity<String> deleteFile(String filename,HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		try {
			System.gc();
			String root_path = request.getSession().getServletContext().getRealPath("/");

			File file = new File(root_path + "/" + innerUploadPath + filename);
			File file2 = new File(root_path + "/" + innerUploadPath + filename.replaceFirst("s_", ""));
			file2.delete(); 
			file.delete();
			logger.info("[deleteFile] - original : " + filename.replaceFirst("s_", ""));

			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}

		return entity;
	}
}
