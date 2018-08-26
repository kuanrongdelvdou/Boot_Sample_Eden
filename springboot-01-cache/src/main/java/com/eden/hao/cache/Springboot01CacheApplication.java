package com.eden.hao.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 搭建基本环境:
 * 1.导入数据库文件, 创建出 department 和 employee 表
 * 2. 创建 javaBean 封装数据.
 * 3. 整合 Mybatis 操作数据库
 * 		1.配置数据源信息
 * 		2.使用注解版的 Mybatis
 * 			1> 使用 @MapperScan 指定需要扫描的 mapper 接口所在的包
 * 			
 *二、快速体验缓存
 *		步骤:
 *		1. 开启基于注解的缓存 @EnableCaching
 *		2. 标注缓存注解即可
 *			@Cacheable
 *			@CacheEvict
 *			@CachePut
 *
 *三、原理
 *	1. 自动配置类: CacheAutoConfiguration
 *
 *	2. 缓存的配置类: redis 的缓存配置类、 Ehcache 的缓存配置类 ....还有很多其他缓存的配置类
 *
 *	3. 哪个配置类默认会生效呢? : 默认是 SimpleCacheConfiguration 生效了.
 *
 *	4. 给容器中注册了一个 CacheManage(缓存管理器): ConcurrentMapCacheManager	
 *
 *	5. ConcurrentMapCacheManager 这个缓存管理器可以获取和创建 ConcurrentMapCache 类型的缓存组件, 他的作用将数据保存在 ConcurrentMap 中;
 *
 *四、缓存的运行流程: 以@Cacheable 作为例子
 *
 *	1. 方法运行之前, 先去查询 Cache(缓存组件), 按照 cacheNames 指定的名字获取(CacheManager 先获取相应的缓存);
 *	      第一次获取缓存组件的时候, 如果没有该缓存组件会自动创建该缓存组件. 
 *	
 *	2. 去 Catch 中查找缓存的内容, 使用一个 key, 默认就是方法的参数. key 是按照某种策
 *
 *     略生成了 ---> 默认使用 SimpleKeyGenerator 来生成 key
 *     
 *     > SimpleKeyGenerator 生成策略: 
 *     
 *     		如果没有参数 key = new SimpleKey(); 
 *     		如果有一个参数  key = 参数值;
 *     	        如果有多个参数 key = new SimpleKey(params)
 *     
 *  3. 如果没有查到缓存, 那么就调用目标方法
 *  
 *  4. 将目标方法返回的结果放入缓存!
 *  
 *  一句话: @Cacheable 标注的方法执行之前先来检查缓存中有没有这个数据, 默认按照参数的值作为 key 去查询缓存
 *  
 *  		如果缓存中没有该 key 就运行方法并将方法返回结果放入缓存. 以后再来调用, 就可以直接使用缓存中的数据
 *  
 *  核心:
 *  
 *  	1. 使用 CacheManager 按照名字得到 Cache 组件(CacheManager 没有配置的情况下, 默认使用 ConcurrentMapManager 来得到 ConcurrentMapCache 缓存组件. )
 *  
 *  	2. key 是使用 keyGenerator 生成的, 默认的是 SimpleKeyGenerator 来生成的.
 *  
 *  	3. 调用缓存
 *  
 * 五. 注解的几个属性
 * 
 *  	1. cacheNames/value: 指定缓存组件的名字, 就是说将方法的返回值放入哪个缓存组件中, 是数组的方式, 可以指定多个缓存!
 *  
 * 		2. key: 缓存数据使用的 key; 可以使用 SpEL 表达式
 * 
 * 		3. keyGenerator: key 的生成器, 可以自己指定 key 的成成器组件 id; key/keyGenerator 二选一使用
 * 
 * 		4. cacheManage: 指定缓存管理器, 或者 cacheResolver 指定获取解析器, 二选一
 * 
 * 		5. condition: 指定符合条件的情况下才缓存; condition = "#id > 0"
 * 
 * 		   @Cacheable(value = {"emp"}, keyGenerator = "myKeyGenerator", condition = "#a0 > 1" and #root.methodMame eq 'aaa') ---> 第一个参数的值大于 1 才进行缓存. --> 可以写多个条件
 * 
 * 		6. 否定缓存: 当 unless 指定的条件为 true, 方法的返回值就不会被缓存, 可以获取到结果进行判断; unless="#result == null"
 * 
 * 		   @Cacheable(value = {"emp"}, keyGenerator = "myKeyGenerator", unless = "#a0 == 1") --> 如果第一个参数等于 1, 结果不缓存!
 * 
 * 六. 整合redis 作为缓存
 * redis 是一个开源(BSD 许可) 的, 内存中数据结构存储系统, 他可以用作数据库、缓存、和消息中间件. 
 * 		1. 安装 redis: 使用 docker
 * 	
 * 		2. 引入 redis 的 starter
 * 
 * 		3. 配置 redis
 * 
 * 		4. 测试缓存
 * 
 * 			1> 原理: CacheManager === Cache 缓存组件来实际给缓存中存取数据
 * 
 * 			2> 引入 redis 的 starter, 容器中保存的是 RedisCacheManager
 * 
 * 			3> RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件; RedisCache 通过操作 redis 来缓存数据!
 * 	
 * 			4> 默认保存数据 k-v 都是都是 Object, 利用 jdk 序列化机制保存. 如何保存为 json?
 * 
 * 				a. 引入了 redis 的 starter, cacheManager 变为 RedisCacheManager
 * 
 * 				b. 默认创建的 RedisCacheManager 操作 redis 的时候使用的是 RedisTemplate<Object, Object> 
 * 
 * 				c. RedisTemplate<Object, Object> 默认使用的是 jdk 的序列化机制
 * 
 * 			5> 自定义 CacheManager, 
 * @author Administrator
 *
 */
@MapperScan("com.eden.hao.cache.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01CacheApplication.class, args);
	}
}
