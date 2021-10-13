/** 
 * projectName: blog 
 * fileName: ArticleColletService.java 
 * packageName: com.example.blog.service 
 * date: Sep 7, 20202:18:00 PM 
 */
package com.blog.gray.service;

import java.util.List;

import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.LabelDO;

/**
 * @title: ArticleColletService.java
 * @package com.example.blog.service
 * @description: TODO
 * @author: Zjh
 * @date: Sep 7, 2020 2:18:00 PM
 * @version: V1.0
 */
public interface ArticleCollectService {

	/**
	 * @title: findAll
	 * @description: get all article information from database.
	 * @return List<ArticleDO> - all articleInfo in database
	 */
	public List<ArticleDO> findAll();

	/**
	 * @title: findNewArticles
	 * @description: sort articles by date.
	 * @param num: return the first 'num' article info.
	 * @return List<ArticleDO> - the first 'num' article info
	 */
	public List<ArticleDO> findNewArticles(int num) throws IndexOutOfBoundsException;

	/**
	 * @title: findAllLabel
	 * @description: get all the label of articles.
	 * @return List<String> - all label
	 */
	public List<LabelDO> findAllLabel();
	
}
