/** 
 * projectName: gray-blog 
 * fileName: LabelService.java 
 * packageName: com.blog.gray.service 
 * date: Oct 16, 20213:27:24 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service;

import java.util.List;

import com.blog.gray.domain.LabelDO;

/**
 * @title: LabelService.java
 * @package com.blog.gray.service
 * @description: 标签相关服务
 * @author: Zjh
 * @date: Oct 16, 2021 3:27:24 PM
 * @version: V1.0
 */
public interface LabelService {

	/**
	 * @title: findById
	 * @description: 根据id查找标签信息
	 * @param id
	 * @return 标签实体类
	 */
	public LabelDO findById(int id);

	/**
	 * @title: findAll
	 * @description: 获取所有标签
	 * @return 全部标签
	 */
	public List<LabelDO> findAll();

}
