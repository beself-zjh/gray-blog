/** 
 * projectName: gray-blog 
 * fileName: PageView.java 
 * packageName: com.blog.gray.annotation 
 * date: Oct 25, 20217:25:33 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 * @title: PageView.java
 * @package com.blog.gray.annotation
 * @description: TODO
 * @author: Zjh
 * @date: Oct 25, 2021 7:25:33 PM 
 * @version: V1.0   
 */
public @interface PageView {

}
