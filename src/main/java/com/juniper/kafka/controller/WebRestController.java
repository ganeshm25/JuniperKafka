package com.juniper.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juniper.kafka.services.KafkaProducer;
import com.juniper.kafka.storage.MessageStorage;

@RestController
@RequestMapping(value="/juniper/kafka")
public class WebRestController {
	
	@Autowired
	KafkaProducer producer;
	
	
	
	@Autowired
	MessageStorage storage;
	
	@GetMapping(value="/producer")
	public String producer(@RequestParam("data")String data){
		producer.send(data);
		
		return "Done";
	}
	
	@GetMapping(value="/consumer")
	public String getAllRecievedMessage(){
		String messages = storage.toString();
		storage.clear();
		
		return messages;
	}
}
