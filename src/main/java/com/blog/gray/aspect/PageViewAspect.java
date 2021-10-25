/** 
 * projectName: gray-blog 
 * fileName: PageViewAspect.java 
 * packageName: com.blog.gray.aspect 
 * date: Oct 25, 20217:26:40 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.IPUtil;
import com.blog.gray.util.RedisUtil;

/**
 * @title: PageViewAspect.java
 * @package com.blog.gray.aspect
 * @description: 文章访问量统计切面
 * @author: Zjh
 * @date: Oct 25, 2021 7:26:40 PM 
 * @version: V1.0   
 */
@Aspect
@Component
public class PageViewAspect {
	
	@Autowired
	private RedisUtil redisUtil;

	@Pointcut("@annotation(com.blog.gray.annotation.PageView)")
	public void pageViewPointcut() {}
	
	/**
	 *@title: before 
	 *@description: 前置通知，访问文章时触发
	 *@param point 切点
	 */
	@Before("pageViewPointcut()")
	public void before(JoinPoint point) {
		Object[] args = point.getArgs();
		HttpServletRequest httpServvletRequest = (HttpServletRequest) args[0];
		Integer id = (Integer) args[1];
		
		String ip = IPUtil.getIpAddr(httpServvletRequest);
		redisUtil.hllSet(RedisKeyConstant.SINGLE_ARTICLE_PV + id, ip);
	}
}
