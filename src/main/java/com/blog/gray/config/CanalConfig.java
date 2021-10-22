/** 
 * projectName: gray-blog 
 * fileName: CanalConfig.java 
 * packageName: com.blog.gray.config 
 * date: Oct 18, 20213:35:43 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @title: CanalConfig.java
 * @package com.blog.gray.config
 * @description: canal 参数配置
 * @author: Zjh
 * @date: Oct 18, 2021 3:35:43 PM
 * @version: V1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "canal.client")
public class CanalConfig {

	/**
	 * @Fields host : 目标主机地址
	 */
	private String host;

	/**
	 * @Fields port : 目标主机端口
	 */
	private int port;

	/**
	 * @Fields userName : canal用户名
	 */
	private String userName;

	/**
	 * @Fields password : canal密码
	 */
	private String password;

	/**
	 * @Fields filter : 目标数据库 / 表
	 */
	private String filter;

	/**
	 * @Fields destination : topic
	 */
	private String destination;

	/**
	 * @Fields retryCount : 报错重试次数
	 */
	private int retryCount;

	/**
	 * @Fields acquireInterval : 监听间隔
	 */
	private long acquireInterval;

	/**
	 * @Fields batchSize : 单次提取日志数量
	 */
	private int batchSize;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public long getAcquireInterval() {
		return acquireInterval;
	}

	public void setAcquireInterval(long acquireInterval) {
		this.acquireInterval = acquireInterval;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

}
