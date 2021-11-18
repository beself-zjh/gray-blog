/** 
 * projectName: gray-blog 
 * fileName: CanalClientConfig.java 
 * packageName: com.blog.gray.config 
 * date: Oct 18, 20213:45:25 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.canal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.blog.gray.canal.client.CanalClient;
import com.blog.gray.canal.client.DefaultCanalClient;
import com.blog.gray.canal.client.CanalConnectorFactory;
import com.blog.gray.canal.listener.CanalListenerFactory;

/**
 * @title: CanalClientConfig.java
 * @package com.blog.gray.config
 * @description: canal 客户端启动
 * @author: Zjh
 * @date: Oct 18, 2021 3:45:25 PM
 * @version: V1.0
 */
public class CanalClientConfiguration {

	/**
	 * @Fields canalConfig : canal配置参数
	 */
	@Autowired
	private CanalConfig canalConfig;

	/**
	 * @Fields canalConnectorFactory : canal连接器工厂
	 */
	@Autowired
	private CanalConnectorFactory canalConnectorFactory;
	
	/**
	 * @Fields canalListenerFactory : canal监听器工厂
	 */
	@Autowired
	private CanalListenerFactory canalListenerFactory;

	/**
	 * @title: canalClient
	 * @description: canal客户端自动装配&启动
	 */
	@Bean
	private CanalClient canalClient() {
		CanalClient canalClient = new DefaultCanalClient(canalConfig, canalConnectorFactory, canalListenerFactory);
		canalClient.start();
		return canalClient;
	}
}
