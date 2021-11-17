/** 
 * projectName: gray-blog 
 * fileName: Uploader.java 
 * packageName: com.blog.gray.fileserver.uploader 
 * date: Nov 17, 20214:52:14 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.uploader;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @title: Uploader.java
 * @package com.blog.gray.fileserver.uploader
 * @description: 文件上传接口
 * @author: Zjh
 * @date: Nov 17, 2021 4:52:14 PM 
 * @version: V1.0   
 */
public interface Uploader {
	
	/**
	 *@title: upload 
	 *@description: 上传文件
	 *@param file 文件
	 */
	public String upload(MultipartFile file);
	
	/**
	 *@title: upload 
	 *@description: 上传多个文件
	 *@param files 文件数组
	 */
	public List<String> upload(MultipartFile[] files);
	
}
