/** 
 * projectName: gray-blog 
 * fileName: RedisSchedulerTest.java 
 * packageName: com.blog.gray.scheduler 
 * date: Oct 26, 20213:58:05 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.scheduler;

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
 * @title: RedisSchedulerTest.java
 * @package com.blog.gray.scheduler
 * @description: 测试缓存保存定时器
 * @author: Zjh
 * @date: Oct 26, 2021 3:58:05 PM 
 * @version: V1.0   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSchedulerTest {
	
	@Autowired
	private CanalConfig canalConfig;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private RedisScheduler redisScheduler;
	
	@Before
	public void before() {
		redisUtil.clear();
	}
	
	@After
	public void after() {
		//redisUtil.clear();
	}
	
	@Test
	public void pageViewSaveTest() {
		// 0. 创建临时测试用例
		String ip = "127.0.0.1";
		ArticleDO article = articleRepository.save(new ArticleDO());
		Long pv = article.getVisits();	// 初始访问量为0
		// 1. 访问一次
		redisUtil.hllSet(RedisKeyConstant.SINGLE_ARTICLE_PV + article.getId(), ip);
		pv += redisUtil.hllSize(RedisKeyConstant.SINGLE_ARTICLE_PV + article.getId());
		// 2. 触发定时异步更新，访问量更新进数据库，删除当日文章pv的缓存
		redisScheduler.pageViewSave();
		try {
			Thread.sleep(3 * canalConfig.getAcquireInterval());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertSame(false, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE_PV + article.getId()));
		Assert.assertSame(false, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + article.getId()));
		// 3. 再次获取该文章，核对访问量
		article = articleService.findById(article.getId());
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + article.getId()));
		Assert.assertEquals(pv, article.getVisits());
		// 4. 删除测试用例
		articleRepository.deleteById(article.getId());
		try {
			Thread.sleep(3 * canalConfig.getAcquireInterval());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertSame(false, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + article.getId()));
	}
	
}
