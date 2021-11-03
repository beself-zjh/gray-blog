/** 
 * projectName: blog 
 * fileName: ArticleColletService.java 
 * packageName: com.example.blog.service 
 * date: Sep 7, 20202:18:00 PM 
 */
package com.blog.gray.service;

import java.util.List;

import com.blog.gray.domain.ArticleDO;

/**
 * @title: ArticleColletService.java
 * @package com.example.blog.service
 * @description: 文章属性相关服务
 * @author: Zjh
 * @date: Sep 7, 2020 2:18:00 PM
 * @version: V1.0
 */
public interface ArticleService {

	/**
	 * @title: findById
	 * @description: 根据id查找文章信息
	 * @param id
	 * @return 文章实体类
	 */
	public ArticleDO findById(int id);

	/**
	 * @title: findAll
	 * @description: 获取全部文章
	 * @return 全部文章
	 */
	public List<ArticleDO> findAll();

	/**
	 * @title: findNewArticles
	 * @description: 获取最新发布的num篇文章的信息
	 * @param num: 返回的文章篇数
	 * @return num篇文章信息
	 */
	public List<ArticleDO> findNewArticles(int num) throws IndexOutOfBoundsException;
	
	/**
	 *@title: findArticles 
	 *@description: 查找所有文章，并按日期排序
	 */
	public List<ArticleDO> findDateSortedAllArticles();
	
	/**
	 *@title: findArticles 
	 *@description: 查找下标从s到t的文章
	 *@param from 起始下标
	 *@param to 终止下标
	 */
	public List<ArticleDO> findDateSortedArticles(int from, int to);

	/**
	 *@title: findDateSortedArticlesByLabel 
	 *@description: 根据标签查找文章
	 *@param labelId
	 */
	public List<ArticleDO> findDateSortedArticlesByLabel(int labelId);
	
	
	/**
	 *@title: findDateSortedArticlesByLabel 
	 *@description: 根据标签查找文章子序列
	 *@param labelId
	 *@param from 起始下标
	 *@param to 终止下标
	 */
	public List<ArticleDO> findDateSortedArticlesByLabel(int labelId, int from, int to);
	
	/**
	 *@title: size 
	 *@description: 统计文章总数量
	 *@return 文章数量
	 */
	public Integer size();
	
	/**
	 *@title: createArticle 
	 *@description: 创建文章
	 *@return 新文章对象
	 */
	public ArticleDO createArticle(String title, String type, String Content, List<Integer> labels);
	
	/**
	 *@title: save 
	 *@description: 保存文章
	 */
	public ArticleDO save(ArticleDO article);

}
