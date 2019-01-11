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
	
	
	@RequestMapping(value = "/kafkaProducer", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String kafkaProducerCreation(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setProducerName(requestDto.getBody().get("data").get("producer_name"));
				kafkaUI.setTopicID(Integer.parseInt(requestDto.getBody().get("data").get("topic")));
				kafkaUI.setClusterID(Integer.parseInt(requestDto.getBody().get("data").get("cluster")));
				kafkaUI.setTargetType(requestDto.getBody().get("data").get("targetType"));
				kafkaUI.setFileName(requestDto.getBody().get("data").get("linux_file_pattern"));
				kafkaUI.setFilePath(requestDto.getBody().get("data").get("linux_file_path"));
				kafkaUI.setSourceID(Integer.parseInt(requestDto.getBody().get("data").get("targetSystem")));
				kafkaUIService.saveProducerDetials(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while producer creation "+ex);
	            
	        }
		return "success";
	}
}
