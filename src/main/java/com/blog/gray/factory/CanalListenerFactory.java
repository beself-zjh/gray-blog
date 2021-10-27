/** 
 * projectName: gray-blog 
 * fileName: CanalListenerFactory.java 
 * packageName: com.blog.gray.factory 
 * date: Oct 27, 202112:06:26 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.factory;

import java.util.List;

import com.alibaba.otter.canal.client.CanalConnector;
import com.blog.gray.config.CanalConfig;
import com.blog.gray.listener.CanalListener;
import com.blog.gray.listener.listenerpoint.ListenerPoint;

/**
 * @title: CanalListenerFactory.java
 * @package com.blog.gray.factory
 * @description: canal监听器工厂
 * @author: Zjh
 * @date: Oct 27, 2021 12:06:26 PM 
 * @version: V1.0   
 */
public interface CanalListenerFactory {

	/**
	 *@title: newDefaultCanalListener 
	 *@description: 创建默认canal监听器
	 *@param canalConfig
	 *@param connector
	 *@param annoListenerPoints
	 */
	public CanalListener newDefaultCanalListener(CanalConfig canalConfig, CanalConnector connector, List<ListenerPoint> annoListenerPoints);
	
}
