package com.page.crawl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import com.page.crawl.dto.PagesVO;

public class PageCrawlTask implements Callable<Map<String, Set<String>>> {

	private PagesVO pagesVO;
	private Map<String, List<String>> pageMap;
	private Set<String> success;
	private Set<String> skipped;
	private Set<String> error;

	public PageCrawlTask(PagesVO pagesVO, Map<String, List<String>> pageMap, Set<String> success, Set<String> skipped,
			Set<String> error) {
		this.pagesVO = pagesVO;
		this.pageMap = pageMap;
		this.success = success;
		this.skipped = skipped;
		this.error = error;

	}

	@Override
	public Map<String, Set<String>> call() throws Exception {
		Map<String, Set<String>> collate = new HashMap<>();
		System.out.println(pagesVO.getAddress() + "--" + pagesVO.getLinks());
		success.add("page-01");// its deault page
		for (String page : pagesVO.getLinks()) {
			if (pageMap.containsKey(page)) {
				if (success.contains(pagesVO.getAddress())) {
					if (!success.contains(page)) {
						success.add(page);
					} else {
						skipped.add(page);
					}
				}
			} else {
				if (error.contains(page)) {
					skipped.add(page);
				}
				error.add(page);
			}

		}
		collate.put("success", success);
		collate.put("skipped", skipped);
		collate.put("error", error);
		return collate;
	}
}
