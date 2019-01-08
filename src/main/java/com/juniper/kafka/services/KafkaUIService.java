package com.juniper.kafka.services;

import com.juniper.kafka.dto.KafkaUIDTO;


public interface KafkaUIService {
	public String saveProducerDetials(KafkaUIDTO kafkaDTO);
	public String saveConsumerDetails(KafkaUIDTO consumerkafkaUIDTO);
	public String saveTopicDetails(KafkaUIDTO kafkatopicUIDTO);
}
