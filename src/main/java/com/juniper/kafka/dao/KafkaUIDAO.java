package com.juniper.kafka.dao;

import java.util.Map;

import com.juniper.kafka.dto.KafkaUIDTO;


public interface KafkaUIDAO {
	
	public String onBoardKafka(KafkaUIDTO kafkaUIDto);
	
	public String saveUIProducer(KafkaUIDTO kafkaUIDto);
	
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto);
	
	public String saveUITopic(KafkaUIDTO kafkaUIDto);
	
	public Map<String, Object> fetchCluster(int clusterId);
	
	public String savePubSubTopic(KafkaUIDTO kafkaUIDto);
	
	public String saveDataFeedDetails(KafkaUIDTO kafkaUIDto);
	
	public String createPubSub(KafkaUIDTO kafkaUIDto);
	
	public String fetchClusterAddress(int clusterId);
}
