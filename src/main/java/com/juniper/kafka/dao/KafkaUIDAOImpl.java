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
		String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});
		return "";
	}
	
	@Override
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}

	@Override
	public String saveUITopic(KafkaUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}
}
