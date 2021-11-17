/** 
 * projectName: gray-blog 
 * fileName: FileUtil.java 
 * packageName: com.blog.gray.fileserver.util 
 * date: Nov 17, 20214:11:10 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.util;

import java.util.UUID;

/**
 * @title: FileUtil.java
 * @package com.blog.gray.fileserver.util
 * @description: 文件工具类
 * @author: Zjh
 * @date: Nov 17, 2021 4:11:10 PM 
 * @version: V1.0   
 */
public class FileUtil {

	/**
	 *@title: renameToUUID 
	 *@description: 创建唯一文件名
	 *@param filename 文件名
	 */
	public static String renameToUUID(String filename) {
		return UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
	}
	
}
