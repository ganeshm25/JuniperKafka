package com.juniper.kafka.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.juniper.kafka.dto.KafkaUIDTO;

@Service
public class KafkaUIDAOImpl  implements KafkaUIDAO{

	
	@Autowired
    private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public String saveUIProducer(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO JUNIPER_EXT_KAFKA_PRODUCER_MASTER " +
				"(PRODUCER_NAME,CLUSTER_ID,TOPIC_ID,SOURCE_TYPE,FILE_PATH,FILE_NAME,SOURCE_ID,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getProducerName(), kafkaUIDto.getClusterID(),kafkaUIDto.getTopicID(),kafkaUIDto.getTargetType(),kafkaUIDto.getFilePath(),kafkaUIDto.getFileName(),kafkaUIDto.getSourceID(),kafkaUIDto.getCreatedBy(),new java.sql.Timestamp(System.currentTimeMillis()),kafkaUIDto.getUpdatedBy(),new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}
	
	
	@Override
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO JUNIPER_EXT_KAFKA_CONSUMER_MASTER " +
				"(CONSUMER_NAME,CLUSTER_ID,TOPIC_ID,SOURCE_TYPE,FILE_PATH,FILE_NAME,SOURCE_ID,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getConsumerName(), kafkaUIDto.getClusterID(),kafkaUIDto.getTopicID(),kafkaUIDto.getTargetType(),kafkaUIDto.getFilePath(),kafkaUIDto.getFileName(),kafkaUIDto.getSourceID(),kafkaUIDto.getCreatedBy(),new java.sql.Timestamp(System.currentTimeMillis()),kafkaUIDto.getUpdatedBy(),new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}

	@Override
	public String saveUITopic(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO juniper_ext_kafka_topic_master " +
				"(kafka_topic, created_dt,purpose_id) VALUES (?, ?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getTopicName(), new java.sql.Timestamp(System.currentTimeMillis()),kafkaUIDto.getPurpose()
		});
		return "success";
	}

	@Override
	public String onBoardKafka(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO juniper_ext_kafka_cluster_master " +
				"(CLUSTER_NAME, ZOOKEEPER_HOST_NAME, ZOOKEEPER_PORT_NUMBER, USERNAME, CREATED_DATE, UPDATED_DATE) VALUES (?,?,?,?,?,?)" ;
		 jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getClusterName(), kafkaUIDto.getHostName(), kafkaUIDto.getPort(),kafkaUIDto.getUserName(), new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}
}
