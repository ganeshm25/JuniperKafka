
package com.juniper.kafka.ui.webservice;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.juniper.kafka.dto.KafkaUIDTO;
import com.juniper.kafka.dto.RequestDTO;
import com.juniper.kafka.services.KafkaUIService;



@RestController
@RequestMapping(value="/juniper/kafkaUI")
public class TestKafkaConnectionController {

	private static final int ADMIN_CLIENT_TIMEOUT_MS = 5000;
	
	@Autowired
	private KafkaUIService kafkaUIService;
	/*
	 * 
	 * Expected JSON Input is blow
	 * {"header":{},"body":{"data":{"projects":"","project":"","user":"","ssl":"","cluster_name":"test","zookeeper_host_name":"sgdus.sjds.sdsa","zookeeper_knox_port":"9080","user_name":"hi","password":"hi","system":"HUB"}}}
	 * 
	 */
	@RequestMapping(value = "/testKafkaCon", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String validateKafkaOnOff(@RequestBody RequestDTO requestDto){
		System.out.println("inside service.....");
		String hostPort = requestDto.getBody().get("data").get("zookeeper_host_name")+":"+requestDto.getBody().get("data").get("zookeeper_knox_port");
		String kafka_is_on = "Congrats!! Given Host "+hostPort+" available.";
		Properties kafka = new Properties();
		kafka.put("bootstrap.servers", hostPort);
	try (AdminClient client = AdminClient.create(kafka)) {
			try {
					client.listTopics(new ListTopicsOptions().timeoutMs(ADMIN_CLIENT_TIMEOUT_MS)).listings().get();
					
					KafkaUIDTO kafkaUI = new KafkaUIDTO();
					kafkaUI.setClusterName(requestDto.getBody().get("data").get("cluster_name"));
					kafkaUI.setHostName(requestDto.getBody().get("data").get("zookeeper_host_name"));
					kafkaUI.setPort(requestDto.getBody().get("data").get("zookeeper_knox_port"));
					kafkaUI.setUserName(requestDto.getBody().get("data").get("user"));
					kafkaUI.setPassword(requestDto.getBody().get("data").get("password"));
					
					kafkaUIService.onBoardKafka(kafkaUI);
					
				} catch (Exception e) {
					System.out.println("Kafka is not available, timed out after {} ms"+ ADMIN_CLIENT_TIMEOUT_MS);
		            kafka_is_on = "Sorry!! Given Host "+hostPort+" not available. Kindly contact admin or try other host.";
		            return kafka_is_on;
				}
	        } catch (Exception ex) {
	            System.out.println("Kafka is not available, timed out after {} ms"+ ADMIN_CLIENT_TIMEOUT_MS);
	            kafka_is_on = "Given Host "+hostPort+" not available. Kindly contact admin or try other host";
	            return kafka_is_on;
	        }
		return kafka_is_on;
	}
}
