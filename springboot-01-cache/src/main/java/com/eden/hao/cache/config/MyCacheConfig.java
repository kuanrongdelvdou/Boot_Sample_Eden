package com.eden.hao.cache.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCacheConfig {

	@Bean("myKeyGenerator")
	public KeyGenerator myKeyGenerator(){
		
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				
				return method.getName() + "[" + Arrays.asList(params).toString() + "]";
			}
		};
	}
}
