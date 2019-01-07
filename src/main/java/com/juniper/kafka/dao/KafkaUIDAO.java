package com.juniper.kafka.dao;

import com.juniper.kafka.dto.KafkaUIDTO;


public interface KafkaUIDAO {
	
	public String saveUIProducer(KafkaUIDTO kafkaUIDto);
	
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto);
	
	public String saveUITopic(KafkaUIDTO kafkaUIDto);

}
