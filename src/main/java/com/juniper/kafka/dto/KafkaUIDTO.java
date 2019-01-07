package com.juniper.kafka.dto;

import java.util.Map;

public class KafkaUIDTO {
	
	private Map<String, String> header;
	private Map<String, Map<String, String>> body;
	private String hostName;
	private String port;
	private String topicName;
	private String consumerName;
	private String producerName;
	
	public Map<String, String> getHeader() {
		return header;
	}
	public void setHeader(Map<String, String> header) {
		this.header = header;
	}
	public Map<String, Map<String, String>> getBody() {
		return body;
	}
	public void setBody(Map<String, Map<String, String>> body) {
		this.body = body;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	
	

  
}
