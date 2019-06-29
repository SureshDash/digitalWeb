package com.page.crawl.dto;

import java.util.ArrayList;

public class AppVO {

	public ArrayList<PagesVO> getPages() {
		return pages;
	}

	public void setPages(ArrayList<PagesVO> pages) {
		this.pages = pages;
	}

	private ArrayList<PagesVO> pages;
}
