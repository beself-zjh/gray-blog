/** 
 * projectName: gray-blog 
 * fileName: FileController.java 
 * packageName: com.blog.gray.fileserver.controller 
 * date: Nov 17, 20213:30:41 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blog.gray.fileserver.config.WebConfigurer;
import com.blog.gray.fileserver.uploader.Uploader;
import com.blog.gray.fileserver.uploader.UploaderFactory;

/**
 * @title: FileController.java
 * @package com.blog.gray.fileserver.controller
 * @description: 文件上传与访问
 * @author: Zjh
 * @date: Nov 17, 2021 3:30:41 PM 
 * @version: V1.0   
 */
@Controller
public class FileUploadController {
	
	@Autowired
	private WebConfigurer webConfigurer;
	
	@Autowired
	private UploaderFactory uploaderFactory;
	
	@RequestMapping(path = "/admin/upload/image", method = RequestMethod.POST)
	public void uploadImageHandler(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "editormd-image-file", required = true) MultipartFile attach) {
		try {
			request.setCharacterEncoding( "utf-8" );
		    response.setHeader( "Content-Type" , "text/html" );
		    
		    Uploader uploader = uploaderFactory.getImageUploader();
		    String filename = uploader.upload(attach);
		        
		    // 下面response返回的json格式是editor.md所限制的，规范输出就OK
		    response.getWriter().write( "{\"success\": 1," 
		    						  + "\"message\":\"successfully upload\","
		    		                  + "\"url\":\"" + webConfigurer.virtualImagePath + filename + "\"}" );
		} catch(Exception e) {
			try {
				response.getWriter().write( "{\"success\": 0, \"message\":\"" + e.getMessage() + "\"");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}
