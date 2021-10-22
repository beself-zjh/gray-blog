/** 
 * projectName: gray-blog 
 * fileName: BeanUtil.java 
 * packageName: com.blog.gray.util 
 * date: Oct 20, 202112:26:06 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.util;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @title: BeanUtil.java
 * @package com.blog.gray.util
 * @description: bean获取工具类
 * @author: Zjh
 * @date: Oct 20, 2021 12:26:06 PM
 * @version: V1.0
 */
@Component
public class BeanUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * @title: setApplicationContext
	 * @description: 注入应用上下文applicationContext;
	 * @param applicationContext spring中应用上下文
	 * @throws BeansException
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtil.applicationContext = applicationContext;
	}

	public static Map<String, Object> getBeanWithAnnotation(Class<? extends Annotation> anno) {
		Map<String, Object> map = null;
		try {
			map = applicationContext.getBeansWithAnnotation(anno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
