package com.juniper.kafka.ui.webservice;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/juniper/kafka/ui")
public class TestKafkaConnectionController {

	private static final int ADMIN_CLIENT_TIMEOUT_MS = 5000;
	
	@GetMapping(value="/validateKafka")
	public String validateKafkaOnOff(@RequestParam("host")String host,@RequestParam("port")String port){
		
		String kafka_is_on = "Yes";
		
		Properties kafka = new Properties();
		
		kafka.put("bootstrap.servers", "scheduler-host.us-east1-b.c.juniperonprem.internal:9092");
		
		try (AdminClient client = AdminClient.create(kafka)) {
	            
			try {
					client.listTopics(new ListTopicsOptions().timeoutMs(ADMIN_CLIENT_TIMEOUT_MS)).listings().get();
				} catch (InterruptedException e) {
					System.out.println("Kafka is not available, timed out after {} ms"+ ADMIN_CLIENT_TIMEOUT_MS);
		            kafka_is_on = "No";
		            return kafka_is_on;
				}
	        } catch (ExecutionException ex) {
	            System.out.println("Kafka is not available, timed out after {} ms"+ ADMIN_CLIENT_TIMEOUT_MS);
	            kafka_is_on = "No";
	            return kafka_is_on;
	        }
		return kafka_is_on;
	}
}
