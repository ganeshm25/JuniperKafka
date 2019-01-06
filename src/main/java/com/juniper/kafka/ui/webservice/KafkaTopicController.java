package com.juniper.kafka.ui.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.juniper.kafka.dto.KafkaTopicDTO;
import com.juniper.kafka.dto.KafkaTopicUIDTO;
import com.juniper.kafka.services.KafkaUIService;

@RestController
@RequestMapping(value="/juniper/kafkaUI")
public class KafkaTopicController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	@RequestMapping(value = "/kafkaTopic", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String kafkaTopicCreation(@RequestBody KafkaTopicDTO requestDto){
		
		KafkaTopicUIDTO kafkaUI = new KafkaTopicUIDTO();
		
		try {
				kafkaUI.setHostName(requestDto.getBody().get("data").get("hostName"));
				kafkaUI.setPort(requestDto.getBody().get("data").get("port"));
				kafkaUI.setTopicName(requestDto.getBody().get("data").get("topicName"));
								
				kafkaUIService.saveTopicDetails(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while saving topic details "+ex);
	            
	        }
		return "success";
	}
}
