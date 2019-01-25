package com.juniper.kafka.dao;

import java.util.HashMap;

import com.juniper.kafka.dto.KafkaUIDTO;


public interface KafkaUIDAO {
	
	public String onBoardKafka(KafkaUIDTO kafkaUIDto);
	
	public String saveUIProducer(KafkaUIDTO kafkaUIDto);
	
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto);
	
	public String saveUITopic(KafkaUIDTO kafkaUIDto);
	
	public HashMap<String,Object> fetchCluster(int clusterId);

}
