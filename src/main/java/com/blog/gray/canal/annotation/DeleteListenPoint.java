/** 
 * projectName: gray-blog 
 * fileName: DeleteListenPoint.java 
 * packageName: com.blog.gray.annotation 
 * date: Oct 22, 20212:43:42 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.canal.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

import com.alibaba.otter.canal.protocol.CanalEntry;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
@ListenPoint(eventType = {CanalEntry.EventType.DELETE})
/**
 * @title: DeleteListenPoint.java
 * @package com.blog.gray.annotation
 * @description: TODO
 * @author: Zjh
 * @date: Oct 22, 2021 2:43:42 PM 
 * @version: V1.0   
 */
public @interface DeleteListenPoint {

	/**
	 *@title: destination 
	 *@description: canal destination
	 */
	@AliasFor(annotation = ListenPoint.class)
	String destination() default "";
	
	/**
	 *@title: schema 
	 *@description: schema 模式（数据库）
	 */
	@AliasFor(annotation = ListenPoint.class)
	String[] schema() default {};
	
	/**
	 *@title: table 
	 *@description: 表
	 */
	@AliasFor(annotation = ListenPoint.class)
	String[] table() default {};
	
}
