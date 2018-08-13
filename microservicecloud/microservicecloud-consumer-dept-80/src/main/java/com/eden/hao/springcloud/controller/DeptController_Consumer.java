package com.eden.hao.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eden.hao.entity.Dept;

@RestController
public class DeptController_Consumer {
	
	//private static final String REST_URL_PREFIX = "http://localhost:8001";
	
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";  // ----> 这才是真正的微服务访问

	/*[1] 使用 restTemplate 访问 restfu 接口非常的简单粗暴无脑. 有三个参数 url, requestMap, ResponseBean.class

      [2] url: 代表 REST 的请求地址

      [3] requestMap: 代表请求参数

      [4] ResponseBean.class : 代表 HTTP 响应转换成的对象类型.*/
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/consumer/dept/add")
	public boolean add(Dept dept){
		
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
	}
	
	@RequestMapping(value="/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id){
		
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
	}
	
	@RequestMapping(value="/consumer/dept/list")
	public List<Dept> list(){
		
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
	}
	
	
	// 测试@EnableDiscoveryClient,消费端可以调用服务发现
	@RequestMapping(value = "/consumer/dept/discovery")
	public Object discovery()
	{
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}
}
