package com.juniper.kafka.dao;

import java.sql.Date;

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
		String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});
		return "success";
	}
	
	@Override
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "success";
	}

	@Override
	public String saveUITopic(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO juniper_ext_kafka_topic_master " +
				"(kafka_topic, created_dt) VALUES (?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getTopicName(), new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}

	@Override
	public String onBoardKafka(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO juniper_ext_kafka_cluster_master " +
				"(CLUSTER_NAME, ZOOKEEPER_HOST_NAME, ZOOKEEPER_PORT_NUMBER, USERNAME, CREATED_DATE, UPDATED_DATE) VALUES (?,?,?,?,?,?)" ;
		int update_flag = jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getClusterName(), kafkaUIDto.getHostName(), kafkaUIDto.getPort(),kafkaUIDto.getUserName(), new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}
}
