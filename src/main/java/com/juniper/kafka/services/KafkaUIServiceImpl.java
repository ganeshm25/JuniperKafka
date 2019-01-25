package com.juniper.kafka.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.Topic;
import com.google.pubsub.v1.TopicName;
import com.google.pubsub.v1.TopicNames;
import com.juniper.kafka.dao.KafkaUIDAO;
import com.juniper.kafka.dto.KafkaUIDTO;

@Service
public class KafkaUIServiceImpl implements KafkaUIService {

	@Autowired
	KafkaUIDAO kafkaUIDAO;

	
	public String saveProducerDetials(KafkaUIDTO kafkaDTO) {
		kafkaUIDAO.saveUIProducer(kafkaDTO);
		return "success";
	}
	
	public String saveConsumerDetails(KafkaUIDTO consumerkafkaUIDTO) {
		kafkaUIDAO.saveUIConsumer(consumerkafkaUIDTO);
		return "success";
	}
	
	public String saveTopicDetails(KafkaUIDTO kafkatopicUIDTO) {
		Properties properties = null;
		AdminClient adminClient = null;
		NewTopic newTopic = null;
		List<NewTopic> newTopics = null;
		HashMap<String,Object> clusterDetail = null;
		try {
			clusterDetail = kafkaUIDAO.fetchCluster(kafkatopicUIDTO.getClusterID());
			properties = new Properties();
			properties.put("bootstrap.servers", clusterDetail.get("zookeeper_host_name")+":"+clusterDetail.get("zookeeper_host_port"));
			properties.put("enable.auto.commit", true);
			properties.put("auto.commit.interval.ms", 100);
			properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			adminClient = AdminClient.create(properties);
			newTopic = new NewTopic(kafkatopicUIDTO.getTopicName(), 0, (short)0); //new NewTopic(topicName, numPartitions, replicationFactor)
			newTopics = new ArrayList<NewTopic>();
			newTopics.add(newTopic);
			CreateTopicsResult ctr =  adminClient.createTopics(newTopics);
			kafkaUIDAO.saveUITopic(kafkatopicUIDTO);
		}catch(Exception e) {
			
		}finally {
			if(adminClient!=null) {
				adminClient.close();
			}
		}
		
		return "success";
	}

	@Override
	public String onBoardKafka(KafkaUIDTO kafkatopicUIDTO) {
		
		kafkaUIDAO.onBoardKafka(kafkatopicUIDTO);
		return "success";
	}

	@Override
	public String savePubSubTopicDetails(KafkaUIDTO kafkatopicUIDTO) {
		
		try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
		     
		      ProjectTopicName topicName = ProjectTopicName.of(kafkatopicUIDTO.getProjectId(), kafkatopicUIDTO.getTopicName());
		      topicAdminClient.createTopic(topicName);
		      
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "success";
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see com.juniper.kafka.services.KafkaUIService#createTopic(com.juniper.kafka.dto.KafkaUIDTO)
	 * 
	 *  bootstrap.servers=localhost:9092
		group.id=test
		enable.auto.commit=true
		auto.commit.interval.ms=1000
		key.deserializer=org.apache.kafka.common.serialization.StringDeserializer
		value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
	 * 
	 */

	/*@Override
	public String createTopic(int clusterId) {
		Properties properties = null;
		AdminClient adminClient = null;
		NewTopic newTopic = null;
		List<NewTopic> newTopics = null;
		try {
			properties = new Properties();
			properties.put("bootstrap.servers", "localhost:9092");
			properties.put("enable.auto.commit", true);
			properties.put("auto.commit.interval.ms", 100);
			properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			adminClient = AdminClient.create(properties);
			newTopic = new NewTopic("testTopic", 0, (short)0); //new NewTopic(topicName, numPartitions, replicationFactor)
			newTopics = new ArrayList<NewTopic>();
			newTopics.add(newTopic);
			CreateTopicsResult ctr =  adminClient.createTopics(newTopics);
			//kafkaUIDAO.activateTopic(kafkatopicUIDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(adminClient!=null) {
				adminClient.close();
			}
		}
		return "success";
	}*/
}
