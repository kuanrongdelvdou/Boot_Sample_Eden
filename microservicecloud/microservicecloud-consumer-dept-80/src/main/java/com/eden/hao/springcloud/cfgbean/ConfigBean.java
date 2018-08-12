package com.eden.hao.springcloud.cfgbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置 spring 的配置文件是 applicationContext.xml --> 现在  boot 对spring 做了优化 --> 没有这个 .xml
 * 
 * 文件了, 而用的是 @Configuration 注解版的配置类来实现同样的 applicationContext.xml 配置文件的作用.
 * @author Administrator
 *
 */
@Configuration  // 只要加了这个注解  ----> 这个类就等同于以前的 applicationContext.xml 
public class ConfigBean {

	@Bean
	public RestTemplate getRestTemplate(){
		
		return new RestTemplate();
	}
}

// applicationContext.xml == ConfigBean(@Configuration)

//@Bean
//public UserService getUserService(){
//	
//}

// <bean id = "userService" class = "com.eden.hao.UserServiceImpl">