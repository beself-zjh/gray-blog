/** 
 * projectName: gray-blog 
 * fileName: LabelDO.java 
 * packageName: com.blog.gray.domain 
 * date: Oct 6, 20214:15:11 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @title: LabelDO.java
 * @package com.blog.gray.domain
 * @description: 标签 数据对象
 * @author: Zjh
 * @date: Oct 6, 2021 4:15:11 PM
 * @version: V1.0
 */
@Entity
@Table(name = "t_label")
public class LabelDO {

	/**
	 * @Fields id : 唯一标识，自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * @Fields label : 标签名
	 */
	@Column(unique = true)
	private String label;

	/**
	 * @Fields count : 包含文章的数量
	 */
	@Column
	private Integer count;

	/**
	 * @Fields articleId : 关联文章
	 */
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "labels")
	private List<ArticleDO> articles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "LabelDO{}";
	}
}
