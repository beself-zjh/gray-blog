/** 
 * projectName: gray-blog 
 * fileName: CanalListenerFactoryImpl.java 
 * packageName: com.blog.gray.factory.impl 
 * date: Oct 27, 202112:11:58 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.factory.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.otter.canal.client.CanalConnector;
import com.blog.gray.config.CanalConfig;
import com.blog.gray.factory.CanalListenerFactory;
import com.blog.gray.listener.CanalListener;
import com.blog.gray.listener.impl.CanalListenerImpl;
import com.blog.gray.listener.listenerpoint.ListenerPoint;

/**
 * @title: CanalListenerFactoryImpl.java
 * @package com.blog.gray.factory.impl
 * @description: canal监听器工厂
 * @author: Zjh
 * @date: Oct 27, 2021 12:11:58 PM 
 * @version: V1.0   
 */
@Service
public class CanalListenerFactoryImpl implements CanalListenerFactory {

	/**   
	 * @title: newDefaultCanalListener
	 * @description: 创建默认canal监听器
	 * @param canalConfig
	 * @param connector
	 * @param annoListenerPoints
	 * @return CanalListener
	 * @see com.blog.gray.factory.CanalListenerFactory#newDefaultCanalListener(com.blog.gray.config.CanalConfig, com.alibaba.otter.canal.client.CanalConnector, java.util.List)     
	 */
	@Override
	public CanalListener newDefaultCanalListener(CanalConfig canalConfig, CanalConnector connector,
			List<ListenerPoint> annoListenerPoints) {
		return new CanalListenerImpl(canalConfig, connector, annoListenerPoints);
	}

}
