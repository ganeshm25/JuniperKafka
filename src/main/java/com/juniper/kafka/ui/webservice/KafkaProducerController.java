package com.juniper.kafka.ui.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.juniper.kafka.dto.KafkaUIDTO;
import com.juniper.kafka.dto.RequestDTO;
import com.juniper.kafka.services.KafkaUIService;

@RestController
@RequestMapping(value="/juniper/kafkaUI")
public class KafkaProducerController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	@RequestMapping(value = "/kafkaProducer", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String kafkaProducerCreation(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setHostName(requestDto.getBody().get("data").get("hostName"));
				kafkaUI.setPort(requestDto.getBody().get("data").get("port"));
				kafkaUI.setTopicName(requestDto.getBody().get("data").get("topicName"));
				kafkaUI.setProducerName(requestDto.getBody().get("data").get("producerName"));
				
				kafkaUIService.saveProducerDetials(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while producer creation "+ex);
	            
	        }
		return "success";
	}
}
