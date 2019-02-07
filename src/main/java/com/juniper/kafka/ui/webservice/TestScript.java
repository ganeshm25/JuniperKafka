package com.juniper.kafka.ui.webservice;

import java.io.IOException;
import java.io.OutputStream;

public class TestScript {

	public static void main(String[] args) {
		
			try {
					String cmd[] = {"/bin/sh","/home/musthafa_ajp/scripts/KafkaBQDataFlow.sh","/home/musthafa_ajp/scripts/JUNIPERONPREM-c59bf3f7bd73.json","35.237.8.127:9092","juniperonprem","testTopicPubSub","demotst","testSubscriptions","ONPREM_DB"};
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


	}

}
