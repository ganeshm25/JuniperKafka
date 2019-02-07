package com.juniper.kafka.dao;
import java.util.List;
import java.util.Map;

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
		String sql = "INSERT INTO JUNIPER_KAFKA_PRODUCER_MASTER " +
				"(PRODUCER_NAME,CLUSTER_ID,TOPIC_ID,SOURCE_TYPE,FILE_PATH,FILE_NAME,SOURCE_ID,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getProducerName(), kafkaUIDto.getClusterID(),kafkaUIDto.getTopicID(),kafkaUIDto.getTargetType(),kafkaUIDto.getFilePath(),kafkaUIDto.getFileName(),kafkaUIDto.getSourceID(),kafkaUIDto.getCreatedBy(),new java.sql.Timestamp(System.currentTimeMillis()),kafkaUIDto.getUpdatedBy(),new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}
	
	
	@Override
	public String saveUIConsumer(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO JUNIPER_KAFKA_CONSUMER_MASTER " +
				"(CONSUMER_NAME,TOPIC_ID,SOURCE_ID,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?,?,?,?,?,?,?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getConsumerName(), kafkaUIDto.getTopicID(),kafkaUIDto.getSourceID(),kafkaUIDto.getCreatedBy(),new java.sql.Timestamp(System.currentTimeMillis()),kafkaUIDto.getUpdatedBy(),new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}

	@Override
	public String saveUITopic(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO juniper_kafka_topic_master " +
				"(kafka_topic, created_dt,BROKER_HOST,BROKER_PORT,BROKER_USERNAME,BROKER_PASSWORD,purpose,topic_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" ;
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getTopicName(), new java.sql.Timestamp(System.currentTimeMillis()),kafkaUIDto.getHostName(),kafkaUIDto.getPort(),kafkaUIDto.getUserName(),null,kafkaUIDto.getPurpose(),"A"
		});
		return "success";
	}

	@Override
	public String onBoardKafka(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO juniper_kafka_cluster_master" +
				"(CLUSTER_NAME, ZOOKEEPER_HOST_NAME, ZOOKEEPER_PORT_NUMBER, CREATED_DATE, UPDATED_DATE,PROJECT_SEQUENCE) VALUES (?,?,?,?,?,?)" ;
		
		
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getClusterName(), kafkaUIDto.getHostName(), kafkaUIDto.getPort(), new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis()),kafkaUIDto.getProjectId()
		});
		return "success";
	}


	@Override
	public Map<String, Object> fetchCluster(int clusterId) {
		String sql = "select zookeeper_host_name,zookeeper_port_number from juniper_kafka_cluster_master where KAFKA_CONN_SEQUENCE="+clusterId;
		Map<String, Object> result=  jdbcTemplateObject.queryForMap(sql);
		return (Map<String, Object>) result;
	}
	
	@Override
	public String savePubSubTopic(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO JUNIPER_KAFKA_PUB_SUB_TOPIC" +
				"(TOPIC_NAME, SERVICE_ACCOUNT, PROJECT_ID, CREATED_DATE, UPDATED_DATE) VALUES (?,?,?,?,?)" ;
		
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getTopicName(), kafkaUIDto.getServiceAccount(), kafkaUIDto.getProjectId(), new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}
	@Override
	public String saveDataFeedDetails(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO JUNIPER_KAFKA_DATA_FEED" +
				"(FEED_NAME, SERVICE_ACCOUNT, CLUSTER_ID, KAFKA_TOPIC,GC_PROJECT,PUB_SUB_TOPIC, DATA_SET,CREATED_DATE,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?)" ;
		
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getFeedName(),kafkaUIDto.getServiceAccount(),kafkaUIDto.getClusterID(), kafkaUIDto.getTopicName(), kafkaUIDto.getGcProjectId(), kafkaUIDto.getPubSubTopicId(),kafkaUIDto.getDataSet(),new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}


	@Override
	public String createPubSub(KafkaUIDTO kafkaUIDto) {
		String sql = "INSERT INTO juniper_kafka_pub_sub_topic" +
				"(PUBSUB_TOPIC_NAME, SERVICE_ACCOUNT, PROJECT_ID, GC_PROJECT_ID, SUBSCRIPTION_NAME,CREATED_DATE, UPDATED_DATE) VALUES (?,?,?,?,?,?,?)" ;
		
		jdbcTemplateObject.update(sql, new Object[]{
				kafkaUIDto.getPubSubTopicName(),kafkaUIDto.getServiceAccount(),201, Integer.parseInt(kafkaUIDto.getGcProjectId()),kafkaUIDto.getPubSubSubscriptionName() ,new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis())
		});
		return "success";
	}


	@Override
	public String fetchClusterAddress(int clusterId) {
		String clusterAddress = "";
		String sql = "select zookeeper_host_name,zookeeper_port_number from juniper_kafka_cluster_master where KAFKA_CONN_SEQUENCE="+clusterId;
		List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql);
		for (Map row : rows) {
			clusterAddress = (row.get("zookeeper_host_name")+":"+row.get("zookeeper_port_number"));
			
		}
		return clusterAddress;
	}
	
}
