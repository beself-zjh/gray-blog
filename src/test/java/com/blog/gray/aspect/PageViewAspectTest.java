/** 
 * projectName: gray-blog 
 * fileName: PageViewAspectTest.java 
 * packageName: com.blog.gray.aspect 
 * date: Oct 26, 20216:35:39 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.aspect;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.RedisUtil;

/**
 * @title: PageViewAspectTest.java
 * @package com.blog.gray.aspect
 * @description: 测试访问量统计切面
 * @author: Zjh
 * @date: Oct 26, 2021 6:35:39 PM 
 * @version: V1.0   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageViewAspectTest {
	
	private MockMvc mock;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void before() {
		redisUtil.clear();
		mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@After
	public void after() {
		redisUtil.clear();
	}
	
	@Test
	public void pageViewRecordTest() {
		Integer articleId = 1;
		// 1. 模拟一次文章访问
		try {
			mock.perform(MockMvcRequestBuilders
					.get("/blog").param("id", articleId.toString()))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2. 检查缓存中访问量
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.SINGLE_ARTICLE_PV + articleId));
		Assert.assertEquals(Long.valueOf(1L), redisUtil.hllSize(RedisKeyConstant.SINGLE_ARTICLE_PV + articleId));
	}
	
}
