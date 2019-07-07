package com.page.crawl.api.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.page.crawl.controller.PageCrawlController;
import com.page.crawl.dto.AppVO;
import com.page.crawl.dto.PagesVO;
import com.page.crawl.dto.WSResponseStatus;
import com.page.crawl.service.PageCrawlService;


public class PageCrawlControllerTest {

	@Autowired
	private PageCrawlService pageCrawlServce;

	@Test
	public void testPageCrawl() throws Exception {

		String address1 = "page-1";
		String address2 = "page-2";
		ArrayList<String> link1 = new ArrayList<>();
		link1.add("page-2");
		link1.add("page-3");
		ArrayList<String> link2 = new ArrayList<>();
		link2.add("page-1");

		PagesVO page1 = new PagesVO();
		page1.setAddress(address1);
		page1.setLinks(link1);
		PagesVO page2 = new PagesVO();
		page2.setAddress(address2);
		page2.setLinks(link2);

		ArrayList<PagesVO> pages = new ArrayList<>();
		pages.add(page1);
		pages.add(page2);
		AppVO app = new AppVO();
		app.setPages(pages);
		PageCrawlController controller = new PageCrawlController();
		//controller.pageCrawl(app).getStatusCode();
	     ResponseEntity<WSResponseStatus> wSResponseStatusResult = controller.pageCrawl(app);
        assertEquals(Response.Status.OK.getStatusCode(), wSResponseStatusResult.getStatusCodeValue());


	}

	
	@Test
	public void testPageCrawlNullTest() throws Exception {

		String address1 = "page-1";
		String address2 = "page-2";
		ArrayList<String> link1 = new ArrayList<>();
		link1.add("page-2");
		link1.add("page-3");
		ArrayList<String> link2 = new ArrayList<>();
		link2.add("page-1");

		PagesVO page1 = new PagesVO();
		page1.setAddress(address1);
		page1.setLinks(link1);
		PagesVO page2 = new PagesVO();
		page2.setAddress(null);
		page2.setLinks(null);

		ArrayList<PagesVO> pages = new ArrayList<>();
		pages.add(page1);
		pages.add(page2);
		AppVO app = new AppVO();
		app.setPages(pages);
		PageCrawlController controller = new PageCrawlController();
		controller.pageCrawl(null);
		ResponseEntity<WSResponseStatus> wSResponseStatusResult = controller.pageCrawl(null);
		assertNotNull(wSResponseStatusResult);

	}

}