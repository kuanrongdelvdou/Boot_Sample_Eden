package com.eden.hao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.eden.hao.myrule.MySelfRule;

@SpringBootApplication
@EnableDiscoveryClient // 服务发现
@EnableEurekaClient

// 在启动改为服务的时候就能去加载我们自定义 Ribbon 配置类, 从而使配置生效
@RibbonClient(name="MICROSERVICECLOUD-DEPT", configuration=MySelfRule.class)
public class DeptConsumer80_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
