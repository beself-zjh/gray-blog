/** 
 * projectName: gray-blog 
 * fileName: TagsViewModel.java 
 * packageName: com.blog.gray.viewmodel 
 * date: Oct 28, 20218:12:47 PM 
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
 * @title: TagsViewModel.java
 * @package com.blog.gray.viewmodel
 * @description: tags view model
 * @author: Zjh
 * @date: Oct 28, 2021 8:12:47 PM 
 * @version: V1.0   
 */
public class TagsViewModel {
	
	private ArticleService articleService;
	private LabelService labelService;
	private WebConfig webConfig;

	private Integer numOfArticle;
	private List<ArticleDO> articles;
	private List<LabelDO> labels;
	
	private Integer page = 1;
	private Integer labelId = -1;


	public TagsViewModel(ArticleService articleService, LabelService labelService, WebConfig webConfig) {
		this.articleService = articleService;
		this.labelService = labelService;
		this.webConfig = webConfig;
	}

	public TagsViewModel(ArticleService articleService, LabelService labelService, WebConfig webConfig, Integer id, Integer page) {
		this(articleService, labelService, webConfig);
		this.page = page;
		this.labelId = id;
	}
	
	public void init() {
		this.numOfArticle = articleService.size();
		this.labels = labelService.findAll();
		if (labelId == -1 && labels.size() > 0)
			labelId = labels.get(0).getId();
		
		int from = (page - 1) * webConfig.getBlogNumPerPage();
		int to = from + webConfig.getBlogNumPerPage();
		articles = articleService.findDateSortedArticlesByLabel(labelId, from, to);
	}
	
	public WebConfig getWebConfig() {
		return webConfig;
	}
	public void setWebConfig(WebConfig webConfig) {
		this.webConfig = webConfig;
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
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
}
