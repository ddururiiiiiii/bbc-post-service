package com.bookbookclub.bbc_post_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.bookbookclub.bbc_post_service.client")
public class BbcPostServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BbcPostServiceApplication.class)
				.properties("spring.config.name=application-post")
				.run(args);
	}

}
