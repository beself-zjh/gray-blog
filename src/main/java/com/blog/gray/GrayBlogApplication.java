package com.blog.gray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.blog.gray.canal.annotation.EnableCanalClient;
import com.blog.gray.config.WebConfig;
import com.blog.gray.fileserver.config.FileUploadConfig;

@SpringBootApplication
@EnableCanalClient
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties({WebConfig.class, FileUploadConfig.class})
public class GrayBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrayBlogApplication.class, args);
	}

}
