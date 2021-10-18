/** 
 * projectName: gray-blog 
 * fileName: CanalConnectorFactory.java 
 * packageName: com.blog.gray.factory 
 * date: Oct 18, 20214:47:15 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.factory;

import com.alibaba.otter.canal.client.CanalConnector;

/**
 * @title: CanalConnectorFactory.java
 * @package com.blog.gray.factory
 * @description: canal连接器工厂方法
 * @author: Zjh
 * @date: Oct 18, 2021 4:47:15 PM 
 * @version: V1.0   
 */
public interface CanalConnectorFactory {

	/**
	 *@title: newDefaultConnector 
	 *@description: 按照配置文件创建canal连接器
	 *@return 默认连接器
	 */
	public CanalConnector newDefaultConnector();
}
