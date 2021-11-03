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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.blog.gray.service.FileService;

/**
 * @title: FileServiceImpl.java
 * @package com.example.blog.service.impl
 * @description: 文件相关服务
 * @author: Zjh
 * @date: Sep 10, 2020 11:23:45 AM
 * @version: V1.0
 */
@Service
public class FileServiceImpl implements FileService {

	/**
	 * @title: mdFileRead
	 * @description: 读取markdown文件
	 * @param path: md file path
	 * @return String - 文件内容
	 * @see com.FileService.blog.service.FileReadService#mdFileRead(java.lang.String)
	 */
	@Override
	public String mdFileRead(String path) {
		// TODO 代码简洁
		String content = new String();
		try {
			File markdownFile = new File(path);
			if (!markdownFile.exists()) {
				return content;
			}
			FileInputStream inputStream = new FileInputStream(markdownFile);
			int length = inputStream.available();
			byte buffer[] = new byte[length];
			inputStream.read(buffer);
			inputStream.close();
			content = new String(buffer, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();// TODO log
		}
		return content;
	}

	/**   
	 * @title: mdFileSave
	 * @description: 保存markdown文件
	 * @param path   
	 * @see com.blog.gray.service.FileService#mdFileSave(java.lang.String)     
	 */
	@Override
	public boolean mdFileSave(String path, String content) {
		File file = new File(path);
        
		try {
			if (!file.exists()) {  
	        	file.createNewFile();  
	        }  
	        byte[] bytes = new byte[512];   
	        bytes = content.getBytes();  
	        int len = bytes.length;   // 字节的长度，不是字符串的长度
	        FileOutputStream fos = new FileOutputStream(file); 
	        fos.write(bytes, 0, len); 
	        fos.close();
		} catch (IOException e) {
			return false;
		} 
        return true;
	}

}
