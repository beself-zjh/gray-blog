/** 
 * projectName: gray-blog 
 * fileName: ListenerPoints.java 
 * packageName: com.blog.gray.listener.listenerpoint 
 * date: Oct 21, 20212:19:20 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.listenerpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.blog.gray.canal.annotation.DeleteListenPoint;
import com.blog.gray.canal.annotation.InsertListenPoint;
import com.blog.gray.canal.annotation.ListenPoints;
import com.blog.gray.canal.annotation.UpdateListenPoint;
import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.RedisUtil;
import com.blog.gray.viewmodel.ArchivesViewModel;

/**
 * @title: ListenerPoints.java
 * @package com.blog.gray.listener.listenerpoint
 * @description: 文章增删改监听点，删除对应缓存
 * @author: Zjh
 * @date: Oct 21, 2021 2:19:20 PM 
 * @version: V1.0   
 */
@ListenPoints
public class ArticleListenerPoints {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ArchivesViewModel archivesViewModel;
	
	@UpdateListenPoint(table = {"t_article"})
	public void updateArticleEvent(RowData rowData) {
		List<Column> columns = rowData.getAfterColumnsList();
		for (Column col : columns) {
			if (col.getName().equals("id")) {
				redisUtil.del(RedisKeyConstant.SINGLE_ARTICLE + col.getValue());
				break;
			}
		}
		// 归档页面刷新  TODO 此行代码不优雅
		archivesViewModel.setFlag(true);
	}
	
	@InsertListenPoint(table = {"t_article"})
	public void insertArticleEvent(RowData rowData) {
		redisUtil.del(RedisKeyConstant.ALL_ARTICLE_ID);
		
		archivesViewModel.setFlag(true);
	}
	
	@DeleteListenPoint(table = {"t_article"})
	public void deleteArticleEvent(RowData rowData) {
		redisUtil.del(RedisKeyConstant.ALL_ARTICLE_ID);
		
		List<Column> columns = rowData.getBeforeColumnsList();
		for (Column col : columns) {
			if (col.getName().equals("id")) {
				redisUtil.del(RedisKeyConstant.SINGLE_ARTICLE + col.getValue());
				redisUtil.del(RedisKeyConstant.SINGLE_ARTICLE_PV + col.getValue());
				break;
			}
		}
		
		archivesViewModel.setFlag(true);
	}

}
