package com.juniper.kafka.ui.webservice;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.juniper.kafka.dto.KafkaUIDTO;
import com.juniper.kafka.dto.RequestDTO;
import com.juniper.kafka.services.KafkaUIService;

@RestController
@RequestMapping(value="/juniper/kafkaUI")
public class KafkaDataFeedController {

	@Autowired
	private KafkaUIService kafkaUIService;
	
	@Value("${kafkarealtime.credential.path}")
	private String project_cr_path;
	
	@Value("${kafkarealtime.shellscript.path}")
	private String dataflow_shellscript_path;

	@RequestMapping(value = "/saveDataFeed", method = { RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
	@ResponseBody
	public String deployFeed(@RequestBody RequestDTO requestDto) {

		try {
			
				KafkaUIDTO kafkaUI = new KafkaUIDTO();
				kafkaUI.setClusterID(Integer.parseInt(requestDto.getBody().get("data").get("cluster_name")));
				kafkaUI.setTopicName(requestDto.getBody().get("data").get("kafka_topic"));
				kafkaUI.setServiceAccount(requestDto.getBody().get("data").get("service_account"));
				kafkaUI.setPubSubTopicId(requestDto.getBody().get("data").get("pubsub_topic"));
				kafkaUI.setFeedName(requestDto.getBody().get("data").get("feed_name"));
				kafkaUI.setDataSet(requestDto.getBody().get("data").get("dataset"));
				kafkaUI.setGcProjectId(requestDto.getBody().get("data").get("project_name"));
				kafkaUIService.saveDataFeedDetails(kafkaUI);
				
				//String credentialPath = "/home/musthafa_ajp/scripts/JUNIPERONPREM-c59bf3f7bd73.json";
				String gcpProjectName = requestDto.getBody().get("data").get("gcp_name");
				String dataset = requestDto.getBody().get("data").get("dataset");
				String kafka_topic_name = requestDto.getBody().get("data").get("kafka_topic");
				String pubsub_topic_name = requestDto.getBody().get("data").get("pubsub_topic");
				String bootstrapServer = kafkaUIService.fetchClusterAddress(Integer.parseInt(requestDto.getBody().get("data").get("cluster_name")));
				String pubsub_subcription_name = requestDto.getBody().get("data").get("pubsub_topic")+"_subscription";
				System.out.println(dataflow_shellscript_path);
				System.out.println(project_cr_path);
				System.out.println(bootstrapServer);
				System.out.println(gcpProjectName);
				System.out.println(pubsub_topic_name);
				System.out.println(kafka_topic_name);
				System.out.println(pubsub_subcription_name);
				System.out.println(dataset);
				/*
				 * CreatePubSubBQPipeline
				 * credentialPath,bootstrapServer,gcpProjectName,pubsub_topic_name,kafka_topic_name,pubsub_subcription_name,dataset
				 * /home/musthafa_ajp/scripts/KafkaBQDataFlow.sh 35.237.8.127:9092 testrazak GCPTOPICTEST demotst demosubscription DemoDataSet
				 *String cmd[] = {"/bin/sh","/home/musthafa_ajp/scripts/KafkaBQDataFlow.sh","/home/musthafa_ajp/scripts/JUNIPERONPREM-c59bf3f7bd73.json","35.237.8.127:9092","juniperonprem","testTopicPubSub","demotst","testSubscriptions","ONPREM_DB"};
				 */
				
				//String cmd[] = {"/home/musthafa_ajp/scripts/KafkaBQDataFlow.sh",credentialPath,bootstrapServer,gcpProjectName,pubsub_topic_name,kafka_topic_name,pubsub_subcription_name,dataset};
				String cmd[] = {dataflow_shellscript_path,project_cr_path,bootstrapServer,gcpProjectName,pubsub_topic_name,kafka_topic_name,pubsub_subcription_name,dataset};
				Process p1=Runtime.getRuntime().exec(cmd);
	            OutputStream st1=p1.getOutputStream();
	            System.out.println( "");
	            System.out.println(st1);
	            int i=p1.waitFor();
	            int j=p1.exitValue();
	            if(j==0)
	            {
	            	System.out.println("Script Execution Successful");
	            }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Kafka is not available, timed out after {} ms");
			
		}

		return "Dataflow Desployed Successfully!.";
	}
}
