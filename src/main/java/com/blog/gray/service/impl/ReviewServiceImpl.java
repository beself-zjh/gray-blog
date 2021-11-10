/** 
 * projectName: gray-blog 
 * fileName: ReviewServiceImpl.java 
 * packageName: com.blog.gray.service.impl 
 * date: Nov 10, 20218:35:53 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.dao.ReviewRepository;
import com.blog.gray.domain.ReviewDO;
import com.blog.gray.service.ReviewService;

/**
 * @title: ReviewServiceImpl.java
 * @package com.blog.gray.service.impl
 * @description: TODO
 * @author: Zjh
 * @date: Nov 10, 2021 8:35:53 PM 
 * @version: V1.0   
 */
@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	/**   
	 * @title: findByArticleId
	 * @description: 根据文章id查找评论
	 * @param id 文章id
	 * @return 评论列表
	 * @see com.blog.gray.service.ReviewService#findByArticleId(java.lang.Integer)     
	 */
	@Override
	public TreeSet<ReviewDO> findByArticleId(Integer id) {
		Map<Integer, ReviewDO> reviewsMap = new HashMap<Integer, ReviewDO>();
		List<ReviewDO> reviewAndReply = reviewRepository.findByArticleId(id);
		
		reviewAndReply.stream().filter(item -> !item.getIsReply()).forEach(r -> {
			reviewsMap.put(r.getId(), r);
		});
		reviewAndReply.stream().filter(item -> item.getIsReply()).forEach(r -> {
			reviewsMap.get(r.getReplyId()).addReply(r);;
		});
		
		TreeSet<ReviewDO> reviews = new TreeSet<ReviewDO>(reviewsMap.values());
		
		return reviews;
	}

}
