/** 
 * projectName: gray-blog 
 * fileName: ViewCodeUtil.java 
 * packageName: com.blog.gray.util 
 * date: Oct 16, 20213:50:44 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.util;

/**
 * @title: ViewCodeUtil.java
 * @package com.blog.gray.util
 * @description: 异常创建工具
 * @author: Zjh
 * @date: Oct 16, 2021 3:50:44 PM 
 * @version: V1.0   
 */
public class ViewCodeUtil {

	/**
	 * @title: ViewCodeUtil.java
	 * @package com.blog.gray.util
	 * @description: 异常类型
	 */
	public enum ViewResultCodeEnum {
		ARTICLE_NOT_EXIST,
		LABEL_NOT_EXIST
	}
	
	/**
	 *@title: toException 
	 *@description: 根据异常类型生成对应描述的运行时异常
	 *@param viewResultCode 异常类型
	 */
	public static RuntimeException toException(ViewResultCodeEnum viewResultCode) {
		switch(viewResultCode) {
		case ARTICLE_NOT_EXIST:
			return new RuntimeException("文章不存在");
		case LABEL_NOT_EXIST:
			return new RuntimeException("标签不存在");
		default:
			return new RuntimeException();
		}
	}
}
