/** 
 * projectName: gray-blog 
 * fileName: ArticleRepositoryTest.java 
 * packageName: com.blog.gray.dao 
 * date: Oct 7, 20212:40:50 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.LabelDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @title: ArticleRepositoryTest.java
 * @package com.blog.gray.dao
 * @description: test {@link com.blog.gray.dao.ArticleRepository}
 * @author: Zjh
 * @date: Oct 7, 2021 2:40:50 PM 
 * @version: V1.0   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void addArticleTest() {
		ArticleDO article = new ArticleDO();
		
		List<LabelDO> labels = new ArrayList<>();
		LabelDO label = new LabelDO();
		label.setLabel("计算机网络");
		labels.add(label);
		LabelDO label2 = new LabelDO();
		label2.setLabel("进程");
		labels.add(label2);
		
		article.setTitle("计算机网络---进程与线程");
		article.setCreatedTime(new Date());
		article.setLastUpdateTime(new Date());
		article.setLabels(labels);
		articleRepository.save(article);
		Assert.assertSame(1, articleRepository.findAll().size());
	}
	
	@Ignore
	@Test
	public void redisTest() throws JsonProcessingException {
		String userListJson = redisTemplate.boundValueOps("article.findAll").get();
		
        if (null == userListJson){
            String all = articleRepository.findAll().toString();
            ObjectMapper objectMapper = new ObjectMapper();
            userListJson = objectMapper.writeValueAsString(all);
            redisTemplate.boundValueOps("article.findAll").set(all);
        }
        
        Assert.assertEquals(true, new ObjectMapper().writeValueAsString(articleRepository.findAll().toString())
        		.equals(redisTemplate.boundValueOps("article.findAll").get()));
	}
}
