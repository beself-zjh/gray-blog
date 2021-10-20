/** 
 * projectName: gray-blog 
 * fileName: ListenPoint.java 
 * packageName: com.blog.gray.annotation 
 * date: Oct 20, 202111:58:35 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.alibaba.otter.canal.protocol.CanalEntry;

@Documented
@Retention(RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
/**
 * @title: ListenPoint.java
 * @package com.blog.gray.annotation
 * @description: 监听点注解
 * @author: Zjh
 * @date: Oct 20, 2021 11:58:35 AM
 * @version: V1.0
 */
public @interface ListenPoint {

	String destination() default "";
	
	String[] schema() default {};
	
	String[] table() default {};
	
	/**
	 * @title: eventType
	 * @description: 监听事件类型
	 */
	CanalEntry.EventType[] eventType() default {};
}
