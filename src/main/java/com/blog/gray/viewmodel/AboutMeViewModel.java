/** 
 * projectName: gray-blog 
 * fileName: AboutMeViewModel.java 
 * packageName: com.blog.gray.viewmodel 
 * date: Oct 29, 20214:17:43 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.config.WebConfig;

/**
 * @title: AboutMeViewModel.java
 * @package com.blog.gray.viewmodel
 * @description: aboutMe view model
 * @author: Zjh
 * @date: Oct 29, 2021 4:17:43 PM 
 * @version: V1.0   
 */
@Service
public class AboutMeViewModel {

	@Autowired
	private WebConfig webConfig;

	public WebConfig getWebConfig() {
		return webConfig;
	}

	public void setWebConfig(WebConfig webConfig) {
		this.webConfig = webConfig;
	}
	
}
