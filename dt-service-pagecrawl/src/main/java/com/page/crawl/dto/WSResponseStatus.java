package com.page.crawl.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class WSResponseStatus implements Serializable{
	private static final long serialVersionUID=1L;
	private String status;
	private String statusCode;
	private String statusMessage;
	private Map<String,Set<String>> collate;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Map<String, Set<String>> getCollate() {
		return collate;
	}
	public void setCollate(Map<String, Set<String>> collate) {
		this.collate = collate;
	}


}
