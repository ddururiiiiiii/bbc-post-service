package com.bookbookclub.bbc_post_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.bookbookclub.bbc_post_service.global.client")
public class BbcPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbcPostServiceApplication.class, args);
	}

}
