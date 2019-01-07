package com.juniper.kafka.dto;

import java.util.Map;

public class RequestDTO {
	

	private Map<String, String> header;
	private Map<String, Map<String, String>> body;
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

 
 
}
