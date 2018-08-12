package com.eden.hao.spirngcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // 告诉 Spring Boot 应用我是一个 Eureka 服务器端的启动类, 接收其他微服务注册进来
public class EurekaServer7002_App {

	public static void main(String[] args) {
		
		SpringApplication.run(EurekaServer7002_App.class, args);
	}
}
