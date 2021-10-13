package com.blog.gray.viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.ConfigureEntity;
import com.blog.gray.domain.LabelDO;
import com.blog.gray.service.ArticleCollectService;

/**
 * @title: NavigationViewModel.java
 * @package com.example.blog.viewmodel
 * @description: binding with navigation frame.
 * @author: Zjh
 * @date: Sep 6, 2020 8:26:21 PM 
 * @version: V1.0   
 */
@Service
@EnableConfigurationProperties(ConfigureEntity.class)
public class NavigationViewModel {

	/**
	 * @Fields configureEntity : The global configuration information.
	 */
	@Autowired
	private ConfigureEntity configureEntity;
	
	@Autowired
	private ArticleCollectService articleCollectService;

	private String name;       //ConfigureEntity.name
	private String profession; //ConfigureEntity.profession
	private String email;      //ConfigureEntity.email
	private String avatarPath; //ConfigureEntity.avatarPath
	private String ageOfSite;  //calculate by ConfigureEntity.siteCreatedTime
	private List<ArticleDO> newArticles; //the newest 8 articles
	private List<LabelDO> labels; //no more than 10 labels
	
	public NavigationViewModel flush() {
		//obtain configure information
		setName(configureEntity.getName());
		setProfession(configureEntity.getProfession());
		setEmail(configureEntity.getEmail());
		setAvatarPath(configureEntity.getAvatarPath());

		//update newArticles
		setNewArticles(articleCollectService.findNewArticles(8));

		//update labels
		List<LabelDO> allLabels = articleCollectService.findAllLabel();
		setLabels(allLabels.subList(0, 10 <= allLabels.size() ? 10 : allLabels.size()));

		//update ageOfSite
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date siteCreatedTime = simpleDateFormat.parse(this.configureEntity.getSiteCreatedTime());
			Date now = new Date();
			setAgeOfSite((now.getTime() - siteCreatedTime.getTime()) / (24 * 60 * 60 * 1000) + " days");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return this;
	}

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

	public String getAgeOfSite() {
		return ageOfSite;
	}

	public void setAgeOfSite(String ageOfSite) {
		this.ageOfSite = ageOfSite;
	}

	public List<ArticleDO> getNewArticles() {
		return newArticles;
	}

	public void setNewArticles(List<ArticleDO> newArticles) {
		this.newArticles = newArticles;
	}

	public List<LabelDO> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelDO> labels) {
		this.labels = labels;
	}
	
}
