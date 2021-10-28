/** 
 * projectName: gray-blog 
 * fileName: HomeViewModel.java 
 * packageName: com.blog.gray.viewmodel 
 * date: Oct 28, 202111:21:07 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import java.util.List;

import com.blog.gray.config.WebConfig;
import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.LabelDO;
import com.blog.gray.service.ArticleService;
import com.blog.gray.service.LabelService;

/**
 * @title: HomeViewModel.java
 * @package com.blog.gray.viewmodel
 * @description: 首页view model
 * @author: Zjh
 * @date: Oct 28, 2021 11:21:07 AM
 * @version: V1.0
 */
public class HomeViewModel {

	private ArticleService articleService;
	private LabelService labelService;
	private WebConfig webConfig;

	private Integer numOfArticle;
	private List<ArticleDO> articles;
	private List<ArticleDO> newArticles;
	private List<LabelDO> labels;

	private Integer page = 1;

	public HomeViewModel(ArticleService articleService, LabelService labelService, WebConfig webConfig) {
		this.articleService = articleService;
		this.labelService = labelService;
		this.webConfig = webConfig;
	}

	public HomeViewModel(ArticleService articleService, LabelService labelService, WebConfig webConfig, Integer page) {
		this(articleService, labelService, webConfig);
		this.page = page;
	}

	public void init() {
		int from = (page - 1) * webConfig.getBlogNumPerPage();
		int to = from + webConfig.getBlogNumPerPage();
		articles = articleService.findDateSortedArticles(from, to);
		newArticles = articleService.findNewArticles(webConfig.getNewBlogNum());
		labels = labelService.findAll();

		numOfArticle = articleService.size();
	}

	public Integer getNumOfArticle() {
		return numOfArticle;
	}

	public void setNumOfArticle(Integer numOfArticle) {
		this.numOfArticle = numOfArticle;
	}

	public List<ArticleDO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDO> articles) {
		this.articles = articles;
	}

	public List<ArticleDO> getNewArticles() {
		return newArticles;
	}

	public void setNewArticles(List<ArticleDO> newArticles) {
		this.newArticles = newArticles;
	}

	public List<LabelDO> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelDO> labels) {
		this.labels = labels;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public WebConfig getWebConfig() {
		return webConfig;
	}

	public void setWebConfig(WebConfig webConfig) {
		this.webConfig = webConfig;
	}
}
