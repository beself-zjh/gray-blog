/** 
 * projectName: gray-blog 
 * fileName: ViewModelFactoryImpl.java 
 * packageName: com.blog.gray.factory.impl 
 * date: Oct 28, 20211:31:15 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.config.WebConfig;
import com.blog.gray.service.ArticleService;
import com.blog.gray.service.FileService;
import com.blog.gray.service.LabelService;
import com.blog.gray.service.ReviewService;
import com.blog.gray.util.MarkdownUtil;

/**
 * @title: ViewModelFactoryImpl.java
 * @package com.blog.gray.factory.impl
 * @description: view model 工厂
 * @author: Zjh
 * @date: Oct 28, 2021 1:31:15 PM 
 * @version: V1.0   
 */
@Service
public class ViewModelFactoryImpl implements ViewModelFactory {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private LabelService labelService;

	@Autowired
	private FileService fileService;

	@Autowired
	private MarkdownUtil markdownUtil;

	@Autowired
	private WebConfig webConfig;
	
	/**   
	 * @title: createHomeViewModel
	 * @description: 创建HomeViewModel
	 * @param page 页码
	 * @return   
	 * @see com.blog.gray.viewmodel.ViewModelFactory#createHomeViewModel(Integer)     
	 */
	@Override
	public HomeViewModel createHomeViewModel(Integer page) {
		HomeViewModel homeViewModel = new HomeViewModel(articleService, labelService, webConfig, page);			
		homeViewModel.init();
		
		return homeViewModel;
	}
	
	/**   
	 * @title: createTagsViewModel
	 * @description: 创建TagsViewModel
	 * @param id 标签
	 * @param page 页码
	 * @return   
	 * @see com.blog.gray.viewmodel.ViewModelFactory#createTagsViewModel(Integer, Integer)     
	 */
	@Override
	public TagsViewModel createTagsViewModel(Integer id, Integer page) {
		TagsViewModel tagsViewModel = new TagsViewModel(articleService, labelService, webConfig, id, page);			
		tagsViewModel.init();
		
		return tagsViewModel;
	}

	/**   
	 * @title: createBlogViewModel
	 * @description: 创建BlogViewModel
	 * @param id 文章id
	 * @return   
	 * @see com.blog.gray.viewmodel.ViewModelFactory#createBlogViewModel(java.lang.Integer)     
	 */
	@Override
	public BlogViewModel createBlogViewModel(Integer id) {
		BlogViewModel blogViewModel = new BlogViewModel(articleService, webConfig, fileService, reviewService, markdownUtil, id);			
		blogViewModel.init();
		
		return blogViewModel;
	}

}
