/** 
 * projectName: blog 
 * fileName: FileReadServiceImpl.java 
 * packageName: com.example.blog.service.impl 
 * date: Sep 10, 202011:23:45 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service.impl;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.blog.gray.service.FileReadService;

/**
 * @title: FileReadServiceImpl.java
 * @package com.example.blog.service.impl
 * @description: get kinds of file content.
 * @author: Zjh
 * @date: Sep 10, 2020 11:23:45 AM
 * @version: V1.0
 */
@Service
public class FileReadServiceImpl implements FileReadService {

	/**
	 * @title: mdFileRead
	 * @description: read md file.
	 * @param path: md file path
	 * @return String - md file content
	 * @see com.example.blog.service.FileReadService#mdFileRead(java.lang.String)
	 */
	@Override
	public String mdFileRead(String path) {
		//TODO 代码简洁
		String content = new String();
		try {
			File markdownFile = new File(path);
		    if(!markdownFile.exists()){
		        return null;
		    }
		    FileInputStream inputStream = new FileInputStream(markdownFile);
		    int length = inputStream.available();
		    byte buffer[] = new byte[length];
		    inputStream.read(buffer);
		    inputStream.close();
		    content =new String(buffer, "utf-8");
		} catch(Exception e) {
			e.printStackTrace();//TODO log
		}
		return content ;
	}

}
