/** 
 * projectName: gray-blog 
 * fileName: WebConfig.java 
 * packageName: com.blog.gray.config 
 * date: Oct 28, 202111:32:17 AM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @title: WebConfig.java
 * @package com.blog.gray.config
 * @description: TODO
 * @author: Zjh
 * @date: Oct 28, 2021 11:32:17 AM
 * @version: V1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "web.config")
public class WebConfig {
	/* 个人信息 */
	private String name;
	private String github;
	private String weChat;
	private String qq;
	private String email;
	private List<String> hobby;
	private List<String> character;

	/* 存储配置 */
	private String blogDirPath;
	private String blogImgDirPath;

	/* 网站布局 */
	private String webName;
	private String introduction;
	private Integer newBlogNum;
	private Integer blogNumPerPage;
	private String province;
	private String icp;
	private String code;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBlogDirPath() {
		return blogDirPath;
	}

	public void setBlogDirPath(String blogDirPath) {
		this.blogDirPath = blogDirPath;
	}

	public String getBlogImgDirPath() {
		return blogImgDirPath;
	}

	public void setBlogImgDirPath(String blogImgDirPath) {
		this.blogImgDirPath = blogImgDirPath;
	}

	public Integer getNewBlogNum() {
		return newBlogNum;
	}

	public void setNewBlogNum(Integer newBlogNum) {
		this.newBlogNum = newBlogNum;
	}

	public Integer getBlogNumPerPage() {
		return blogNumPerPage;
	}

	public void setBlogNumPerPage(Integer blogNumPerPage) {
		this.blogNumPerPage = blogNumPerPage;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public List<String> getHobby() {
		return hobby;
	}

	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}

	public List<String> getCharacter() {
		return character;
	}

	public void setCharacter(List<String> character) {
		this.character = character;
	}
	
	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}
}
