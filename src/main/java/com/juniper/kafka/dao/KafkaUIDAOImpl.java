package com.juniper.kafka.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import  org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.juniper.kafka.dto.KafkaUIDTO;

public class KafkaUIDAOImpl extends JdbcDaoSupport implements KafkaUIDAO{

	
	@Autowired
    @Qualifier("jdbcMaster")
    private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public String saveUIProducer(KafkaUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}
	
	@Override
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}

	@Override
	public String saveUITopic(KafkaUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}
}
