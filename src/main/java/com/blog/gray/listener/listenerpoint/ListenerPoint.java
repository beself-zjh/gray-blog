/** 
 * projectName: gray-blog 
 * fileName: ListenerPoint.java 
 * packageName: com.blog.gray.listener.listenerpoint 
 * date: Oct 19, 20213:39:41 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.listener.listenerpoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.blog.gray.annotation.ListenPoint;

/**
 * @title: ListenerPoint.java
 * @package com.blog.gray.listener.listenerpoint
 * @description: 用于封装ListenPoint注解的方法
 * @author: Zjh
 * @date: Oct 19, 2021 3:39:41 PM
 * @version: V1.0
 */
public class ListenerPoint {
	
	private Object target;
	
	private Method method;
	
	private ListenPoint anno;
	
	public ListenerPoint(Object target, Method method, ListenPoint anno) {
		this.target = target;
		this.method = method;
		this.anno = anno;
	}
	
	public Object getTarget() {
		return target;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public ListenPoint getAnno() {
		return anno;
	}
}
