package com.juniper.kafka.ui.webservice;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.juniper.kafka.dto.RequestDTO;

@RestController
@RequestMapping(value="/juniper/kafkaUI")
public class TestKafkaConnectionController {

	private static final int ADMIN_CLIENT_TIMEOUT_MS = 5000;
	
	@RequestMapping(value = "/testKafkaCon", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String validateKafkaOnOff(@RequestBody RequestDTO requestDto){
		
		String kafka_is_on = "Yes";
		
		Properties kafka = new Properties();
		
		kafka.put("bootstrap.servers", requestDto.getBody().get("data").get("hostName")+":"+requestDto.getBody().get("data").get("port"));
		
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
