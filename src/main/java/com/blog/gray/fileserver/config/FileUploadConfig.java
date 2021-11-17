/** 
 * projectName: gray-blog 
 * fileName: FileUploadConfig.java 
 * packageName: com.blog.gray.fileserver.config 
 * date: Nov 17, 20213:33:34 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @title: FileUploadConfig.java
 * @package com.blog.gray.fileserver.config
 * @description: 文件服务器配置
 * @author: Zjh
 * @date: Nov 17, 2021 3:33:34 PM 
 * @version: V1.0   
 */
@ConfigurationProperties(prefix = "fileserver")
public class FileUploadConfig {
	
	/**
	 * @Fields imgUploadPath : 图片上传路径
	 */
	private String imgUploadPath;

	public String getImgUploadPath() {
		return imgUploadPath;
	}

	public void setImgUploadPath(String imgUploadPath) {
		this.imgUploadPath = imgUploadPath;
	}
}
