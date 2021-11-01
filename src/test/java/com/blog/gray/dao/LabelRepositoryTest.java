///** 
// * projectName: gray-blog 
// * fileName: LabelRepositoryTest.java 
// * packageName: com.blog.gray.dao 
// * date: Oct 17, 20213:28:06 PM 
// * copyright(c) 2017-2020 xxx公司
// */
//package com.blog.gray.dao;
//
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.blog.gray.domain.LabelDO;
//
///**
// * @title: LabelRepositoryTest.java
// * @package com.blog.gray.dao
// * @description: test  {@link com.blog.gray.dao.LabelRepository}
// * @author: Zjh
// * @date: Oct 17, 2021 3:28:06 PM 
// * @version: V1.0   
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LabelRepositoryTest {
//
//	@Autowired
//	private LabelRepository labelRepository;
//	
//	@Test
//	public void findAllIdTest() {
//		List<LabelDO> labelList = labelRepository.findAll();
//		List<Integer> labelIdList = labelRepository.findAllId();
//		
//		Assert.assertSame(labelList.size(), labelIdList.size());
//		
//		labelList.sort((LabelDO item1, LabelDO item2) -> item1.getId().compareTo(item2.getId()));
//		labelIdList.sort((Integer item1, Integer item2) -> item1.compareTo(item2));
//		
//		if(labelList.size() == labelIdList.size())
//			for(int i = 0; i < labelList.size(); ++i)
//				Assert.assertEquals(labelList.get(i).getId(), labelIdList.get(i));
//	}
//	
//	@Test
//	public void findByArticlesId() {
//		for (LabelDO label : labelRepository.findByArticles_id(7)) {
//			System.out.println(label.getLabel());
//		}
//	}
//}
