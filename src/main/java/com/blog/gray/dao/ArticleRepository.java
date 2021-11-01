/** 
 * projectName: gray-blog 
 * fileName: ArticleRepository.java 
 * packageName: com.blog.gray.dao 
 * date: Oct 6, 20218:08:15 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.gray.domain.ArticleDO;

/**
 * @title: ArticleRepository.java
 * @package com.blog.gray.dao
 * @description: 文章 数据访问对象
 * @author: Zjh
 * @date: Oct 6, 2021 8:08:15 PM
 * @version: V1.0
 */
public interface ArticleRepository extends JpaRepository<ArticleDO, Integer> {

	@Query(value = "select t.id from ArticleDO t")
	public List<Integer> findAllId();

	public List<ArticleDO> findByLabels_id(Integer id);
}
