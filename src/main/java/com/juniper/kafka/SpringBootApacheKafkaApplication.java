package com.juniper.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.juniper")
public class SpringBootApacheKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApacheKafkaApplication.class, args);
	}

}

