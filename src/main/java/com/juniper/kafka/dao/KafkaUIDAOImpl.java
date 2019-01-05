package com.juniper.kafka.dao;

import com.juniper.kafka.dto.KafkaUIDto;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.*;
import  org.springframework.jdbc.core.support.JdbcDaoSupport;

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

}
