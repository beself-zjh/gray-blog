/** 
 * projectName: gray-blog 
 * fileName: CanalClientConfig.java 
 * packageName: com.blog.gray.config 
 * date: Oct 18, 20213:45:25 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.blog.gray.factory.CanalConnectorFactory;
import com.blog.gray.service.CanalClientService;
import com.blog.gray.service.impl.CanalClientServiceImpl;

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
	 * @title: canalClient 
	 * @description: canal客户端自动装配&启动
	 */
	@Bean
	private CanalClientService canalClient() {
		CanalClientService canalTest = new CanalClientServiceImpl(canalConnectorFactory);
		canalTest.start();
		return canalTest;
	}
}
