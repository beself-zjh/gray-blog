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
import com.blog.gray.service.FileReadService;
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

	@Autowired
	private ConfigureEntity configureEntity;
	
	@Autowired
	private FileReadService fileReadService;
	
	@Autowired
	private MarkdownUtil markdownUtil;
	
	private String markdown;

	public ArticleViewModel flush(String filename) {
		
		setMarkdown(markdownUtil.markdownToHtml(fileReadService.mdFileRead(configureEntity.getArticleDirPath() + "\\" + filename + ".md")));
		
		return this;
	}
	
	public String getMarkdown() {
		return markdown;
	}
	
	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}


}
