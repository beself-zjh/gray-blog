/** 
 * projectName: gray-blog 
 * fileName: Comment.java 
 * packageName: com.blog.gray.domain 
 * date: Nov 10, 20217:17:41 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.domain;

import java.sql.Date;
import java.util.Calendar;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @title: Comment.java
 * @package com.blog.gray.domain
 * @description: 评论
 * @author: Zjh
 * @date: Nov 10, 2021 7:17:41 PM 
 * @version: V1.0   
 */
@Entity
@Table(name = "t_review")
public class ReviewDO implements Comparable<ReviewDO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer articleId;
	
	@Column
	private String content;
	
	@Column
	private Date date = new Date(Calendar.getInstance().getTimeInMillis());
	
	@Column
	private String reviewerName = "Anonymous";
	
	@Column
	private String reviewerEmail;
	
	@Column
	private Boolean isReply = false;
	
	@Column
	private Integer replyId = -1;
	
	private TreeSet<ReviewDO> replys = new TreeSet<ReviewDO>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getReviewerEmail() {
		return reviewerEmail;
	}

	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}

	public Boolean getIsReply() {
		return isReply;
	}

	public void setIsReply(Boolean isReply) {
		this.isReply = isReply;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public TreeSet<ReviewDO> getReplys() {
		return replys;
	}

	/**  
	 * @title: addReply
	 * @description: 添加回复
	 */
	public void addReply(ReviewDO reply) {
		if (this.replys == null)
			this.replys = new TreeSet<ReviewDO>();
		this.replys.add(reply);
	}
	
	/**   
	 * @title: compareTo
	 * @description: 比较  用于排序
	 * @param o
	 * @return   
	 * @see java.lang.Comparable#compareTo(java.lang.Object)     
	 */
	@Override
	public int compareTo(ReviewDO o) {
		boolean before = date.before(o.getDate());
		boolean after = date.after(o.getDate());
			
		if (before) return -1;
		if (after) return 1;
		
		return id - o.getId();
	}

}
