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
public class KafkaClusterOnboardController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	
	/*
	 * 
	 * Expected JSON Input is blow
	 * {"header":{},"body":{"data":{"projects":"","project":"","user":"","ssl":"","cluster_name":"test","zookeeper_host_name":"sgdus.sjds.sdsa","zookeeper_knox_port":"9080","user_name":"hi","password":"hi","system":"HUB"}}}
	 * 
	 */
	
	@RequestMapping(value = "/onboardKafka", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String kafkaConsumerCreation(@RequestBody RequestDTO requestDto){
		
		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		
		try {
				kafkaUI.setClusterName(requestDto.getBody().get("data").get("cluster_name"));
				kafkaUI.setHostName(requestDto.getBody().get("data").get("zookeeper_host_name"));
				kafkaUI.setPort(requestDto.getBody().get("data").get("zookeeper_knox_port"));
				kafkaUI.setUserName(requestDto.getBody().get("data").get("user"));
				kafkaUI.setPassword(requestDto.getBody().get("data").get("password"));
				
				kafkaUIService.onBoardKafka(kafkaUI);
				
	        } catch (Exception ex) {
	            System.out.println("Exception while Consumer creation "+ex);
	            
	        }
		return "success";
	}
}

