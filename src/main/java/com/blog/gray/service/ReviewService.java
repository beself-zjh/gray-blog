/** 
 * projectName: gray-blog 
 * fileName: ReviewService.java 
 * packageName: com.blog.gray.service 
 * date: Nov 10, 20218:06:23 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service;

import java.util.TreeSet;

import com.blog.gray.domain.ReviewDO;

/**
 * @title: ReviewService.java
 * @package com.blog.gray.service
 * @description: 评论对象 增删改查服务
 * @author: Zjh
 * @date: Nov 10, 2021 8:06:23 PM 
 * @version: V1.0   
 */
public interface ReviewService {

	/**
	 *@title: findByArticleId 
	 *@description: 根据文章id查找评论
	 *@param id 文章id
	 *@return 评论列表
	 */
	public TreeSet<ReviewDO> findByArticleId(Integer id);

}
