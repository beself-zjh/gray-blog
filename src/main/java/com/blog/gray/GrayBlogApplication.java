package com.blog.gray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.blog.gray.annotation.EnableCanalClient;

@SpringBootApplication
@EnableCanalClient
public class GrayBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrayBlogApplication.class, args);
	}

}
