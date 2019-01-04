package com.juniper.kafka.ui.webservice;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;

public class TestValidate {

	public static void main(String[] args) {
		
		String kafka_is_on = "Yes";
		int ADMIN_CLIENT_TIMEOUT_MS = 5000;
		
		Properties kafka = new Properties();
		
		kafka.put("bootstrap.servers", "35.185.47.113:9092");
		kafka.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafka.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		//kafka.put("zookeeper.connect", "35.185.47.113:2181");
		//kafka.put("ssl.keystore.location", "C:\\Users\\mohammed_razak01\\Documents\\test_key");
			
		try (AdminClient client = AdminClient.create(kafka)) {
	            
			try {
					client.listTopics(new ListTopicsOptions().timeoutMs(ADMIN_CLIENT_TIMEOUT_MS)).listings().get();
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Kafka is not available, timed out after {} ms"+ ADMIN_CLIENT_TIMEOUT_MS);
		            kafka_is_on = "No";
		            //return kafka_is_on;
				}
	        } catch (ExecutionException ex) {
	        	ex.printStackTrace();
	            System.out.println("Kafka is not available, timed out after {} ms"+ ADMIN_CLIENT_TIMEOUT_MS);
	            kafka_is_on = "No";
	            //return kafka_is_on;
	        }
		//return kafka_is_on;
		System.out.println("result---->"+kafka_is_on);
	}

}
