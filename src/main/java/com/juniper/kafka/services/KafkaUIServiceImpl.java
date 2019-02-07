package com.juniper.kafka.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.juniper.kafka.dao.KafkaUIDAO;
import com.juniper.kafka.dto.KafkaUIDTO;
import com.juniper.kafka.pubsub.sink.CloudPubSubSinkTask;

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
		Map<String, Object> clusterDetail = null;
		try {
			clusterDetail = kafkaUIDAO.fetchCluster(kafkatopicUIDTO.getClusterID());
			properties = new Properties();
			properties.put("bootstrap.servers",
					clusterDetail.get("zookeeper_host_name") + ":" + clusterDetail.get("zookeeper_port_number"));
			properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			adminClient = AdminClient.create(properties);
			adminClient.createTopics(Arrays.asList(new NewTopic(kafkatopicUIDTO.getTopicName(), 1, (short) 1)));
			kafkaUIDAO.saveUITopic(kafkatopicUIDTO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (adminClient != null) {
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
		try {

			String prg = "import sys";
			BufferedWriter out = new BufferedWriter(new FileWriter("path/a.py"));
			out.write(prg);
			out.close();
			Process p = Runtime.getRuntime().exec("python path/a.py");
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String ret = in.readLine();
			System.out.println("value is : " + ret);

			kafkaUIDAO.savePubSubTopic(kafkatopicUIDTO);

		} catch (Exception e) {
			System.out.println("in exception block");
			e.printStackTrace();
		}
		return "success";
	}

	@Override
	public String saveDataFeedDetails(KafkaUIDTO kafkatopicUIDTO) {
		try {
			kafkaUIDAO.saveDataFeedDetails(kafkatopicUIDTO);

		} catch (Exception e) {
			System.out.println("in exception block");
			e.printStackTrace();
		}
		return "success";
	}

	@Override
	public String createPubSub(KafkaUIDTO kafkatopicUIDTO) {
		try {
			kafkaUIDAO.createPubSub(kafkatopicUIDTO);
		} catch (Exception e) {
			System.out.println("in exception block");
			e.printStackTrace();
		}
		return "success";
	}

	public void kafkaConsumer(Properties props, String topic) {
		Map<String, String> gcpMap = new HashMap<String, String>();
		try {
			gcpMap.put("cps.project", "testrazak");
			gcpMap.put("cps.topic", "GCPTOPICTEST");
			gcpMap.put("gcp.credentials.file.path", "D:\\pocgithub\\JuniperKafka\\src\\main\\resources\\testrazak-523ecb8ca86f.json");
			CloudPubSubSinkTask cptask = new CloudPubSubSinkTask();
			cptask.start(gcpMap);
			KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
			Map<String, String> attributes = new HashMap<>();
			consumer.subscribe(Arrays.asList(topic));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(),
							record.value());
					PubsubMessage.Builder builder = PubsubMessage.newBuilder();
					PubsubMessage message = builder.setData(ByteString.copyFromUtf8(record.value())).putAllAttributes(attributes).build();
					cptask.publishMessage(record.topic(), 1, message);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String fetchClusterAddress(int clusterId) {
		String cluster = "";
		try {
			cluster = kafkaUIDAO.fetchClusterAddress(clusterId);

		} catch (Exception e) {
			System.out.println("in exception block");
			e.printStackTrace();
		}
		return cluster;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juniper.kafka.services.KafkaUIService#createTopic(com.juniper.kafka.dto.
	 * KafkaUIDTO)
	 * 
	 * bootstrap.servers=localhost:9092 group.id=test enable.auto.commit=true
	 * auto.commit.interval.ms=1000
	 * key.deserializer=org.apache.kafka.common.serialization.StringDeserializer
	 * value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
	 * 
	 */

	/*
	 * @Override public String createTopic(int clusterId) { Properties properties =
	 * null; AdminClient adminClient = null; NewTopic newTopic = null;
	 * List<NewTopic> newTopics = null; try { properties = new Properties();
	 * properties.put("bootstrap.servers", "localhost:9092");
	 * properties.put("enable.auto.commit", true);
	 * properties.put("auto.commit.interval.ms", 100);
	 * properties.put("key.deserializer",
	 * "org.apache.kafka.common.serialization.StringDeserializer");
	 * properties.put("value.deserializer",
	 * "org.apache.kafka.common.serialization.StringDeserializer"); adminClient =
	 * AdminClient.create(properties); newTopic = new NewTopic("testTopic", 0,
	 * (short)0); //new NewTopic(topicName, numPartitions, replicationFactor)
	 * newTopics = new ArrayList<NewTopic>(); newTopics.add(newTopic);
	 * CreateTopicsResult ctr = adminClient.createTopics(newTopics);
	 * //kafkaUIDAO.activateTopic(kafkatopicUIDTO);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }finally { if(adminClient!=null)
	 * { adminClient.close(); } } return "success"; }
	 */
}
