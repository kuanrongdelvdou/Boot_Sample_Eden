package com.eden.hao.elsticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * SpringBoo 默认支持两种技术和 ES 交互
 * 
 * 		1. Jest (默认是不生效的)
 * 			1> 需要导入 jest 的工具包(io.searchbox.client.JestClient)
 * 
 * 		2. SpringData ElasticSearch[ES 版本有可能不适合]
 * 
 * 			1> Client 节点信息 , clusterNodes, clusterName
 * 
 * 			2> ElasticsearchTemplate 操作 ES
 * 
 * 			3> 编写一个 ElasticsearchRepository 的子接口来操作 ES;
 * 
 * SpringData ElasticSearch 操作 ES 两种方式:
 * 	
 * 	1> 编写一个 ElasticsearchRepostory
 * 
 * 	2> 
 * 
 * 			
 * 		
 * @author Administrator
 *
 */
@SpringBootApplication
public class Springboot03Elsticsearch2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot03Elsticsearch2Application.class, args);
	}
}
