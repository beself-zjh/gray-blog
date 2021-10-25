/** 
 * projectName: gray-blog 
 * fileName: RedisCacheAspect.java 
 * packageName: com.blog.gray.aspect 
 * date: Oct 23, 202111:15:22 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.aspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.LabelDO;
import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.RedisUtil;

/**
 * @title: RedisCacheAspect.java
 * @package com.blog.gray.aspect
 * @description: 缓存查询与更新切面
 * @author: Zjh
 * @date: Oct 23, 2021 11:15:22 AM 
 * @version: V1.0   
 */
@Aspect
@Component
public class RedisCacheAspect {
	
	@Autowired
	private RedisUtil redisUtil;

	/**
	 *@title: findArticleByIdAroundAdvice 
	 *@description: 按id查找文章时，先查询缓存，若没有相应缓存则查询数据库并更新缓存
	 *@param proceedingJoinPoint 切点 ArticleRepository.findById
	 */
	@SuppressWarnings("unchecked")
	@Around("execution(* com.blog.gray.dao.ArticleRepository.findById(..))")
	public Object findArticleByIdAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object[] args = proceedingJoinPoint.getArgs();
		
		Optional<ArticleDO> result = Optional.empty();
		if (redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + args[0])) {
			result = Optional.of((ArticleDO)redisUtil.get(RedisKeyConstant.SINGLE_ARTICLE + args[0]));
		}
		else {
			try {
				result = (Optional<ArticleDO>)proceedingJoinPoint.proceed();
				if (result.isPresent())
					redisUtil.set(RedisKeyConstant.SINGLE_ARTICLE + args[0], result.get()); // 放入缓存
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 *@title: findAllArticleIdAroundAdvice 
	 *@description: 查找文章id时，先查询缓存，若没有相应缓存则查询数据库并更新缓存
	 *@param proceedingJoinPoint ArticleRepository.findAllId
	 */
	@SuppressWarnings("unchecked")
	@Around("execution(* com.blog.gray.dao.ArticleRepository.findAllId(..))")
	public Object findAllArticleIdAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		List<Integer> result = new ArrayList<>();
		if (redisUtil.hasKey(RedisKeyConstant.ALL_ARTICLE_ID)) // cache读取
			return Arrays.asList(redisUtil.lGet(RedisKeyConstant.ALL_ARTICLE_ID, 0, -1).toArray(new Integer[0]));
		else {
			try {
				result = (List<Integer>)proceedingJoinPoint.proceed();
				redisUtil.lSet(RedisKeyConstant.ALL_ARTICLE_ID, result); // 放入缓存
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 *@title: findLabelByIdAroundAdvice 
	 *@description: 按id查找标签时，先查询缓存，若没有相应缓存则查询数据库并更新缓存
	 *@param proceedingJoinPoint 切点 LabelRepository.findById
	 */
	@SuppressWarnings("unchecked")
	@Around("execution(* com.blog.gray.dao.LabelRepository.findById(..))")
	public Object findLabelByIdAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object[] args = proceedingJoinPoint.getArgs();
		
		Optional<LabelDO> result = Optional.empty();
		if (redisUtil.hasKey(RedisKeyConstant.SINGLE_LABEL + args[0])) {
			result = Optional.of((LabelDO)redisUtil.get(RedisKeyConstant.SINGLE_LABEL + args[0]));
		}
		else {
			try {
				result = (Optional<LabelDO>)proceedingJoinPoint.proceed();
				if (result.isPresent())
					redisUtil.set(RedisKeyConstant.SINGLE_LABEL + args[0], result.get()); // 放入缓存
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 *@title: findAllLabelIdAroundAdvice 
	 *@description: 查找标签id时，先查询缓存，若没有相应缓存则查询数据库并更新缓存
	 *@param proceedingJoinPoint 切点 LabelRepository.findAllId
	 */
	@SuppressWarnings("unchecked")
	@Around("execution(* com.blog.gray.dao.LabelRepository.findAllId(..))")
	public Object findAllLabelIdAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		List<Integer> result = new ArrayList<>();
		if (redisUtil.hasKey(RedisKeyConstant.ALL_LABEL_ID)) // cache读取
			return Arrays.asList(redisUtil.lGet(RedisKeyConstant.ALL_LABEL_ID, 0, -1).toArray(new Integer[0]));
		else {
			try {
				result = (List<Integer>)proceedingJoinPoint.proceed();
				redisUtil.lSet(RedisKeyConstant.ALL_LABEL_ID, result); // 放入缓存
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
