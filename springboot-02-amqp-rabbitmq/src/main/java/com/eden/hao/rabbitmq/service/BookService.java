package com.eden.hao.rabbitmq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.eden.hao.rabbitmq.bean.Book;

// 这个bookservice 就来监听来自于消息队列中的book 相关的内容
@Service
public class BookService {

	@RabbitListener(queues = "atguigu.news")
	public void receive(Book book){
		
		System.out.println("收到消息: " + book);
	}
	
	@RabbitListener(queues = "atguigu.news")
	public void receive02(Message message){
		
		System.out.println("收到消息: " + message);
	}
}
