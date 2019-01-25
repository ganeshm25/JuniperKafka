package com.juniper.kafka.ui.webservice;

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
public class KafkaTopicController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	@RequestMapping(value = "/kafkaTopic", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String kafkaTopicCreation(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setTopicName(requestDto.getBody().get("data").get("kafka_topic"));
				kafkaUI.setPurpose(requestDto.getBody().get("data").get("purpose"));
				kafkaUI.setClusterID(Integer.parseInt(requestDto.getBody().get("data").get("cluster_id")));
				kafkaUI.setPartitionCount(1);//(requestDto.getBody().get("data").get("partition"));
				kafkaUI.setReplicationFactor(1);//(requestDto.getBody().get("data").get("replication"));
				kafkaUIService.saveTopicDetails(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while saving topic details "+ex);
	            
	        }
		return "success";
	}
	
	@RequestMapping(value = "/activateTopic", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String crateKafkaTopic(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setTopicID(Integer.parseInt(requestDto.getBody().get("data").get("topicID")));
				//kafkaUI.setActivationFlag(requestDto.getBody().get("data").get("activationFlag"));
				//kafkaUIService.createTopic(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while saving topic details "+ex);
	            
	        }
		return "success";
	}
	
	
	@RequestMapping(value = "/pubsubTopic", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String pubsubTopicCreation(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setServiceAccount(requestDto.getBody().get("data").get("serviceaccount"));
				kafkaUI.setProjectId(requestDto.getBody().get("data").get("project_id"));
				kafkaUI.setTopicName(requestDto.getBody().get("data").get("topic_name"));
			
				kafkaUIService.savePubSubTopicDetails(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while saving topic details "+ex);
	            
	        }
		return "success";
	}
}
