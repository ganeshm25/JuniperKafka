package com.juniper.kafka.dto;

public class ConsumerKafkaUIDTO {
	
  private String hostName;
  private String port;
  private String topicName;
  private String consumerName;
  //private int noConsumer;
  
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

/*public int getNoConsumer() {
	return noConsumer;
}

public void setNoConsumer(int noConsumer) {
	this.noConsumer = noConsumer;
}*/
  
}
