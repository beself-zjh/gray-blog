/** 
 * projectName: gray-blog 
 * fileName: ArticleRepositoryTest.java 
 * packageName: com.blog.gray.dao 
 * date: Oct 7, 20212:40:50 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.gray.domain.ArticleDO;

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
	
	@Test
	public void findAllIdTest() {
		List<ArticleDO> articleList = articleRepository.findAll();
		List<Integer> articleIdList = articleRepository.findAllId();
		
		Assert.assertSame(articleList.size(), articleIdList.size());
		
		if(articleList.size() == articleIdList.size())
			for(int i = 0; i < articleList.size(); ++i)
				Assert.assertEquals(articleList.get(i).getId(), articleIdList.get(i));
	}

}
