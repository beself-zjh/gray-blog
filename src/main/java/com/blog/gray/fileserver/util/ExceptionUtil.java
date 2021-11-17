/** 
 * projectName: gray-blog 
 * fileName: ExceptionUtil.java 
 * packageName: com.blog.gray.fileserver.util 
 * date: Nov 17, 20217:53:11 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.util;

/**
 * @title: ExceptionUtil.java
 * @package com.blog.gray.fileserver.util
 * @description: TODO
 * @author: Zjh
 * @date: Nov 17, 2021 7:53:11 PM 
 * @version: V1.0   
 */
public class ExceptionUtil {

	/**
	 * @title: ExceptionUtil.java
	 * @package com.blog.gray.fileserver.util
	 * @description: 异常类型
	 */
	public enum ErrorCodeEnum {
		ERROR_FILETYPE, CANNOT_SAVE
	}

	/**
	 * @title: toException
	 * @description: 根据异常类型生成对应描述的运行时异常
	 * @param ErrorCodeEnum 异常类型
	 */
	public static RuntimeException toException(ErrorCodeEnum errorCode) {
		switch (errorCode) {
		case ERROR_FILETYPE:
			return new RuntimeException("错误的文件类型");
		case CANNOT_SAVE:
			return new RuntimeException("文件保存失败");
		default:
			return new RuntimeException();
		}
	}
}
