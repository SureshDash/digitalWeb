package com.page.crawl.dto;

import java.util.ArrayList;

public class PagesVO {
	private String address;
	private ArrayList<String> links;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<String> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<String> links) {
		this.links = links;
	}

}
