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

import com.blog.gray.CanalTest;

/**
 * @title: CanalClientConfig.java
 * @package com.blog.gray.config
 * @description: canal 客户端启动
 * @author: Zjh
 * @date: Oct 18, 2021 3:45:25 PM 
 * @version: V1.0   
 */
public class CanalClientConfig {
	
	@Autowired
	private CanalConfig canalConfig;
	
	@Bean
	private CanalTest canalClient() {
		CanalTest canalTest = new CanalTest();
		canalTest.start();
		return canalTest;
	}
}
