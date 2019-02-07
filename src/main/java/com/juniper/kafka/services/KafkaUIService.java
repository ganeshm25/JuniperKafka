package com.juniper.kafka.services;

import java.util.Properties;

import com.juniper.kafka.dto.KafkaUIDTO;


public interface KafkaUIService {
	public String onBoardKafka(KafkaUIDTO kafkatopicUIDTO);
	public String saveProducerDetials(KafkaUIDTO kafkaDTO);
	public String saveConsumerDetails(KafkaUIDTO consumerkafkaUIDTO);
	public String saveTopicDetails(KafkaUIDTO kafkatopicUIDTO);
	public String savePubSubTopicDetails(KafkaUIDTO kafkatopicUIDTO);
	public String saveDataFeedDetails(KafkaUIDTO kafkatopicUIDTO);
	//public String createTopic(KafkaUIDTO kafkatopicUIDTO);
	public String createPubSub(KafkaUIDTO kafkatopicUIDTO);
	public String fetchClusterAddress(int clusterId);
	public void kafkaConsumer(Properties props,String topic);
}
