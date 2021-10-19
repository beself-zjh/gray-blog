/** 
 * projectName: gray-blog 
 * fileName: CanalClientServiceImpl.java 
 * packageName: com.blog.gray.service.impl 
 * date: Oct 19, 20211:34:07 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.otter.canal.client.CanalConnector;
import com.blog.gray.CanalTest;
import com.blog.gray.config.CanalConfig;
import com.blog.gray.factory.CanalConnectorFactory;
import com.blog.gray.listener.listenerpoint.ListenerPoint;
import com.blog.gray.service.AbstractCanalClientService;

/**
 * @title: CanalClientServiceImpl.java
 * @package com.blog.gray.service.impl
 * @description: canal 客户端
 * @author: Zjh
 * @date: Oct 19, 2021 1:34:07 PM 
 * @version: V1.0   
 */
public class CanalClientServiceImpl extends AbstractCanalClientService {
	
	@Autowired
	private CanalConfig canalConfig;
	
	/**
	 * @Fields executor : 线程池
	 */
	private ThreadPoolExecutor executor;
	
	/**
	 * @Fields annoListenerPoints : 注解监听点
	 */
	private final List<ListenerPoint> annoListenerPoints = new ArrayList<>();
	
	public CanalClientServiceImpl(CanalConnectorFactory canalConnectorFactory) {
		super(canalConnectorFactory);
		executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 
				60L, TimeUnit.SECONDS, new SynchronousQueue<>(), 
				Executors.defaultThreadFactory());
		initListenerPoints();
	}

	/**   
	 * @title: stop
	 * @description: 客户端终止
	 * @see com.blog.gray.service.AbstractCanalClientService#stop()     
	 */
	@Override
	public void stop() {
		executor.shutdown();
	}

	/**   
	 * @title: process
	 * @description: 将监听canal的任务提交至线程池
	 * @param connector   
	 * @see com.blog.gray.service.AbstractCanalClientService#process(com.alibaba.otter.canal.client.CanalConnector)     
	 */
	@Override
	protected void process(CanalConnector connector) {
		executor.submit(new CanalTest()); // TODO 工厂创建
	}
	
	
	/**
	 *@title: initListenerPoints 
	 *@description: 收集并初始化监听点注解
	 */
	private void initListenerPoints() {
		
	}

}
