package com.juniper.kafka.dao;



import com.juniper.kafka.dto.ConsumerKafkaUIDTO;
import com.juniper.kafka.dto.KafkaTopicUIDTO;
import com.juniper.kafka.dto.KafkaUIDto;


public interface KafkaUIDAO {
	
	public String saveUIProducer(KafkaUIDto kafkaUIDto);
	
	public String saveUIConsumer(ConsumerKafkaUIDTO consumerkafkaUIDTO);
	
	public String saveUITopic(KafkaTopicUIDTO consumerkafkaUIDTO);

}
