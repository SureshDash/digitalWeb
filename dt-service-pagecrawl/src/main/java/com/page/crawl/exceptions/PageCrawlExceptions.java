package com.page.crawl.exceptions;

public class PageCrawlExceptions extends RuntimeException{
	private static final long serialVersionUID=1L;
private Object data;


public PageCrawlExceptions(String errorMsg) {
	super(errorMsg);
	
}

public PageCrawlExceptions(String errorMsg, Object data) {
	super(errorMsg);
	this.data=data;
	
	
}


public Object getData() {
	return data;
}

public void setData(Object data) {
	this.data = data;
}

}
