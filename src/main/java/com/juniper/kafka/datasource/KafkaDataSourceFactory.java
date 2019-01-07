package com.juniper.kafka.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class KafkaDataSourceFactory {

	@Bean(name = "datasource")
	@ConfigurationProperties("spring.datasource")
	@Primary
	public DataSource dataSource(){
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "jdbcMaster")
    @Autowired
    public JdbcTemplate masterJdbcTemplate(@Qualifier("dsMaster") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }
}
