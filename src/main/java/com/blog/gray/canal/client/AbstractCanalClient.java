/** 
 * projectName: gray-blog 
 * fileName: AbstractCanalClientService.java 
 * packageName: com.blog.gray.service 
 * date: Oct 19, 20212:04:15 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.blog.gray.canal.listener.CanalListenerFactory;

/**
 * @title: AbstractCanalClientService.java
 * @package com.blog.gray.service
 * @description: 抽象canal客户端，控制客户端的启动与结束
 * @author: Zjh
 * @date: Oct 19, 2021 2:04:15 PM
 * @version: V1.0
 */
public abstract class AbstractCanalClient implements CanalClient {

	/**
	 * @Fields canalConnectorFactory : canal连接器工厂
	 */
	protected final CanalConnectorFactory canalConnectorFactory;
	
	/**
	 * @Fields canalListenerFactory : canal监听器工厂
	 */
	protected final CanalListenerFactory canalListenerFactory;

	public AbstractCanalClient(CanalConnectorFactory canalConnectorFactory,
			CanalListenerFactory canalListenerFactory) {
		this.canalConnectorFactory = canalConnectorFactory;
		this.canalListenerFactory = canalListenerFactory;
	}

	/**
	 * @title: start
	 * @description: 启动客户端 ，获取连接器并对其操作
	 * @see com.blog.gray.canal.client.CanalClient#start()
	 */
	@Override
	public void start() {
		process(canalConnectorFactory.newDefaultConnector());
	}

	/**
	 * @title: stop
	 * @description: 终止客户端
	 * @see com.blog.gray.canal.client.CanalClient#stop()
	 */
	@Override
	public abstract void stop();

	/**
	 * @title: process
	 * @description: 操作连接器，由子类实现
	 */
	protected abstract void process(CanalConnector connector);
}
