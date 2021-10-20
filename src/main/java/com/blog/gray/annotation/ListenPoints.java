/** 
 * projectName: gray-blog 
 * fileName: ListenPoints.java 
 * packageName: com.blog.gray.annotation 
 * date: Oct 20, 202112:16:25 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Component
/**
 * @title: ListenPoints.java
 * @package com.blog.gray.annotation
 * @description: 监听点集合类注解
 * @author: Zjh
 * @date: Oct 20, 2021 12:16:25 PM
 * @version: V1.0
 */
public @interface ListenPoints {

	@AliasFor(annotation = Component.class)
	String[] value() default {};
}
