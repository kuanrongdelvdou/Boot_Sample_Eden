package com.eden.hao.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * 一、 自动配置 CacheAutoConfiguration --> 看看里面有什么
 * 
 * 	1. 自动配置了链接工厂  ConnectionFactory
 * 
 * 	2. RabbitProperties 封装了 RabbitMQ 的配置
 * 
 * 	3. RabbitTemplate : 给 RabbitMQ 发送和接收消息的 ---> 和 JdbcTemplate 一个道理
 * 
 * 	4. AmqAdmin: RabbitMQ 系统管理功能组件(创建交换器、消息队列等等....)
 * 
 * 	5. @EnableRabbit + @RabbitListener : 监听消息队列的内容
 * @author Administrator
 *
 */
@EnableRabbit // 开启基于注解的 RabbitMQ 模式
@SpringBootApplication
public class Springboot02AmqpRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02AmqpRabbitmqApplication.class, args);
	}
}
