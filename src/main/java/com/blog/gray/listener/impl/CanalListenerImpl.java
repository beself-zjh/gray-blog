/** 
 * projectName: gray-blog 
 * fileName: CanalListener.java 
 * packageName: com.blog.gray.listener.impl 
 * date: Oct 19, 20215:03:51 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.listener.impl;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.util.StringUtils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.Message;
import com.blog.gray.config.CanalConfig;
import com.blog.gray.listener.AbstractCanalListener;
import com.blog.gray.listener.listenerpoint.ListenerPoint;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @title: CanalListener.java
 * @package com.blog.gray.listener.impl
 * @description: canal监听实现类 ，负责canal监听事件处理
 * @author: Zjh
 * @date: Oct 19, 2021 5:03:51 PM
 * @version: V1.0
 */
public class CanalListenerImpl extends AbstractCanalListener {

	/**
	 * @title: CanalListener
	 * @param canalConfig        canal配置参数
	 * @param connector          canal连接器
	 * @param annoListenerPoints 监听点注解
	 * @throws:
	 */
	public CanalListenerImpl(CanalConfig canalConfig, CanalConnector connector, List<ListenerPoint> annoListenerPoints) {
		super(canalConfig, connector, annoListenerPoints);
	}

	/**
	 * @title: distributeEvent
	 * @description: 分发处理数据库更新事件
	 * @param message 数据库更新日志解析
	 * @see com.blog.gray.listener.AbstractCanalListener#distributeEvent(com.alibaba.otter.canal.protocol.Message)
	 */
	@Override
	protected void distributeEvent(Message message) {
		List<CanalEntry.Entry> entries = message.getEntries();
		for (CanalEntry.Entry entry : entries) {
			List<CanalEntry.EntryType> ignoreEntryTypes = getIgnoreEntryTypes();
			if(ignoreEntryTypes != null &&
					ignoreEntryTypes.stream().anyMatch(t -> entry.getEntryType() == t)) {
				continue;
			}
			
			RowChange rowChange;
			try {
				rowChange = RowChange.parseFrom(entry.getStoreValue());
			} catch (InvalidProtocolBufferException e) {
				throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
			}
			
			if (rowChange.hasIsDdl() && rowChange.getIsDdl()) {
				continue;
			}
			
			for (RowData rowData : rowChange.getRowDatasList()) {
				distributeByAnno(canalConfig.getDestination(), 
						entry.getHeader().getSchemaName(),
						entry.getHeader().getTableName(), 
						rowChange.getEventType(), rowData);
			}
		}
	}
	
	protected void distributeByAnno(String destination, String schema, 
			String table, CanalEntry.EventType eventType, RowData rowData) {
		annoListenerPoints.stream().filter(getFilter(destination, schema, table, eventType)).forEach(lp -> {
			Method method = lp.getMethod();
			method.setAccessible(true);
			Object[] args = getInvokeArgs(method, eventType, rowData);
			try {
				method.invoke(lp.getTarget(), args);
			} catch (Exception e) {
				logger.error("{}: Error occurred when invoke the listener's interface! class:{}, method:{}",
                        Thread.currentThread().getName(),
                        lp.getTarget().getClass().getName(), method.getName());
			}
		});	
	}

	protected List<CanalEntry.EntryType> getIgnoreEntryTypes() {
		return Arrays.asList(CanalEntry.EntryType.TRANSACTIONBEGIN,
				CanalEntry.EntryType.TRANSACTIONEND, 
				CanalEntry.EntryType.HEARTBEAT);
	}
	
	protected Predicate<ListenerPoint> getFilter(String destination,
			String schema, String table, CanalEntry.EventType eventType) {
		Predicate<ListenerPoint> df = p -> !StringUtils.hasText(p.getAnno().destination())
				|| p.getAnno().destination().equals(destination);
		Predicate<ListenerPoint> sf = p -> p.getAnno().schema().length == 0
				|| Arrays.stream(p.getAnno().schema()).anyMatch(s -> s.equals(schema));
		Predicate<ListenerPoint> tf = p -> p.getAnno().table().length == 0
				|| Arrays.stream(p.getAnno().table()).anyMatch(t -> t.equals(table));
		Predicate<ListenerPoint> ef = p -> p.getAnno().eventType().length == 0 
				|| Arrays.stream(p.getAnno().eventType()).anyMatch(e -> e == eventType);
		
		return df.and(sf).and(tf).and(ef);
	}
	
	protected Object[] getInvokeArgs(Method method, CanalEntry.EventType eventType, RowData rowData) {
		return Arrays.stream(method.getParameterTypes())
				.map(t -> t == CanalEntry.EventType.class 
						? eventType 
						: t == RowData.class 
						? rowData : null).toArray();
	}

}
