package com.juniper.kafka.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

public class KafkaDataSourceFactory {

	@Bean(name = "datasource")
	@ConfigurationProperties("spring.datasource")
	@Primary
	public DataSource dataSource(){
	    return DataSourceBuilder.create().build();
	}
}
