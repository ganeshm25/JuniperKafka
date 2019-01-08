package com.juniper.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniper.kafka.dao.KafkaUIDAO;
import com.juniper.kafka.dto.KafkaUIDTO;

@Service
public class KafkaUIServiceImpl implements KafkaUIService {

	@Autowired
	KafkaUIDAO kafkaUIDAO;

	
	public String saveProducerDetials(KafkaUIDTO kafkaDTO) {
		kafkaUIDAO.saveUIProducer(kafkaDTO);
		return "success";
	}
	
	public String saveConsumerDetails(KafkaUIDTO consumerkafkaUIDTO) {
		kafkaUIDAO.saveUIConsumer(consumerkafkaUIDTO);
		return "success";
	}
	
	public String saveTopicDetails(KafkaUIDTO kafkatopicUIDTO) {
		kafkaUIDAO.saveUITopic(kafkatopicUIDTO);
		return "success";
	}
}
