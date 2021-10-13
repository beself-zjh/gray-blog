/** 
 * projectName: blog 
 * fileName: FileReadService.java 
 * packageName: com.example.blog.service 
 * date: Sep 10, 202011:21:58 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service;

/**
 * @title: FileReadService.java
 * @package com.example.blog.service
 * @description: get kinds of file content.
 * @author: Zjh
 * @date: Sep 10, 2020 11:21:58 AM 
 * @version: V1.0   
 */
public interface FileReadService {

	
	/**
	 * @title: mdFileRead
	 * @description: read md file.
	 * @param path: md file path
	 * @return String - md file content
	 */
	public String mdFileRead(String path);
	
}
