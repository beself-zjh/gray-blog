/** 
 * projectName: gray-blog 
 * fileName: ViewModelFactory.java 
 * packageName: com.blog.gray.factory 
 * date: Oct 28, 20211:28:30 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

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
	 *@title: createTagsViewModel 
	 *@description: 创建TagsViewModel
	 *@param id 标签
	 *@param page 页码 
	 */
	public TagsViewModel createTagsViewModel(Integer id, Integer page);
	
	/**
	 *@title: createBlogViewModel 
	 *@description: 创建BlogViewModel
	 *@param id 文章id
	 */
	public BlogViewModel createBlogViewModel(Integer id);
}
