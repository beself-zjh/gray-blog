/** 
 * projectName: gray-blog 
 * fileName: ReviewRepository.java 
 * packageName: com.blog.gray.dao 
 * date: Nov 10, 20217:46:54 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.gray.domain.ReviewDO;

/**
 * @title: ReviewRepository.java
 * @package com.blog.gray.dao
 * @description: 评论 数据访问对象
 * @author: Zjh
 * @date: Nov 10, 2021 7:46:54 PM 
 * @version: V1.0   
 */
public interface ReviewRepository extends JpaRepository<ReviewDO, Integer> {

	public List<ReviewDO> findByArticleId(Integer id);
	
}
