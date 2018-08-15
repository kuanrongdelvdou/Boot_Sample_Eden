package com.eden.hao.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration // 这是一个配置类(等同于 spirng 中的 applicationContext.xml 一样)
public class MySelfRule {

	@Bean
	public IRule myRule(){
		
		return new RandomRule(); // Ribbon 默认是轮询, 我自定义为随机.
		//return new RandomRule_Eden(); // 我们自定义的规则
	}
}
