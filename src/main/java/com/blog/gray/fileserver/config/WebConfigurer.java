/** 
 * projectName: gray-blog 
 * fileName: WebConfigurer.java 
 * packageName: com.blog.gray.fileserver.config 
 * date: Nov 17, 20213:39:12 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.fileserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @title: WebConfigurer.java
 * @package com.blog.gray.fileserver.config
 * @description: 虚拟路径映射
 * @author: Zjh
 * @date: Nov 17, 2021 3:39:12 PM 
 * @version: V1.0   
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
	
	public final String virtualImagePath = "/images/";
	
	@Autowired
	private FileUploadConfig fileUploadConfig;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(virtualImagePath + "**")
                .addResourceLocations("file:" + fileUploadConfig.getImgUploadPath());
    }
}
