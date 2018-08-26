package com.eden.hao.cache.config;



import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.eden.hao.cache.bean.Department;
import com.eden.hao.cache.bean.Employee;

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> ser = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(ser);
        return template;
    }
    
    // CacheManagerCustomizers 可以来定制缓存的一些规则. 
    @Primary
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> empRedisTemplate){
    	RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
    	
    	// 可以多了一个前缀, 使用前缀, 默认会将 CacheName 作为 key 的前缀.
    	cacheManager.setUsePrefix(true);
		return cacheManager;
    }
    
    // 定义 dept 的 redisTemplate 并加入到容器中
    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> ser = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    // 定义 dept 的 CacheManager
    @Bean
    public RedisCacheManager departmentCacheManager(RedisTemplate<Object, Department> deptRedisTemplate){
    	RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
    	
    	// 可以多了一个前缀, 使用前缀, 默认会将 CacheName 作为 key 的前缀.
    	cacheManager.setUsePrefix(true);
		return cacheManager;
    }
}
