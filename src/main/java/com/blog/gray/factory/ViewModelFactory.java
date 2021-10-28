/** 
 * projectName: gray-blog 
 * fileName: ViewModelFactory.java 
 * packageName: com.blog.gray.factory 
 * date: Oct 28, 20211:28:30 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.factory;

import com.blog.gray.viewmodel.HomeViewModel;
import com.blog.gray.viewmodel.TagsViewModel;

/**
 * @title: ViewModelFactory.java
 * @package com.blog.gray.factory
 * @description: view model 工厂
 * @author: Zjh
 * @date: Oct 28, 2021 1:28:30 PM 
 * @version: V1.0   
 */
public interface ViewModelFactory {

	/**
	 *@title: createHomeViewModel 
	 *@description: 创建HomeViewModel
	 *@param page 页码 
	 */
	public HomeViewModel createHomeViewModel(Integer page);
	
	/**
	 *@title: createHomeViewModel 
	 *@description: 创建TagsViewModel
	 *@param id 标签
	 *@param page 页码 
	 */
	public TagsViewModel createTagsViewModel(Integer id, Integer page);
}
