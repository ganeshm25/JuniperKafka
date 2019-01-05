package com.juniper.kafka.dao;

import com.juniper.kafka.dto.KafkaUIDto;

public interface KafkaUIDAO {
	
	public String saveUIProducer(KafkaUIDto kafkaUIDto);

}
