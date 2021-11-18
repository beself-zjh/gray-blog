/** 
 * projectName: gray-blog 
 * fileName: CanalClientService.java 
 * packageName: com.blog.gray.service 
 * date: Oct 18, 20214:26:46 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.canal.client;

/**
 * @title: CanalClientService.java
 * @package com.blog.gray.service
 * @description: canal客户端
 * @author: Zjh
 * @date: Oct 18, 2021 4:26:46 PM
 * @version: V1.0
 */
public interface CanalClient {

	/**
	 * @title: start
	 * @description: 客户端启动
	 */
	public void start();

	/**
	 * @title: stop
	 * @description: 客户端停止
	 */
	public void stop();
}
