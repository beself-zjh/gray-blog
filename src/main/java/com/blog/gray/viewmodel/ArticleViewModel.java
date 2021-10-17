/** 
 * projectName: blog 
 * fileName: ArticleViewModel.java 
 * packageName: com.example.blog.viewmodel 
 * date: Sep 10, 202011:03:21 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.domain.ConfigureEntity;
import com.blog.gray.service.FileService;
import com.blog.gray.util.MarkdownUtil;

/**
 * @title: ArticleViewModel.java
 * @package com.example.blog.viewmodel
 * @description: binding with article page.
 * @author: Zjh
 * @date: Sep 10, 2020 11:03:21 AM 
 * @version: V1.0   
 */
@Service
public class ArticleViewModel {

	/**
	 * @Fields markdown : 文章内容
	 */
	private String markdown;
	
	private Long visits;
	
	@Autowired
	private ConfigureEntity configureEntity;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private MarkdownUtil markdownUtil;
	

	public ArticleViewModel flush(String filename) {
		String path = configureEntity.getArticleDirPath() + "\\" + filename + ".md";
		String markdownText = fileService.mdFileRead(path);
		setMarkdown(markdownUtil.markdownToHtml(markdownText));
		
		return this;
	}
	
	public String getMarkdown() {
		return markdown;
	}
	
	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}


}
