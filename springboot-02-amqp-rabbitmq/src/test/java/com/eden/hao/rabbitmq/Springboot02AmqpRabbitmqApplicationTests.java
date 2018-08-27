package com.eden.hao.rabbitmq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eden.hao.rabbitmq.bean.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpRabbitmqApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	AmqpAdmin amqpAdmin;
	
	@Test
	public void createExchange(){
		// 创建一个交换器
		//amqpAdmin.declareExchange(new DirectExchange("amqpamdin.exchange"));
		
		// 创建一个队列
		//amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));
		
		// 创建绑定器
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", 
				Binding.DestinationType.QUEUE, "amqpamdin.exchange", "amqp.eden", null));
		
		System.out.println("创建交换器完成, 交换器的名称是 amqpamdin.exchange!!");
	}
	
	
	/**
	 * 测试如下内容;
	 * 单播(点对点)
	 */
	@Test
	public void contextLoads() {
		
		//sent 方式:  massage 需要自己构造一个,定义消息体内容和消息头!
		//rabbitTemplate.send(exchange, routingKey, message);
		
		//convertAndSend 方法: object 默认当成消息体, 只需要传入需要发送的对象, 自动序列化发送给 rabbitmq
		//rabbitTemplate.convertAndSend(exchange, routingKey, object);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "这是第一个消息");
		map.put("data", Arrays.asList("helloworld", 124, true));
		
		// 使用 convertAndSend 方法发送点对点消息
		// 对象被默认序列化以后发送出去(默认的序列化机制是 jdk的)
		
		Book book = new Book("风云再起", "edenhao", 87);
		rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", book);
	}

	//接收数据
	@Test
	public void receive(){
		
		// 也有两个接收方法, receice 和 receiveAndConvert --> 区别自己查 api
		//rabbitTemplate.receive(queueName);
		
		Object object = rabbitTemplate.receiveAndConvert("atguigu.news");
		
		System.out.println("接收到的数据类型是: " + object.getClass());
		
		System.out.println("接收到的数据是: " + object);
	}
	
	/**
	 * 广播
	 */
	@Test
	public void BroadCasttest(){
		
		// 广播不用写路由见, 绑定在它下面的消息队列都可以接收到他的消息
		rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三国", "罗贯中", 100));
	}
}
