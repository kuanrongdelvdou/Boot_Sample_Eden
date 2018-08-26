package com.eden.hao.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import com.eden.hao.cache.bean.Department;
import com.eden.hao.cache.mapper.DepartmentMapper;

@CacheConfig(cacheNames = "dept", cacheManager = "departmentCacheManager") // 抽取缓存的公共配置
@Service
public class DepartmentService {
 
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Qualifier("departmentCacheManager")
	@Autowired
	RedisCacheManager departmentCacheManager;
	
	/**
	 * 缓存的数据可以存入 redis; 但是第二次从缓存中查询 dept 的信息就不能反序列化回来了 
	 * 
	 * 原因就是因为我们存的是 dept 的 json 数据, 而我们自定义的 CacheManager 默认使用的是
	 * 
	 * RedisTemplate<Object, Employee> 来操作数据的(只能操作 Employee)
	 * @param id
	 * @return
	 */
//	@Cacheable(value = "dept")
//	public Department getDeptById(Integer id){
//		return departmentMapper.getDeptById(id);
//	}
	
	
	// 使用代码的形式操作缓存
	public Department getDeptById(Integer id){
		
		 Department dept = departmentMapper.getDeptById(id);
		 
		 // 获取某个缓存
		 Cache deptCache = departmentCacheManager.getCache("dept");
		 
		 // 向缓存中存放数据
		 deptCache.put("dept:1", dept);
		 
		 return dept;
	}
}
