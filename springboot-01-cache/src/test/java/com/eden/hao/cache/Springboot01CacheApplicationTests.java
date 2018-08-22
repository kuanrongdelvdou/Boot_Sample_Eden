package com.eden.hao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

}
