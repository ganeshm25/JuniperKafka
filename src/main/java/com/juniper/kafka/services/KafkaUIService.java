package com.juniper.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.juniper.kafka.dto.ConsumerKafkaUIDTO;
import com.juniper.kafka.dto.KafkaTopicUIDTO;
import com.juniper.kafka.dao.KafkaUIDAO;
import com.juniper.kafka.dto.KafkaUIDto;

public class KafkaUIService {

	@Autowired
	KafkaUIDAO kafkaUIDAO;

	
	public String saveProducerDetials(KafkaUIDto kafkaDTO) {
		kafkaUIDAO.saveUIProducer(kafkaDTO);
		return "success";
	}
	
	public String saveConsumerDetails(ConsumerKafkaUIDTO consumerkafkaUIDTO) {
		kafkaUIDAO.saveUIConsumer(consumerkafkaUIDTO);
		return "success";
	}
	
	public String saveTopicDetails(KafkaTopicUIDTO kafkatopicUIDTO) {
		kafkaUIDAO.saveUITopic(kafkatopicUIDTO);
		return "success";
	}
}
