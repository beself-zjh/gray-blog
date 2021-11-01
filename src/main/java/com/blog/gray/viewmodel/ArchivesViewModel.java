/** 
 * projectName: gray-blog 
 * fileName: ArchivesViewModel.java 
 * packageName: com.blog.gray.viewmodel 
 * date: Oct 29, 202112:55:31 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.domain.ArticleDO;
import com.blog.gray.service.ArticleService;

/**
 * @title: ArchivesViewModel.java
 * @package com.blog.gray.viewmodel
 * @description: archives view model
 * @author: Zjh
 * @date: Oct 29, 2021 12:55:31 PM 
 * @version: V1.0   
 */
@Service
public class ArchivesViewModel {

	@Autowired
	private ArticleService articleService;
	
	private Integer numOfArticle;
	private Map<Integer, List<ArticleDO>> archives;
	
	private Boolean flag = true;
	
	public ArchivesViewModel init() {
		if (flag) {
			synchronized (flag) {
				if (flag) {
					numOfArticle = articleService.size();
					
					archives = new LinkedHashMap<>();
					Calendar calendar = Calendar.getInstance();
					List<ArticleDO> articles = articleService.findDateSortedAllArticles();
					articles.forEach(article -> {		
						calendar.setTime(article.getCreatedTime());
						Integer key = calendar.get(Calendar.YEAR);
						if (archives.containsKey(key)) {
							archives.get(key).add(article);
						} else {
							List<ArticleDO> value = new ArrayList<>();
							value.add(article);
							archives.put(key, value);
						}
					});
					flag = false;
				}
			}
		}
		
		return this;
	}
	
	public Integer getNumOfArticle() {
		return numOfArticle;
	}

	public void setNumOfArticle(Integer numOfArticle) {
		this.numOfArticle = numOfArticle;
	}

	public Map<Integer, List<ArticleDO>> getArchives() {
		return archives;
	}

	public void setArchives(Map<Integer, List<ArticleDO>> archives) {
		this.archives = archives;
	}

	public void setFlag(Boolean flag) {
		synchronized (this.flag) {
			this.flag = flag;
		}
	}
	
}
