package com.eden.hao.springcloud.cfgbean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;

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
	@LoadBalanced // Spirng Cloud Ribbon 是基于 Netfix Ribbon 实现的一套客户端        负载均衡工具  
	public RestTemplate getRestTemplate(){
		
		return new RestTemplate();
	}
	
	/*public IRule myRule(){
		
		//return new RandomRule(); // 用我们重新选择的随机算法替代默认的轮询!
		//return new RoundRobinRule()// 轮询
		return new RetryRule();		
	}*/
}

// applicationContext.xml == ConfigBean(@Configuration)

//@Bean
//public UserService getUserService(){
//	
//}

// <bean id = "userService" class = "com.eden.hao.UserServiceImpl">