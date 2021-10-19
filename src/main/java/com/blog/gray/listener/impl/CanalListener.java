/** 
 * projectName: gray-blog 
 * fileName: CanalListener.java 
 * packageName: com.blog.gray.listener.impl 
 * date: Oct 19, 20215:03:51 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.listener.impl;

import java.util.List;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.blog.gray.config.CanalConfig;
import com.blog.gray.listener.AbstractCanalListener;
import com.blog.gray.listener.listenerpoint.ListenerPoint;

/**
 * @title: CanalListener.java
 * @package com.blog.gray.listener.impl
 * @description: canal监听实现类 ，负责canal监听事件处理
 * @author: Zjh
 * @date: Oct 19, 2021 5:03:51 PM 
 * @version: V1.0   
 */
public class CanalListener extends AbstractCanalListener {

	/**   
	 * @title: CanalListener   
	 * @param canalConfig canal配置参数
	 * @param connector canal连接器
	 * @param annoListenerPoints 监听点注解 
	 * @throws:   
	 */
	public CanalListener(CanalConfig canalConfig, CanalConnector connector, List<ListenerPoint> annoListenerPoints) {
		super(canalConfig, connector, annoListenerPoints);
	}

	/**   
	 * @title: distributeEvent
	 * @description: 分发处理数据库更新事件
	 * @param message 数据库更新日志解析
	 * @see com.blog.gray.listener.AbstractCanalListener#distributeEvent(com.alibaba.otter.canal.protocol.Message)     
	 */
	@Override
	protected void distributeEvent(Message message) {
		// TODO Auto-generated method stub
		
	}

}
