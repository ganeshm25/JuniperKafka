package com.juniper.kafka.dto;

public class KafkaTopicUIDTO {
	
  private String hostName;
  private String port;
  private String topicName;
  //private String noReplication;
  //private int noPartition;
  
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
    
    
}
