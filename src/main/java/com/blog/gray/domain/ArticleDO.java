/** 
 * projectName: gray-blog 
 * fileName: ArticleDO.java 
 * packageName: com.blog.gray.model 
 * date: Oct 6, 20213:08:35 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @title: ArticleDO.java
 * @package com.blog.gray.model
 * @description: 文章 数据对象
 * @author: Zjh
 * @date: Oct 6, 2021 3:08:35 PM 
 * @version: V1.0   
 */
@Entity
@Table(name = "t_article")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class ArticleDO {	

	/**
	 * @Fields id : 唯一标识，自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * @Fields title : 标题
	 */
	@Column
	public String title;
	
	/**
	 * @Fields lastUpdateTime : 上次更新时间
	 */
	@Column 
	private Date lastUpdateTime;
	
	/**
	 * @Fields createdTime : 创建时间
	 */
	@Column
	private Date createdTime;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "articledo_labeldo", joinColumns = @JoinColumn(name = "article_id"),
	inverseJoinColumns = @JoinColumn(name = "label_id"))
	private List<LabelDO> labels;
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public List<LabelDO> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelDO> labels) {
		this.labels = labels;
	}

	@Override
	public String toString() {
		return "ArticleDO{" + title + "}";
	}
}
