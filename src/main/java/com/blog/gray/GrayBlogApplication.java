package com.blog.gray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.blog.gray.annotation.EnableCanalClient;

@SpringBootApplication
@EnableCanalClient
@EnableScheduling
@EnableAsync
public class GrayBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrayBlogApplication.class, args);
	}

}
