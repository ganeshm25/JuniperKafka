package com.juniper.kafka.ui.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.PubSubAdmin;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQuery.DatasetListOption;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Dataset;
import com.google.pubsub.v1.Topic;
import com.juniper.kafka.dto.KafkaUIDTO;
import com.juniper.kafka.dto.RequestDTO;
import com.juniper.kafka.services.KafkaUIService;

@RestController
@RequestMapping(value = "/juniper/kafkaUI")
public class KafkaTopicController {

	@Autowired
	private KafkaUIService kafkaUIService;

	@Autowired
	private PubSubAdmin pubSubAdmin;
	
	@Value("${kafkarealtime.credential.path}")
	private String project_cr_path;
	
	@Value("${spring.cloud.gcp.project-id}")
	private String project_id;
	
	@Value("${temp.kafka.bootstrap}")
	private String bootStrap;

	@RequestMapping(value = "/kafkaTopic", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	@ResponseBody
	public String kafkaTopicCreation(@RequestBody RequestDTO requestDto) {

		KafkaUIDTO kafkaUI = new KafkaUIDTO();

		try {
			kafkaUI.setTopicName(requestDto.getBody().get("data").get("kafka_topic"));
			kafkaUI.setPurpose(requestDto.getBody().get("data").get("purpose"));
			kafkaUI.setClusterID(Integer.parseInt(requestDto.getBody().get("data").get("cluster_id")));
			kafkaUI.setPartitionCount(1);// (requestDto.getBody().get("data").get("partition"));
			kafkaUI.setReplicationFactor(1);// (requestDto.getBody().get("data").get("replication"));
			kafkaUIService.saveTopicDetails(kafkaUI);

		} catch (Exception ex) {
			System.out.println("Exception while saving topic details " + ex);

		}
		return "success";
	}

	@RequestMapping(value = "/activateTopic", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	@ResponseBody
	public String crateKafkaTopic(@RequestBody RequestDTO requestDto) {

		KafkaUIDTO kafkaUI = new KafkaUIDTO();

		try {
			kafkaUI.setTopicID(Integer.parseInt(requestDto.getBody().get("data").get("topicID")));
			// kafkaUI.setActivationFlag(requestDto.getBody().get("data").get("activationFlag"));
			// kafkaUIService.createTopic(kafkaUI);

		} catch (Exception ex) {
			System.out.println("Exception while saving topic details " + ex);

		}
		return "success";
	}

	/*
	 * @RequestMapping(value = "/pubsubTopic", method = { RequestMethod.GET,
	 * RequestMethod.POST}, consumes = "application/json")
	 * 
	 * @ResponseBody public String pubsubTopicCreation(@RequestBody RequestDTO
	 * requestDto){
	 * 
	 * KafkaUIDTO kafkaUI = new KafkaUIDTO();
	 * 
	 * try { kafkaUI.setServiceAccount(requestDto.getBody().get("data").get(
	 * "serviceaccount"));
	 * kafkaUI.setProjectId(requestDto.getBody().get("data").get("project_id"));
	 * kafkaUI.setTopicName(requestDto.getBody().get("data").get("topic_name"));
	 * 
	 * kafkaUIService.savePubSubTopicDetails(kafkaUI);
	 * 
	 * } catch (Exception ex) {
	 * System.out.println("Exception while saving topic details "+ex);
	 * 
	 * } return "success"; }
	 */

	@RequestMapping(value = "/savepubSubTopicdetails", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	@ResponseBody
	public String pubsubTopicCreation(@RequestBody RequestDTO requestDto) {

		KafkaUIDTO kafkaUI = new KafkaUIDTO();
		try {
			kafkaUI.setProjectId(requestDto.getBody().get("data").get("project"));
			kafkaUI.setPubSubTopicName(requestDto.getBody().get("data").get("topic_name"));
			kafkaUI.setPubSubSubscriptionName(requestDto.getBody().get("data").get("topic_name") + "_subscription");
			kafkaUI.setGcProjectId(requestDto.getBody().get("data").get("gcp_id"));
			kafkaUI.setServiceAccount(requestDto.getBody().get("data").get("serviceaccount"));
			pubSubAdmin.createTopic(requestDto.getBody().get("data").get("topic_name"));
			pubSubAdmin.createSubscription(requestDto.getBody().get("data").get("topic_name")+"_subscription",requestDto.getBody().get("data").get("topic_name"));
			kafkaUIService.createPubSub(kafkaUI);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception while Consumer creation " + ex);
		}
		return "success";
	}


	@RequestMapping(value = "/listDataSet", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String,String> listDataSet() {
		
		StringBuffer restStr = new StringBuffer();
		HashMap<String, String> map = new HashMap<>();
		Properties properties = new Properties();
		
		try {
			
			System.out.println(project_cr_path);
			System.out.println(project_id);
			System.out.println(bootStrap);
			
			File credentialsPath = new File(project_cr_path);
			GoogleCredentials credentials;
			
			try (FileInputStream serviceAccountStream = new FileInputStream(credentialsPath)) {
				credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
			}

			BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId(project_id).setCredentials(credentials)
					.build().getService();

			for (Dataset dataset : bigquery.listDatasets().iterateAll()) {
				restStr.append(dataset.getDatasetId().getDataset()+",");
			}
			map.put("dataSet", restStr.toString());
			restStr = new StringBuffer();
			List<Topic> ltpsb = pubSubAdmin.listTopics();
			ListIterator lit = ltpsb.listIterator();
	        while (lit.hasNext()) {
	        	Topic to = (Topic) lit.next();
	        	if(to!=null) {
	        		restStr.append(to.getName().split("/")[3]+",");
	        	}
	        }
	        map.put("pubsubTopic", restStr.toString());
	        
	        
	        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrap);
	        AdminClient adminClient = AdminClient.create(properties);
	        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
	        listTopicsOptions.listInternal(true);
	        System.out.println(adminClient.listTopics(listTopicsOptions).names().get().toString());
	        restStr = new StringBuffer();
	        for (String str : adminClient.listTopics(listTopicsOptions).names().get()) {
	        	restStr.append(str+",");
	        }
	        map.put("kafkaTopics", restStr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
}
