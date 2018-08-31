package com.javasampleapproach.rabbitmq.web;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.rabbitmq.publisher.Publisher;

@RestController
public class WebController {
	
	//@Autowired
	//Publisher publisher;
	
	@EndpointInject(uri="direct:start")
    private FluentProducerTemplate producerTemplate;

	
	@RequestMapping("/send")
	public String sendMsg(@RequestParam("msg")String msg){		
		producerTemplate.request(); 
		//return publisher.produceMsg(msg);
		return "done";
	}
}
