package com.blog.gray.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.dao.ArticleRepository;
import com.blog.gray.domain.ArticleDO;
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
			return articleOptional.get();
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
		int n = Math.abs(num); // 防止num为负数

		// 获取全部根据日期排序
		List<ArticleDO> allArticleInfo = findAll();
		allArticleInfo
				.sort((ArticleDO item1, ArticleDO item2) -> item1.getCreatedTime().compareTo(item2.getCreatedTime()));

		return allArticleInfo.subList(0, n <= allArticleInfo.size() ? n : allArticleInfo.size());
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
