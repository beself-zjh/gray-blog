/** 
 * projectName: gray-blog 
 * fileName: RawBlogDO.java 
 * packageName: com.blog.gray.domain 
 * date: Oct 31, 20214:25:47 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.domain;

import java.util.List;

/**
 * @title: RawBlogDO.java
 * @package com.blog.gray.domain
 * @description: TODO
 * @author: Zjh
 * @date: Oct 31, 2021 4:25:47 PM 
 * @version: V1.0   
 */
public class RawBlogDO {
	private String type;
	private String title;
	private String content;
	private List<Integer> labels;
	
	public List<Integer> getLabels() {
		return labels;
	}
	public void setLabels(List<Integer> labels) {
		this.labels = labels;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
