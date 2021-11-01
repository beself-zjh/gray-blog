/** 
 * projectName: gray-blog 
 * fileName: HeaderViewModel.java 
 * packageName: com.blog.gray.viewmodel 
 * date: Oct 29, 202112:12:52 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.config.WebConfig;

/**
 * @title: HeaderViewModel.java
 * @package com.blog.gray.viewmodel
 * @description: header view model
 * @author: Zjh
 * @date: Oct 29, 2021 12:12:52 PM 
 * @version: V1.0   
 */
@Service
public class HeaderViewModel {

	@Autowired
	private WebConfig webConfig;

	public WebConfig getWebConfig() {
		return webConfig;
	}

	public void setWebConfig(WebConfig webConfig) {
		this.webConfig = webConfig;
	}
		
}
