/** 
 * projectName: gray-blog 
 * fileName: InsertListenPoint.java 
 * packageName: com.blog.gray.annotation 
 * date: Oct 22, 20212:38:18 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.annotation;

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
@ListenPoint(eventType = {CanalEntry.EventType.INSERT})
/**
 * @title: InsertListenPoint.java
 * @package com.blog.gray.annotation
 * @description: TODO
 * @author: Zjh
 * @date: Oct 22, 2021 2:38:18 PM 
 * @version: V1.0   
 */
public @interface InsertListenPoint {
	
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
