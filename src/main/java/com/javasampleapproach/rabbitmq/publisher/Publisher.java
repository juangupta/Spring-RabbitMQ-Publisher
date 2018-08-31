package com.javasampleapproach.rabbitmq.publisher;

import java.sql.Timestamp;

//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class Publisher {
	
/*	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	public String produceMsg(String msg){
		
		msg = Thread.currentThread().getName();
		System.out.println(msg);
		String response = (String) amqpTemplate.convertSendAndReceive(exchange, "",msg);
		try{
			System.out.println(response.equalsIgnoreCase(Thread.currentThread().getName()));			
		}
		catch(Exception e) {
			System.out.println("No response");
		}
		
		return response;
	}*/
}