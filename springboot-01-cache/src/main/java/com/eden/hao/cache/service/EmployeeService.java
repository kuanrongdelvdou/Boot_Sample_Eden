package com.eden.hao.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.eden.hao.cache.bean.Employee;
import com.eden.hao.cache.mapper.EmployeeMapper;

// cacheManager 的属性名默认就是自定义的 CacheManager 的方法名
@CacheConfig(cacheNames = "emp", cacheManager = "employeeCacheManager") // 抽取缓存的公共配置
@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 注解 @Cacheable: 将方法的运行结果进行缓存, 以后要相同的数据, 直接从缓存中获取, 先不条用方法.
	 * 
	 * CacheManager 管理多个 Cache 组件, 对缓存的真正 CRUD 操作在 Cache 组件中, 每一个缓存组件有一个唯一的名字
	 * 
	 * @Cacheable 的几个属性:
	 * 		cacheNames/values: 指定缓存的名字;
	 * 		key: 缓存数据使用的 key, 可以用他来指定. 默认是使用方法参数的值 		1-方法的返回值.
	 * 			编写 SpEL 表达式: #id --> 参数 id 的值; #a0, #p0, #root.args[0] ....
	 * 		keyGenerator: key 的生成器, 也可以自己指定 key 的生成器的组件 id, key/keyGenerator 二选一使用!
	 * 		cacheManager: 指定缓存管理器; 或者 cacheResolver , 二者选其中一个;
	 * 		condition: 指定符合条件的情况下才缓存;
	 * 		unless: 否定缓存, 当 unless 指定的条件为 ture, 方法的返回值就不会缓存, 可以获取到结果进行判断;
	 * 		sync: 是否使用异步模式
	 * 
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames = "emp")
	public Employee getEmp(Integer id){
		
		System.out.println("查询" + id + "号员工");
		Employee emp = employeeMapper.getEmpById(id);
		return emp;
	}
	
	
	/**
	 * @CachePut : 即调用方法, 又更新缓存数据;如: 修改了数据库的某个数据的同时又跟新缓存--> 同步更新缓存.
	 * 
	 * 运行时机:
	 * 
	 * 		1. 先调用目标方法
	 * 
	 * 		2. 将目标方法的返回结果缓存起来
	 * 
	 * 测试步骤:
	 * 
	 * 		1. 查询 1号员工, 查询到的结果会存放到缓存中.
	 * 
	 * 		    放入缓存的 key : 1 (是 id), lastName: 张三
	 * 
	 * 		2. 以后查询还是之前的结果(从缓存中拿数据)
	 * 
	 * 		3. 更新 1 号员工: [lastName: zhangsan; gender: 0]
	 * 
	 * 			将方法的返回值也放进缓存, 但是我们的 @CachePut 没有指定缓存的 key, 所以默认的 key 就是参数的名字
	 * 
	 * 			key: 传入的 employee 对象    值: 返回的 employee 对象;			
	 * 
	 * 		4. 此时查询 1 号员工的结果是什么?
	 * 
	 * 		      应该是更新后员工信息 ---> 其实是没更新前的数据, 为什么呢?
	 * 
	 * 			---> 因为 1 号员工没有在缓存中更新(更新了, 只不过更新的 key 不同而已)
	 * 
	 * 			key = "#emp.id" : 使用传入的参数的员工 id;
	 * 
	 * 			key = "result.id" : 使用返回后的 emp 的 id;
	 * 
	 * 			注意: @Cacheable 的 key 是不能用 @resoult 的! --> 自己想想为什么!
	 * 
	 * 
	 */
	@CachePut(value = "emp", key = "#emp.id")
	public Employee updateEmp(Employee emp){
		System.out.println("update emp : " + emp);
		employeeMapper.updateEmp(emp);	
		return emp;
	}
	
	/**
	 *  @CacheEvit: 清除缓存; 如: 在删除一个员工的时候, 同时清除掉缓存中相关员工的信息
	 *  
	 *  1. key : 可以通过 key 来指定要清除的数据
	 *  
	 *  2. allEntries : 删除缓存中的所有数据 allEntries = true
	 *  
	 *  3. beforeInvocation = false : 缓存的清除是否是在方法执行之前执行. false --> 默认代表是在方法执行之后执行.
	 *  
	 *  	用在什么场合呢? 比如说在 deleteEmp 方法中写一句 int 1 = 10/0 --> 会报错, 如果没有设置 beforeInvocation , 则方法报错了没有执行完是不会清空缓存的.
	 *  
	 *  	如果设置了 beforeInvocation = true, 即使方法报错了, 我们是在方法执行之前就清空缓存了!
	 */
	@CacheEvict(value = "emp", key = "#id")
	public void deleteEmp(Integer id){
		
		System.out.println("deleteEmp : " + id);
		employeeMapper.deleteEmp(id);
	}
	
	/*
	 * 这个缓存的规则说明:
	 * 1. 查询的时候将传入参数 lastName 作为 key 缓存起来.
	 * 
	 * 2. 我们拿到数据后将得到的数据 id 作为 key 缓存起来.
	 * 
	 * 3. 拿到数据后将得到的数据的 email 作为 key 缓存起来.
	 * 
	 *最后的结果就是: ---> 我们按照员工的名字查询一个数据以后, 按照员工的 lastName、id、email
	 *
	 *	在缓存中都可以查到缓存数据.	
	 * 
	 * */
	
	@Caching(
			cacheable = {
					@Cacheable(value = "emp", key = "#lastName")
			},
			put = {
					@CachePut(value = "emp", key = "#result.id"),
					//@CachePut(value = "emp", key = "#result.email")
			}		
			)
	public Employee getEmployeeByLastName(String lastName){
		return employeeMapper.getEmpByLastName(lastName);
	}
}
