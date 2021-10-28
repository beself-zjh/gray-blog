/** 
 * projectName: gray-blog 
 * fileName: FooterViewModel.java 
 * packageName: com.blog.gray.viewmodel 
 * date: Oct 28, 20218:05:50 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.config.WebConfig;

/**
 * @title: FooterViewModel.java
 * @package com.blog.gray.viewmodel
 * @description: 页脚view model
 * @author: Zjh
 * @date: Oct 28, 2021 8:05:50 PM 
 * @version: V1.0   
 */
@Service
public class FooterViewModel {
	
	@Autowired
	private WebConfig webConfig;

	public WebConfig getWebConfig() {
		return webConfig;
	}

	public void setWebConfig(WebConfig webConfig) {
		this.webConfig = webConfig;
	}

}
