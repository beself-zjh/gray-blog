/** 
 * projectName: gray-blog 
 * fileName: AbstrctCanalListener.java 
 * packageName: com.blog.gray.listener 
 * date: Oct 19, 20213:18:47 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.listener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.blog.gray.config.CanalConfig;
import com.blog.gray.listener.listenerpoint.ListenerPoint;

/**
 * @title: AbstrctCanalListener.java
 * @package com.blog.gray.listener
 * @description: 抽象canal监听器，监听canal消息并分发
 * @author: Zjh
 * @date: Oct 19, 2021 3:18:47 PM 
 * @version: V1.0   
 */
public abstract class AbstractCanalListener implements CanalListener {
	
	protected static Logger logger = LoggerFactory.getLogger(AbstractCanalListener.class);
	
	/**
	 * @Fields canalConfig : canal参数配置
	 */
	protected final CanalConfig canalConfig;
	
	/**
	 * @Fields connector : canal 连接器
	 */
	protected final CanalConnector connector;
	
	/**
	 * @Fields annotationListenerPoints : 所有注解监听点
	 */
	protected final List<ListenerPoint> annoListenerPoints = new ArrayList<>();
	
	/**
	 * @Fields running : 运行标记
	 */
	private volatile boolean running = true;
	
	
	public AbstractCanalListener(CanalConfig canalConfig, CanalConnector connector, List<ListenerPoint> annoListenerPoints) {
		this.canalConfig = canalConfig;
		this.connector = connector;
		if(annoListenerPoints != null)
			this.annoListenerPoints.addAll(annoListenerPoints);
	}
	
	/**   
	 * @title: run
	 * @description: 监听开始
	 * @see java.lang.Runnable#run()     
	 */
	@Override
	public void run() {
		int errorCount = canalConfig.getRetryCount();
        final long interval = canalConfig.getAcquireInterval();
        final String threadName = Thread.currentThread().getName();
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Message message = connector.getWithoutAck(canalConfig.getBatchSize());
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (logger.isDebugEnabled()) {
                    logger.debug("{}: Get message from canal server >>>>> size:{}", threadName, size);
                }
                //empty message
                if (batchId == -1 || size == 0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("{}: Empty message... sleep for {} millis", threadName, interval);
                    }
                    Thread.sleep(interval);
                } else {
                    distributeEvent(message);
                }
                // commit ack
                connector.ack(batchId);
                if (logger.isDebugEnabled()) {
                    logger.debug("{}: Ack message. batchId:{}", threadName, batchId);
                }
            } catch (CanalClientException e) {
                errorCount--;
                logger.error(threadName + ": Error occurred!! ", e);
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e1) {
                    errorCount = 0;
                }
            } catch (InterruptedException e) {
                errorCount = 0;
                connector.rollback();
            } finally {
                if (errorCount <= 0) {
                    stop();
                    logger.info("{}: Topping the client.. ", Thread.currentThread().getName());
                }
            }
        }
        stop();
	}
	
	/**
	 *@title: stop 
	 *@description: 停止运行
	 */
	private void stop() {
		this.running = false;
	}
	
	/**
	 *@title: distributeEvent 
	 *@description: 分发处理数据库更新事件
	 *@param message
	 */
	protected abstract void distributeEvent(Message message);
}
