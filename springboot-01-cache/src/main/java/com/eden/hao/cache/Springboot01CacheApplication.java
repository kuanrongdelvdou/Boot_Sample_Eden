package com.eden.hao.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 搭建基本环境:
 * 1.导入数据库文件, 创建出 department 和 employee 表
 * 2. 创建 javaBean 封装数据.
 * 3. 整合 Mybatis 操作数据库
 * 		1.配置数据源信息
 * 		2.使用注解版的 Mybatis
 * 			1> 使用 @MapperScan 指定需要扫描的 mapper 接口所在的包
 * 			
 * 		
 * @author Administrator
 *
 */
@MapperScan("com.eden.hao.cache.mapper")
@SpringBootApplication
public class Springboot01CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01CacheApplication.class, args);
	}
}
