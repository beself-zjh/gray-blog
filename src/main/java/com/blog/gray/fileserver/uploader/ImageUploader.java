/** 
 * projectName: gray-blog 
 * fileName: ImageUploader.java 
 * packageName: com.blog.gray.fileserver.uploader 
 * date: Nov 17, 20214:55:52 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.uploader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.blog.gray.fileserver.config.FileUploadConfig;

/**
 * @title: ImageUploader.java
 * @package com.blog.gray.fileserver.uploader
 * @description: 图片文件上传器
 * @author: Zjh
 * @date: Nov 17, 2021 4:55:52 PM 
 * @version: V1.0   
 */
public class ImageUploader extends AbstractUploader {

	private FileUploadConfig fileUploadConfig;
	
	public ImageUploader(FileUploadConfig fileUploadConfig) {
		super();
		this.fileUploadConfig = fileUploadConfig;
	}
	
	/**   
	 * @title: checkFileFormat
	 * @description: 检查文件格式
	 * @param file 文件
	 * @return true:合法  false:不合法
	 * @see com.blog.gray.fileserver.uploader.AbstractUploader#checkFileFormat(org.springframework.web.multipart.MultipartFile)     
	 */
	@Override
	protected boolean checkFileFormat(MultipartFile file) {
		String filename = file.getOriginalFilename();
		Integer splitIndex = filename.lastIndexOf(".");
		if (splitIndex == filename.length() - 1)
			return false;
		String postfix = filename.substring(splitIndex + 1);
		return getAllowedFileTypes().contains(postfix.toLowerCase());
	}

	/**   
	 * @title: getRootPath
	 * @description: 获取文件保存目录
	 * @return 路径
	 * @see com.blog.gray.fileserver.uploader.AbstractUploader#getRootPath()     
	 */
	@Override
	protected String getRootPath() {
		return fileUploadConfig.getImgUploadPath();
	}
	
	/**
	 *@title: getAllowedFileTypes 
	 *@description: 获取合法文件类型
	 *@return 合法文件类型集合
	 */
	private Set<String> getAllowedFileTypes() {
		return new HashSet<String>(Arrays.asList("jpg", "png"));
	}

}
