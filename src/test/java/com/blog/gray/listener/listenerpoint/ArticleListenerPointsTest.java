/** 
 * projectName: gray-blog 
 * fileName: ArticleListenerPoints.java 
 * packageName: com.blog.gray.listener.listenerpoint 
 * date: Oct 22, 20213:33:26 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.listener.listenerpoint;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.gray.config.CanalConfig;
import com.blog.gray.dao.ArticleRepository;
import com.blog.gray.domain.ArticleDO;
import com.blog.gray.service.ArticleService;
import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.RedisUtil;

/**
 * @title: ArticleListenerPoints.java
 * @package com.blog.gray.listener.listenerpoint
 * @description: TODO
 * @author: Zjh
 * @date: Oct 22, 2021 3:33:26 PM 
 * @version: V1.0   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleListenerPointsTest {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CanalConfig canalConfig;
	
	@Before
	public void before() {
		redisUtil.clear();
	}
	
	@Test
	public void updateArticleEventTest() {
		ArticleDO articleDO = articleRepository.save(new ArticleDO());
		// 1.  缓存中没有
		Assert.assertSame(false, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + articleDO.getId()));
		// 2. 访问一次后缓存中有
		articleService.findById(articleDO.getId());
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + articleDO.getId()));
		// 3. 更新数据库后 ，缓存删除
		articleDO.title = "Test Article";
		articleDO = articleRepository.save(articleDO);
		try {
			Thread.sleep(2 * canalConfig.getAcquireInterval());
		} catch (InterruptedException e) {
			
		}
		Assert.assertSame(false, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + articleDO.getId()));

		articleRepository.deleteById(articleDO.getId());
	}
	
	@Test
	public void insertOrDeleteArticleEventTest() {
		ArticleDO sample = new ArticleDO();
		// 1. 读取全部文章，缓存all_article_id
		articleService.findAll();
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.ALL_ARTICLE_ID));
		// 2. 插入一个文章，缓存all_article_id消失
		sample = articleRepository.save(sample);
		try {
			Thread.sleep(2 * canalConfig.getAcquireInterval());
		} catch (InterruptedException e) {
			
		}
		Assert.assertSame(false, redisUtil.hasKey(RedisKeyConstant.ALL_ARTICLE_ID));
		// 3. 再次缓存all_article_id
		articleService.findAll();
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.ALL_ARTICLE_ID));
		// 4. 删除一篇文章，缓存all_article_id消失
		articleRepository.deleteById(sample.getId());
		try {
			Thread.sleep(2 * canalConfig.getAcquireInterval());
		} catch (InterruptedException e) {
			
		}
		Assert.assertSame(false, redisUtil.hasKey(RedisKeyConstant.ALL_LABEL_ID));
	}
	
	@After
	public void after() {
		redisUtil.clear();
	}
}
