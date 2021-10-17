/** 
 * projectName: gray-blog 
 * fileName: LabelDAO.java 
 * packageName: com.blog.gray.dao 
 * date: Oct 7, 20212:35:46 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.gray.domain.LabelDO;

/**
 * @title: LabelDAO.java
 * @package com.blog.gray.dao
 * @description: 标签 数据访问对象
 * @author: Zjh
 * @date: Oct 7, 2021 2:35:46 PM 
 * @version: V1.0   
 */
public interface LabelRepository extends JpaRepository<LabelDO, Integer>{
	
	@Query(value = "select t.id from LabelDO t")
	public List<Integer> findAllId();
	
}
