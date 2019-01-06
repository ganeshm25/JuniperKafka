package com.juniper.kafka.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.juniper.kafka.dto.ConsumerKafkaUIDTO;
import com.juniper.kafka.dto.KafkaTopicUIDTO;
import com.juniper.kafka.dto.KafkaUIDto;

public class KafkaUIDAOImpl extends JdbcDaoSupport implements KafkaUIDAO{

	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public String saveUIProducer(KafkaUIDto kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}
	
	@Override
	public String saveUIConsumer(ConsumerKafkaUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}

	@Override
	public String saveUITopic(KafkaTopicUIDTO kafkaUIDto) {
		/*String sql = "INSERT INTO juiperkafka " +
				"(host, port) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				kafkaUIDto.getHostName(), kafkaUIDto.getPort()
		});*/
		return "";
	}
}
