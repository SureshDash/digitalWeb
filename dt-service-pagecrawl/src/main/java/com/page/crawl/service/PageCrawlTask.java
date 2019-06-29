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

	public PageCrawlTask(PagesVO pagesVO, Map<String, List<String>> pageMap, Set<String> success, Set<String> skipped) {
		this.pagesVO = pagesVO;
		this.pageMap = pageMap;
		this.success = success;
		this.skipped = skipped;

	}

	@Override
	public Map<String, Set<String>> call() throws Exception {
		Map<String, Set<String>> collate = new HashMap<>();
		System.out.println(pagesVO.getAddress() + "--" + pagesVO.getLinks());
		for (String s : pagesVO.getLinks()) {
			if (pageMap.containsKey(s)) {
				List<String> compare = pageMap.get(s);
				if (success.contains(s)) {
					skipped.add(s);
				} else // if (!compare.contains(s))
				{
					success.add(s);
				}
			} else {
				success.add(s);
			}

		}

		collate.put("success", success);
		collate.put("skipped", skipped);
// collate.put("error", error);
		return collate;
	}
}
