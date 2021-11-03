package com.blog.gray.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.dao.ArticleRepository;
import com.blog.gray.dao.LabelRepository;
import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.LabelDO;
import com.blog.gray.service.ArticleService;
import com.blog.gray.util.ViewCodeUtil;
import com.blog.gray.util.ViewCodeUtil.ViewResultCodeEnum;

/**
 * @title: ArticleServiceImpl.java
 * @package com.blog.gray.service.impl
 * @description: 文章属性相关服务
 * @author: Zjh
 * @date: Oct 16, 2021 3:39:18 PM
 * @version: V1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private LabelRepository labelRepository;

	/**
	 * Note ：务必处理文章不存在异常
	 * @title: findById
	 * @description: 根据id查找文章信息
	 * @param id
	 * @return 文章实体类
	 */
	@Override
	public ArticleDO findById(int id) throws RuntimeException {
		// redis + mysql读取
		Optional<ArticleDO> articleOptional = articleRepository.findById(id);
		if (articleOptional.isPresent()) {
			ArticleDO article = articleOptional.get();
			article.setLabels(labelRepository.findByArticles_id(article.getId()));
			return article;
		} else { // 若数据库中不存在，抛出文章不存在异常
			throw ViewCodeUtil.toException(ViewResultCodeEnum.ARTICLE_NOT_EXIST);
		}
	}

	/**
	 * @title: findAll
	 * @description: 获取全部文章信息
	 * @return 全部文章 信息
	 */
	@Override
	public List<ArticleDO> findAll() {
		List<ArticleDO> articleList = new ArrayList<ArticleDO>();
		List<Integer> articleIdList = findAllId(); // 读取全部文章的id

		for (int id : articleIdList) { // 逐个读取文章属性
			try {
				articleList.add(findById(id));
			} catch (Exception e) { // 跳过不存在的文章，并打印警告，此时数据库存在不一致数据
				e.printStackTrace();
			}
		}

		return articleList;
	}

	/**
	 * @title: findNewArticles
	 * @description: 获取最新发布的num篇文章信息
	 * @param num: 文章篇数
	 * @return num篇文章信息
	 */
	@Override
	public List<ArticleDO> findNewArticles(int num) {
		int from = size() - num;
		int to = from + num;
		List<ArticleDO> newArticles = findDateSortedArticles(from, to);
		Collections.reverse(newArticles);
		return newArticles;
	}
	
	/**
	 *@title: findArticles 
	 *@description: 查找所有文章，并按日期排序
	 */
	@Override
	public List<ArticleDO> findDateSortedAllArticles() {
		List<ArticleDO> allArticles = findAll();
		
		allArticles.sort((ArticleDO item1, ArticleDO item2) 
				-> item1.getCreatedTime().compareTo(item2.getCreatedTime()));
		
		return allArticles;
	}
	
	/**
	 *@title: findArticles 
	 *@description: 查找下标从s到t的文章
	 *@param from 起始下标
	 *@param to 终止下标
	 */
	@Override
	public List<ArticleDO> findDateSortedArticles(int from, int to) {
		int start = from < 0 ? 0 : from;
		int end = to <= 0 ? 1 : to;

		List<ArticleDO> allArticles = findDateSortedAllArticles();
		int maxIndex = allArticles.size() - 1;

		if (maxIndex >= 0) {
			return allArticles.subList(Math.min(start, maxIndex), Math.min(end, maxIndex + 1));
		}
		return allArticles;
	}

	/**
	 *@title: size 
	 *@description: 统计文章总数量
	 *@return 文章数量
	 */
	@Override
	public Integer size() {
		return findAllId().size();
	}
	
	/**   
	 * @title: findDateSortedArticlesByLabel
	 * @description: 根据标签查找文章
	 * @param labelId
	 * @return   
	 * @see com.blog.gray.service.ArticleService#findDateSortedArticlesByLabel(int)     
	 */
	@Override
	public List<ArticleDO> findDateSortedArticlesByLabel(int labelId) {
		return articleRepository.findByLabels_id(labelId);
	}

	/**   
	 * @title: findDateSortedArticlesByLabel
	 * @description: 根据标签查找文章子序列
	 * @param labelId
	 * @param from 起始下标
	 * @param to 终止下标
	 * @return   
	 * @see com.blog.gray.service.ArticleService#findDateSortedArticlesByLabel(int, int, int)     
	 */
	@Override
	public List<ArticleDO> findDateSortedArticlesByLabel(int labelId, int from, int to) {
		List<ArticleDO> articles = findDateSortedArticlesByLabel(labelId);
		int start = from < 0 ? 0 : from;
		int end = to <= 0 ? 1 : to;
		int maxIndex = articles.size() - 1;
		if (maxIndex >= 0) {
			articles.sort((ArticleDO item1, ArticleDO item2) 
					-> item1.getCreatedTime().compareTo(item2.getCreatedTime()));
			return articles.subList(Math.min(start, maxIndex), Math.min(end, maxIndex + 1));
		}
		return articles;
	}

	/**   
	 * @title: createArticle
	 * @description: 创建文章
	 * @return  新文章对象
	 * @see com.blog.gray.service.ArticleService#createArticle()     
	 */
	@Override
	public ArticleDO createArticle(String title, String type, String content, List<Integer> labels) {
		ArticleDO article = new ArticleDO();
		article.setCreatedTime(new Date(Calendar.getInstance().getTimeInMillis()));
		article.setTitle(title);
		article.setType(type);

		// 设置摘要
		String summary = content.replaceAll("#", "").replaceAll("\n", " ");
		summary = summary.substring(0, Math.min(summary.length(), 200));
		article.setSummary(summary);

		// 设置标签
		List<LabelDO> labelList = labelRepository.findAllById(labels);
		article.setLabels(labelList);
		
		return article;
	}

	/**   
	 * @title: save
	 * @description: 保存文章
	 * @param article   
	 * @see com.blog.gray.service.ArticleService#save(com.blog.gray.domain.ArticleDO)     
	 */
	@Override
	public ArticleDO save(ArticleDO article) {
		return articleRepository.save(article);
	}
	
	/**
	 * @title: findAllId
	 * @description: 获取全部文章的id
	 * @return 所有文章id
	 */
	private List<Integer> findAllId() {
		// redis + mysql读取
		List<Integer> articleIdList = articleRepository.findAllId();

		return articleIdList;
	}

}
