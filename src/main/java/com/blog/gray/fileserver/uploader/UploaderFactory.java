/** 
 * projectName: gray-blog 
 * fileName: UploaderFactory.java 
 * packageName: com.blog.gray.fileserver.uploader 
 * date: Nov 17, 20214:57:15 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.fileserver.config.FileUploadConfig;

/**
 * @title: UploaderFactory.java
 * @package com.blog.gray.fileserver.uploader
 * @description: 文件上传器工厂
 * @author: Zjh
 * @date: Nov 17, 2021 4:57:15 PM 
 * @version: V1.0   
 */
@Service
public class UploaderFactory {

	@Autowired
	private FileUploadConfig fileUploadConfig;
	
	private volatile ImageUploader imageUploader;
	
	/**
	 *@title: getImageUploader 
	 *@description: 获取图片上传器
	 *@return 图片上传器实例
	 */
	public synchronized ImageUploader getImageUploader( ) {
		if (imageUploader == null) {
			imageUploader = new ImageUploader(fileUploadConfig);
		}
		
		return imageUploader;
	}
}
