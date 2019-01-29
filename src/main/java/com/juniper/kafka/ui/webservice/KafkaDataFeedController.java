package com.juniper.kafka.ui.webservice;

import java.util.Properties;


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
public class KafkaDataFeedController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	@RequestMapping(value = "/saveDataFeed", method = {RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String validateKafkaOnOff(@RequestBody RequestDTO requestDto){
		System.out.println("inside service.....");
		String hostPort = requestDto.getBody().get("data").get("zookeeper_host_name")+":"+requestDto.getBody().get("data").get("zookeeper_knox_port");
		String kafka_is_on = "Congrats!! Given Host "+hostPort+" available.";
		Properties kafka = new Properties();
		kafka.put("bootstrap.servers", hostPort);
	
			try {
					KafkaUIDTO kafkaUI = new KafkaUIDTO();
					kafkaUI.setFeedName(requestDto.getBody().get("data").get("feed_name"));
					kafkaUI.setClusterName(requestDto.getBody().get("data").get("cluster_name"));
					kafkaUI.setTopicName(requestDto.getBody().get("data").get("topic_name"));
					kafkaUI.setProjectId(requestDto.getBody().get("data").get("project_name"));
					kafkaUI.setServiceAccount(requestDto.getBody().get("data").get("service_account"));
					kafkaUI.setPubSubTopicId(requestDto.getBody().get("data").get("pub_sub_topic"));
					kafkaUI.setDataSet(requestDto.getBody().get("data").get("data_set"));
					kafkaUIService.saveDataFeedDetails(kafkaUI);
					
				} catch (Exception e) {
					System.out.println("Kafka is not available, timed out after {} ms");
		            kafka_is_on = "Sorry!! Given Host "+hostPort+" not available. Kindly contact admin or try other host.";
		            return kafka_is_on;
				}
	    
		return kafka_is_on;
	}
}
