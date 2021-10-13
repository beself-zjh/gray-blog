package com.blog.gray.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.dao.ArticleRepository;
import com.blog.gray.dao.LabelRepository;
import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.LabelDO;
import com.blog.gray.service.ArticleCollectService;
import com.blog.gray.util.RedisUtil;

@Service
public class ArticleCollectServiceImpl implements ArticleCollectService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired 
	private RedisUtil redisUtil;

	/**
	 * @title: findAll
	 * @description: get all article information from database.
	 * @return List<ArticleDO> - all articleInfo in database
	 */
	//@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public List<ArticleDO> findAll() {
		if(redisUtil.hasKey("articleList")) {
			return Arrays.asList(redisUtil.lGet("articleList", 0, -1).toArray(new ArticleDO[0]));
			//return (List<ArticleDO>)(Object)redisUtil.lGet("articleList", 0, -1);
		}
		List<ArticleDO> articleList = articleRepository.findAll();
		redisUtil.lSet("articleList", articleList);
		
		return articleList;
	}

	/**
	 * @title: findNewArticles
	 * @description: sort articles by date.
	 * @param num: the number of article you want to get.
	 * @return List<ArticleDO> - the first 'num' article info
	 */
	@Override
	public List<ArticleDO> findNewArticles(int num) throws IndexOutOfBoundsException {
		// num can't less than zero
		if (num < 0)
			throw new IndexOutOfBoundsException();

		// sort by date
		List<ArticleDO> allArticleInfo = this.findAll();
		allArticleInfo
				.sort((ArticleDO item1, ArticleDO item2) -> item1.getCreatedTime().compareTo(item2.getCreatedTime()));

		return allArticleInfo.subList(0, num <= allArticleInfo.size() ? num : allArticleInfo.size());
	}

	/**
	 * @title: findAllLabel
	 * @description: get all the label of articles.
	 * @return List<String> - all label
	 */
	//@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public List<LabelDO> findAllLabel() {
		if(redisUtil.hasKey("labelList")) {
			return Arrays.asList(redisUtil.lGet("labelList", 0, -1).toArray(new LabelDO[0]));
			//return (List<LabelDO>)(Object)redisUtil.lGet("labelList", 0, -1);
		}
		List<LabelDO> labelList = labelRepository.findAll();
		redisUtil.lSet("labelList", labelList);
		
		return labelList;
	}
}
