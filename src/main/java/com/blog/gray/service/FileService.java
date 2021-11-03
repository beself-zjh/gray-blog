/** 
 * projectName: blog 
 * fileName: FileReadService.java 
 * packageName: com.example.blog.service 
 * date: Sep 10, 202011:21:58 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service;

/**
 * @title: FileService.java
 * @package com.example.blog.service
 * @description: 文件相关服务
 * @author: Zjh
 * @date: Sep 10, 2020 11:21:58 AM
 * @version: V1.0
 */
public interface FileService {

	/**
	 * @title: mdFileRead
	 * @description: 读取markdown文件
	 * @param path 文件路径
	 * @return String 文件内容
	 */
	public String mdFileRead(String path);

	/**
	 * @title: mdFileSave
	 * @description: 保存markdown文件
	 * @param path 文件路径
	 */
	public boolean mdFileSave(String path, String content);
	
}
