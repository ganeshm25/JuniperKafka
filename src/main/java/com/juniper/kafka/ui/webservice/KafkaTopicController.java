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
				kafkaUIService.saveTopicDetails(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while saving topic details "+ex);
	            
	        }
		return "success";
	}
}
