/** 
 * projectName: gray-blog 
 * fileName: CanalConnectorFactoryImpl.java 
 * packageName: com.blog.gray.factory.impl 
 * date: Oct 18, 20214:51:39 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.factory.impl;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.blog.gray.config.CanalConfig;
import com.blog.gray.factory.CanalConnectorFactory;

/**
 * @title: CanalConnectorFactoryImpl.java
 * @package com.blog.gray.factory.impl
 * @description: canal连接器工厂方法
 * @author: Zjh
 * @date: Oct 18, 2021 4:51:39 PM 
 * @version: V1.0   
 */
@Service
public class CanalConnectorFactoryImpl implements CanalConnectorFactory {

	@Autowired
	private CanalConfig canalConfig;
	/**   
	 * @title: newDefaultConnector
	 * @description: 按照配置参数创建canal连接器
	 * @return 默认连接器创建
	 * @see com.blog.gray.factory.CanalConnectorFactory#newDefaultConnector()     
	 */
	@Override
	public CanalConnector newDefaultConnector() {
		CanalConnector connector = CanalConnectors.newSingleConnector(
				new InetSocketAddress(canalConfig.getHost(), canalConfig.getPort()),
				canalConfig.getDestination(), 
				canalConfig.getUserName(), 
				canalConfig.getPassword()
				);

		connector.connect();
		if (!StringUtils.hasText(canalConfig.getFilter())) {
			connector.subscribe(canalConfig.getFilter());
		} else {
			connector.subscribe();
		}

		connector.rollback();
        return connector;
	}

}
