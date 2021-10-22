/** 
 * projectName: gray-blog 
 * fileName: ListenerPoints.java 
 * packageName: com.blog.gray.listener.listenerpoint 
 * date: Oct 21, 20212:19:20 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.listener.listenerpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.blog.gray.annotation.ListenPoint;
import com.blog.gray.annotation.ListenPoints;
import com.blog.gray.annotation.UpdateListenPoint;
import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.RedisUtil;

/**
 * @title: ListenerPoints.java
 * @package com.blog.gray.listener.listenerpoint
 * @description: 
 * @author: Zjh
 * @date: Oct 21, 2021 2:19:20 PM 
 * @version: V1.0   
 */
@ListenPoints
public class ArticleListenerPoints {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@UpdateListenPoint(table = {"t_article"})
	public void updateArticleEvent(RowData rowData) {
		List<Column> columns = rowData.getAfterColumnsList();
		for (Column col : columns) {
			if (col.getName().equals("id")) {
				redisUtil.del(RedisKeyConstant.SINGLE_ARTICLE + col.getValue());
				break;
			}
		}
	}
	
	@ListenPoint(table = {"t_article"}, eventType = {CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
	public void insertOrDeleteArticleEvent(RowData rowData) {
		redisUtil.del(RedisKeyConstant.ALL_ARTICLE_ID);
	}

}
