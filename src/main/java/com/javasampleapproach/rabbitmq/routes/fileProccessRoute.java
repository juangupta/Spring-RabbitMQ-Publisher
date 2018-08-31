package com.javasampleapproach.rabbitmq.routes;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class fileProccessRoute extends RouteBuilder {

	
	@Override
	public void configure() throws Exception {
		
		int corePoolSize = 50;
		int maxPoolSize = 400;
		int keepAliveTime = 30;
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(65000);
		 
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
		                         maxPoolSize,
		                         keepAliveTime,
		                         TimeUnit.SECONDS,
		                         workQueue);
		
		from("direct:start")
		.log("Inicio")
		.setHeader("CamelAwsS3Key", simple("prueba"))
		//.pollEnrich("file:/Users/juagomez?fileName=prueba")
		.pollEnrich("aws-s3://buckettestjgg?amazonS3Client=#amazonS3Client&deleteAfterRead=false&fileName=prueba")
		.convertBodyTo(String.class)		
		.split()
		.tokenize("\n")
	    .parallelProcessing()
	    .streaming()
	    //.executorService(pool)
	    //.to("direct:split")
	    .to("seda:split?waitForTaskToComplete=never")
		.end()
		.log("Fin");	     
		
		from("seda:split?concurrentConsumers=500")
		//from("direct:split")
		//.log("total: ${property.CamelSplitSize}")
		 .setExchangePattern(ExchangePattern.InOnly)
		 .setHeader("rabbitmq.CONTENT_ENCODING", constant("UTF-8"))
		 .setHeader("rabbitmq.CONTENT_TYPE", constant("text/plain"))
		 //.to("rabbitmq://amq.direct?connectionFactory=#connectionFactory&autoDelete=false&routingKey=jggtest&declare=false&channelPoolMaxSize=500");
		 .to("rabbitmq://amq.direct?connectionFactory=#connectionFactory&autoDelete=false&declare=false&channelPoolMaxSize=500");
		//toBean("publisher.produceMsg");
		
	}
}
