package com.javasampleapproach.rabbitmq;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultSaslConfig;
import com.rabbitmq.client.SaslConfig;
import com.rabbitmq.client.SslContextFactory;

@SpringBootApplication
public class SpringRabbitMqPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitMqPublisherApplication.class, args);
	}	

	
	@Bean
    public ConnectionFactory connectionFactory() {
		

		ConnectionFactory connectionFactory = new ConnectionFactory();
		//connectionFactory.setHost("portal-ssl1571-27.bmix-dal-yp-c0f0e752-d6b2-43bc-8be6-1d26c74f82ad.2126222060.composedb.com");
        //connectionFactory.setPort(5672);
        //connectionFactory.setUsername("juagomez");
        //connectionFactory.setPassword("Edpyme");
        try {
			//connectionFactory.setUri("amqp://juagomez:vinula@localhost:5672");
        	connectionFactory.setUri("amqps://admin:HFZRGNULUDSCNDYT@portal-ssl1333-9.bmix-dal-yp-c0f0e752-d6b2-43bc-8be6-1d26c74f82ad.2126222060.composedb.com:51048/bmix-dal-yp-c0f0e752-d6b2-43bc-8be6-1d26c74f82ad");
        	//connectionFactory.setUri("amqp://Edpyme@portal-ssl1333-9.bmix-dal-yp-c0f0e752-d6b2-43bc-8be6-1d26c74f82ad.2126222060.composedb.com:51048/bmix-dal-yp-c0f0e752-d6b2-43bc-8be6-1d26c74f82ad");
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectionFactory.setConnectionTimeout(30000);
        return connectionFactory;
    }
	
	@Bean
	public AmazonS3 amazonS3Client() {
		BasicAWSCredentials credentials = new BasicAWSCredentials("697188e84a294b05ba71486a465f1e5b", "008338bdfb6d8456e8b1aad547ccc82af2d05b71f0f5feaf");
		AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard().withEndpointConfiguration(new EndpointConfiguration("https://s3-api.us-geo.objectstorage.softlayer.net", "us-geo")).withCredentials(credentialsProvider);
        return clientBuilder.build();
		//return s3Client;
    }
	
}
