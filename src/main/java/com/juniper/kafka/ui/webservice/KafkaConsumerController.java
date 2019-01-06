package com.juniper.kafka.ui.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.juniper.kafka.dto.ConsumerDTO;
import com.juniper.kafka.dto.ConsumerKafkaUIDTO;
import com.juniper.kafka.services.KafkaUIService;

@RestController
@RequestMapping(value="/juniper/kafkaUI")
public class KafkaConsumerController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	@RequestMapping(value = "/kafkaConsumer", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String kafkaConsumerCreation(@RequestBody ConsumerDTO requestDto){
		
		ConsumerKafkaUIDTO kafkaUI = new ConsumerKafkaUIDTO();
		
		try {
				kafkaUI.setHostName(requestDto.getBody().get("data").get("hostName"));
				kafkaUI.setPort(requestDto.getBody().get("data").get("port"));
				kafkaUI.setTopicName(requestDto.getBody().get("data").get("topicName"));
				kafkaUI.setConsumerName(requestDto.getBody().get("data").get("consumerName"));
				//kafkaUI.setConsumerName(requestDto.getBody().get("data").get("noConsumer"));
				
				kafkaUIService.saveConsumerDetails(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while Consumer creation "+ex);
	            
	        }
		return "success";
	}
}

