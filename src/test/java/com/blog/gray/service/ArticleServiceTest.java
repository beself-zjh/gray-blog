/** 
 * projectName: gray-blog 
 * fileName: ArticleServiceTest.java 
 * packageName: com.blog.gray.service 
 * date: Oct 17, 20213:59:41 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.gray.domain.ArticleDO;
import com.blog.gray.util.RedisUtil;

/**
 * @title: ArticleServiceTest.java
 * @package com.blog.gray.service
 * @description: 测试ArticleService
 * @author: Zjh
 * @date: Oct 17, 2021 3:59:41 PM 
 * @version: V1.0   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ArticleService articleService;

	@Before
	public void before() {
		redisUtil.clear();
	}
	
	@Test
	public void findByIdTest() {
		ArticleDO article1 = articleService.findById(5);
		Assert.assertEquals(true, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + 5));
		
		ArticleDO article2 = articleService.findById(5);
		Assert.assertEquals(article1.getId(), article2.getId());
	}
	
	@Test
	public void findAllTest() {
		List<ArticleDO> articles = articleService.findAll();
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.ALL_ARTICLE_ID));
		for(ArticleDO article : articles) {
			Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE + article.getId()));
		}
	}
}
