/** 
 * projectName: gray-blog 
 * fileName: RedisScheduler.java 
 * packageName: com.blog.gray.scheduler 
 * date: Oct 25, 20219:10:21 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blog.gray.dao.ArticleRepository;
import com.blog.gray.domain.ArticleDO;
import com.blog.gray.service.ArticleService;
import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.RedisUtil;

/**
 * @title: RedisScheduler.java
 * @package com.blog.gray.scheduler
 * @description: 临时缓存定时回存数据库
 * @author: Zjh
 * @date: Oct 25, 2021 9:10:21 PM 
 * @version: V1.0   
 */
// TODO 定时器待单测， 另增加访问量显示任务  cur + deta
@Component
public class RedisScheduler {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 *@title: pageViewSave 
	 *@description: 每日0点，将当日各文章访问量更新至数据库
	 */
	@Async
	@Scheduled(cron = "0 0 0 * * ?")
	public void pageViewSave() {
		List<ArticleDO> articles = articleService.findAll();
		
		articles.forEach(article -> {
			String key = RedisKeyConstant.SINGLE_ARTICLE_PV + article.getId();
			if (redisUtil.hasKey(key)) {
				Long today = redisUtil.hllSize(key);
				article.setVisits(article.getVisits() + today);
				
				articleRepository.save(article);
				redisUtil.del(key);
			}
		});
	}
	
}
