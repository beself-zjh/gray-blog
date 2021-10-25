/** 
 * projectName: gray-blog 
 * fileName: LabelServiceTest.java 
 * packageName: com.blog.gray.service 
 * date: Oct 25, 20216:13:15 PM 
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

import com.blog.gray.domain.LabelDO;
import com.blog.gray.util.RedisUtil;

/**
 * @title: LabelServiceTest.java
 * @package com.blog.gray.service
 * @description: TODO
 * @author: Zjh
 * @date: Oct 25, 2021 6:13:15 PM 
 * @version: V1.0   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelServiceTest {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private LabelService labelService;
	
	@Before
	public void before() {
		redisUtil.clear();
	}
	
	@Test
	public void findByIdTest() {
		LabelDO label1 = labelService.findById(1);
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.SINGLE_LABEL + 1));
		
		LabelDO label2 = labelService.findById(1);
		Assert.assertEquals(label1.getLabel(), label2.getLabel());
	}
	
	@Test
	public void findAllTest() {
		List<LabelDO> labels = labelService.findAll();
		Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.ALL_LABEL_ID));
		for (LabelDO label : labels) {
			Assert.assertSame(true, redisUtil.hasKey(RedisKeyConstant.SINGLE_LABEL + label.getId()));
		}
	}

}
