package com.juniper.kafka.ui.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.juniper.kafka.dto.KafkaUIDTO;
import com.juniper.kafka.dto.RequestDTO;
import com.juniper.kafka.services.KafkaUIService;

@RestController
@CrossOrigin
public class KafkaConsumerController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	
	@RequestMapping(value = "/kafkaConsumer", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String kafkaConsumerCreation(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setHostName(requestDto.getBody().get("data").get("cluster_name"));
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

