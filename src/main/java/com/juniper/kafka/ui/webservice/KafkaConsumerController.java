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
public class KafkaConsumerController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	
	@RequestMapping(value = "/kafkaConsumer", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String kafkaConsumerCreation(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setConsumerName(requestDto.getBody().get("data").get("consumer_name"));
				kafkaUI.setTopicID(Integer.parseInt(requestDto.getBody().get("data").get("topic")));
				kafkaUI.setClusterID(Integer.parseInt(requestDto.getBody().get("data").get("cluster")));
				kafkaUI.setTargetType(requestDto.getBody().get("data").get("sourceType"));
				kafkaUI.setFileName(requestDto.getBody().get("data").get("linux_file_pattern"));
				kafkaUI.setFilePath(requestDto.getBody().get("data").get("linux_file_path"));
				kafkaUI.setSourceID(Integer.parseInt(requestDto.getBody().get("data").get("targetSystem")));
				
				kafkaUIService.saveConsumerDetails(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while Consumer creation "+ex);
	            
	        }
		return "success";
	}
}

