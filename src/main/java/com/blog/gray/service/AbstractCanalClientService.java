/** 
 * projectName: gray-blog 
 * fileName: AbstractCanalClientService.java 
 * packageName: com.blog.gray.service 
 * date: Oct 19, 20212:04:15 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service;

import com.alibaba.otter.canal.client.CanalConnector;
import com.blog.gray.factory.CanalConnectorFactory;

/**
 * @title: AbstractCanalClientService.java
 * @package com.blog.gray.service
 * @description: 抽象canal客户端，控制客户端的启动与结束
 * @author: Zjh
 * @date: Oct 19, 2021 2:04:15 PM
 * @version: V1.0
 */
public abstract class AbstractCanalClientService implements CanalClientService {

	/**
	 * @Fields canalConnectorFactory : canal连接器工厂
	 */
	protected final CanalConnectorFactory canalConnectorFactory;

	public AbstractCanalClientService(CanalConnectorFactory canalConnectorFactory) {
		this.canalConnectorFactory = canalConnectorFactory;
	}

	/**
	 * @title: start
	 * @description: 启动客户端 ，获取连接器并对其操作
	 * @see com.blog.gray.service.CanalClientService#start()
	 */
	@Override
	public void start() {
		process(canalConnectorFactory.newDefaultConnector());
	}

	/**
	 * @title: stop
	 * @description: 终止客户端
	 * @see com.blog.gray.service.CanalClientService#stop()
	 */
	@Override
	public abstract void stop();

	/**
	 * @title: process
	 * @description: 操作连接器，由子类实现
	 */
	protected abstract void process(CanalConnector connector);
}
