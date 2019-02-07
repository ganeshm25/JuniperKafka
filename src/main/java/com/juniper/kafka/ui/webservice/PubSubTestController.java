package com.juniper.kafka.ui.webservice;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/juniper/kafkaUI")
public class PubSubTestController {

	
	@RequestMapping(value = "/about", method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String doPubSubCreate(){
		return "Hello!! I am Kafka Web Service!!!";
	}
}

