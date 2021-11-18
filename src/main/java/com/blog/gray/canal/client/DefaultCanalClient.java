/** 
 * projectName: gray-blog 
 * fileName: CanalClientServiceImpl.java 
 * packageName: com.blog.gray.service.impl 
 * date: Oct 19, 20211:34:07 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.canal.client;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.core.annotation.AnnotatedElementUtils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.blog.gray.canal.annotation.ListenPoint;
import com.blog.gray.canal.annotation.ListenPoints;
import com.blog.gray.canal.config.CanalConfig;
import com.blog.gray.canal.listener.CanalListenerFactory;
import com.blog.gray.canal.model.ListenerPoint;
import com.blog.gray.canal.util.BeanUtil;

/**
 * @title: CanalClientServiceImpl.java
 * @package com.blog.gray.service.impl
 * @description: canal 客户端
 * @author: Zjh
 * @date: Oct 19, 2021 1:34:07 PM
 * @version: V1.0
 */
public class DefaultCanalClient extends AbstractCanalClient {

	/**
	 * @Fields canalConfig : canal配置
	 */
	private CanalConfig canalConfig;

	/**
	 * @Fields executor : 线程池
	 */
	private ThreadPoolExecutor executor;

	/**
	 * @Fields annoListenerPoints : 注解监听点
	 */
	private final List<ListenerPoint> annoListenerPoints = new ArrayList<>();

	public DefaultCanalClient(CanalConfig canalConfig, CanalConnectorFactory canalConnectorFactory,
			CanalListenerFactory canalListenerFactory) {
		super(canalConnectorFactory, canalListenerFactory);
		this.canalConfig = canalConfig;
		executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(),
				Executors.defaultThreadFactory());
		initListenerPoints();
	}

	/**
	 * @title: stop
	 * @description: 客户端终止
	 * @see com.blog.gray.canal.client.AbstractCanalClient#stop()
	 */
	@Override
	public void stop() {
		executor.shutdown();
	}

	/**
	 * @title: process
	 * @description: 将监听canal的任务提交至线程池
	 * @param connector
	 * @see com.blog.gray.canal.client.AbstractCanalClient#process(com.alibaba.otter.canal.client.CanalConnector)
	 */
	@Override
	protected void process(CanalConnector connector) {
		executor.submit(canalListenerFactory
				.newDefaultCanalListener(canalConfig, connector, annoListenerPoints));
	}

	/**
	 * @title: initListenerPoints
	 * @description: 收集并初始化监听点注解
	 */
	private void initListenerPoints() {
		Map<String, Object> annoListenerMap = BeanUtil.getBeanWithAnnotation(ListenPoints.class);

		if (annoListenerMap != null) {
			for (Object target : annoListenerMap.values()) {
				Method[] methods = target.getClass().getDeclaredMethods();
				if (methods != null && methods.length > 0) {
					for (Method method : methods) {
						//ListenPoint anno = AnnotationUtils.findAnnotation(method, ListenPoint.class);
						ListenPoint anno = AnnotatedElementUtils.findMergedAnnotation(method, ListenPoint.class);
						if (anno != null) {
							annoListenerPoints.add(new ListenerPoint(target, method, anno));
						}
					}
				}
			}
		}
	}

}
