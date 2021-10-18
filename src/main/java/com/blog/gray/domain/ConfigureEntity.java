package com.blog.gray.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @title: ConfigureEntity.java
 * @package com.example.blog.domain
 * @description: Global configuration information from application.properties.
 * @author: Zjh
 * @date: Sep 6, 2020 8:37:35 PM 
 * @version: V1.0   
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix="config")
public class ConfigureEntity {
	
	private String name;           //config.name
	private String profession;     //config.profession
	private String email;          //config.email
	private String avatarPath;     //config.avatarPath
	private String siteCreatedTime;//config.siteCreatedTime
	private String articleDirPath; //config.articleDirPath

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getSiteCreatedTime() {
		return siteCreatedTime;
	}

	public void setSiteCreatedTime(String siteCreatedTime) {
		this.siteCreatedTime = siteCreatedTime;
	}
	
	public String getArticleDirPath() {
		return articleDirPath;
	}

	public void setArticleDirPath(String articleDirPath) {
		this.articleDirPath = articleDirPath;
	}
}
