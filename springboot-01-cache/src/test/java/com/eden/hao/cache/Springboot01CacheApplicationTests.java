package com.eden.hao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.eden.hao.cache.bean.Employee;
import com.eden.hao.cache.mapper.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Test
	public void contextLoads() {
		
		Employee emp = employeeMapper.getEmpById(1);
		
		System.out.println(emp);
	}
	
	// 和 jdbcTemplate一样, 简化操作 redis 的.
	@Autowired
	RedisTemplate redisTemplate;  // k-v 都是 Object(对象)
	
	// 由于我们操作 String 类型比较多, 所以除了 RedisTemplate 之外还抽象除了一个 专门操作 String 类型的 Template
	@Autowired
	StringRedisTemplate stringRedisTemplate;  // k-v 都是字符串
	
	/**
	 * Redis 常见的五大数据类型:
	 * 	1. String(字符串): stringRedisTemplate.opsForValue()  --> 操作字符串的
	 * 
	 * 	2. List(列表): stringRedisTemplate.opsForList()  --> 操作列表的
	 * 
	 * 	3. Set(集合): stringRedisTemplate.opsForSet() --> 操作集合的
	 * 
	 * 	4. Hash(散列): stringRedisTemplate.opsForHash()  --> 操作散列的
	 * 
	 * 	5. Zset(有序集合): stringRedisTemplate.opsForZSet() ---> 操作有序集合的 
	 */
	@Test
	public void test01(){
		
		// 给 redis 中保存了一个数据
		//stringRedisTemplate.opsForValue().append("msg", "你好");
		
		// 从 redis 中获取数据
		String string = stringRedisTemplate.opsForValue().get("msg");
		System.out.println("从redis 中获取数据 msg:" + string);
		
		stringRedisTemplate.opsForList().leftPush("mylist", "1");
		stringRedisTemplate.opsForList().leftPush("mylist", "2");
		stringRedisTemplate.opsForList().leftPush("mylist", "3");
		stringRedisTemplate.opsForList().leftPush("mylist", "4");
	}
	
	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;
	
	// 测试保存对象
	@Test
	public void test02(){
		Employee employee = employeeMapper.getEmpById(1);
		
		// 默认如果保存对象, 使用 jdk 序列化机制, 序列化后的数据保存到 redis 中.
		//redisTemplate.opsForValue().set("emp-01", employee);
		
		// 1. 将数据以 json 的方式保存, 有一下两种方式:
		// 方式一: 自己将对象转为 json(这种方式就不讲了, 市面上有很多三方的转换工具 jar 包)
		// 方式二: redisTemplate 有默认的序列化规则
		
		empRedisTemplate.opsForValue().set("emp-01", employee);
				
	}
}
