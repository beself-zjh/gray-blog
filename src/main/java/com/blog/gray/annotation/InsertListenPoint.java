/** 
 * projectName: gray-blog 
 * fileName: InsertListenPoint.java 
 * packageName: com.blog.gray.annotation 
 * date: Oct 20, 202112:03:50 PM 
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
@ListenPoint(eventType = CanalEntry.EventType.INSERT)
/**
 * @title: InsertListenPoint.java
 * @package com.blog.gray.annotation
 * @description: 插入事件监听点注解
 * @author: Zjh
 * @date: Oct 20, 2021 12:03:50 PM
 * @version: V1.0
 */
public @interface InsertListenPoint {

	@AliasFor(annotation = ListenPoint.class)
	String destination() default "";
	
	@AliasFor(annotation = ListenPoint.class)
	String[] schema() default {};
	
	@AliasFor(annotation = ListenPoint.class)
	String[] table() default {};
	
}
