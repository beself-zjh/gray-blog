/** 
 * projectName: gray-blog 
 * fileName: ListenerPoint.java 
 * packageName: com.blog.gray.listener.listenerpoint 
 * date: Oct 19, 20213:39:41 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.canal.model;

import java.lang.reflect.Method;

import com.blog.gray.canal.annotation.ListenPoint;

/**
 * @title: ListenerPoint.java
 * @package com.blog.gray.listener.listenerpoint
 * @description: 用于封装ListenPoint注解的方法
 * @author: Zjh
 * @date: Oct 19, 2021 3:39:41 PM
 * @version: V1.0
 */
public class ListenerPoint {
	
	/**
	 * @Fields target : 监听点所在bean对象
	 */
	private Object target;
	
	/**
	 * @Fields method : 监听点方法
	 */
	private Method method;
	
	/**
	 * @Fields anno : 监听点注解类型
	 */
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
