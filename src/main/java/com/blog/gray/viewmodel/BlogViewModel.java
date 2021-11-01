/** 
 * projectName: gray-blog 
 * fileName: BlogViewModel.java 
 * packageName: com.blog.gray.viewmodel 
 * date: Oct 29, 20215:28:05 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import com.blog.gray.config.WebConfig;
import com.blog.gray.domain.ArticleDO;
import com.blog.gray.service.ArticleService;
import com.blog.gray.service.FileService;
import com.blog.gray.util.MarkdownUtil;

/**
 * @title: BlogViewModel.java
 * @package com.blog.gray.viewmodel
 * @description: blog view model
 * @author: Zjh
 * @date: Oct 29, 2021 5:28:05 PM 
 * @version: V1.0   
 */
public class BlogViewModel {

	private FileService fileService;
	private ArticleService articleService;
	private MarkdownUtil markdownUtil;
	private WebConfig webConfig;
	
	private ArticleDO article;
	private String content;

	private Integer articleId;
	
	public BlogViewModel(ArticleService articleService, WebConfig webConfig, 
			FileService fileService, MarkdownUtil markdownUtil, Integer id) {
		this.articleService = articleService;
		this.webConfig = webConfig;
		this.fileService = fileService;
		this.markdownUtil = markdownUtil;
		this.articleId = id;
	}
	
	public void init() {
		article = articleService.findById(articleId);
		
		String filename = articleId.toString();
		String path = webConfig.getBlogDirPath() + "\\" + filename + ".md";
		String markdownText = fileService.mdFileRead(path);
		//content = markdownUtil.markdownToHtml(markdownText);
		content = markdownText;
	}

	public WebConfig getWebConfig() {
		return webConfig;
	}

	public void setWebConfig(WebConfig webConfig) {
		this.webConfig = webConfig;
	}

	public ArticleDO getArticle() {
		return article;
	}

	public void setArticle(ArticleDO article) {
		this.article = article;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
