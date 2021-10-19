/** 
 * projectName: gray-blog 
 * fileName: EnableCanalClient.java 
 * packageName: com.blog.gray.annotation 
 * date: Oct 18, 20214:07:48 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.blog.gray.config.CanalClientConfiguration;
import com.blog.gray.config.CanalConfig;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@Import({CanalConfig.class, CanalClientConfiguration.class})
/**
 * @title: EnableCanalClient.java
 * @package com.blog.gray.annotation
 * @description: 启动canal客户端注解
 * @author: Zjh
 * @date: Oct 18, 2021 4:07:48 PM 
 * @version: V1.0   
 */
public @interface EnableCanalClient {

}
