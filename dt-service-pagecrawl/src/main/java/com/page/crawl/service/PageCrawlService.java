package com.page.crawl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.page.crawl.dto.AppVO;
import com.page.crawl.dto.PagesVO;

@Service
public class PageCrawlService {
	private static final int MYTHREADS = 30;

	public Map<String, Set<String>> processPageCrawl(AppVO appVO) {
	Map<String, List<String>> pageMap = new HashMap<>();
	List<Map<String, Set<String>>> res = new ArrayList<>();
	Set<String> success = new HashSet<>();
	Set<String> skipped = new HashSet<>();
	Set<String> error = new HashSet<>();
	Map<String, Set<String>> collate = new HashMap<>();

	ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);

	for (PagesVO vo : appVO.getPages()) {
	pageMap.put(vo.getAddress(), vo.getLinks());
	PageCrawlTask page = new PageCrawlTask(vo, pageMap, success, skipped);
	Future<Map<String, Set<String>>> result = executor.submit(page);
	Map<String, Set<String>> collate1;
	try {
	collate1 = result.get();
	success.addAll(collate1.get("success"));
	skipped.addAll(collate1.get("skipped"));

	} catch (InterruptedException | ExecutionException e) {
	e.printStackTrace();
	}
	}
	List<String> successList = new ArrayList<String>(success);
	List<String> skippedList = new ArrayList<String>(skipped);

	for (String sucss : successList) {
	if (!pageMap.containsKey(sucss)) {
	success.remove(sucss);
	error.add(sucss);
	}
	}
	for (String sucss : skippedList) {
	if (!pageMap.containsKey(sucss)) {
	success.remove(sucss);
	error.add(sucss);
	}
	}
	collate.put("success", success);
	collate.put("skipped", skipped);
	collate.put("error", error);
	collate.entrySet().stream()
	.forEach(entry -> System.out.println("Key : " + entry.getKey() + "   Value : " + entry.getValue()));
	;

	return collate;
	}

	public Map<String, Set<String>> processPageCrawl1(AppVO appVO) {
	Map<String, List<String>> pageMap = new HashMap<>();
	Set<String> success = new HashSet<>();
	Set<String> skipped = new HashSet<>();
	Set<String> error = new HashSet<>();
	Map<String, Set<String>> collate = new HashMap<>();

	for (PagesVO vo : appVO.getPages()) {
	System.out.println(vo.getAddress() + "--" + vo.getLinks());
	if (null == pageMap || pageMap.isEmpty()) {
	pageMap.put(vo.getAddress(), vo.getLinks());
	for (String s : vo.getLinks()) {
	success.add(s);
	}

	} else {
	pageMap.put(vo.getAddress(), vo.getLinks());
	for (String s : vo.getLinks()) {
	if (pageMap.containsKey(s)) {
	List<String> compare = pageMap.get(s);
	if (compare.contains(vo.getAddress())) {
	skipped.add(s);
	} else if (!compare.contains(vo.getAddress())) {
	success.add(s);
	}
	} else {
	success.add(s);
	}
	}
	}
	}
	// using for-each loop for iteration over Map.entrySet()
	List<String> successList = new ArrayList<String>(success);
	List<String> skippedList = new ArrayList<String>(skipped);

	for (String sucss : successList) {
	if (!pageMap.containsKey(sucss)) {
	success.remove(sucss);
	error.add(sucss);
	}
	}
	for (String sucss : skippedList) {
	if (!pageMap.containsKey(sucss)) {
	success.remove(sucss);
	error.add(sucss);
	}
	}
	collate.put("success", success);
	collate.put("skipped", skipped);
	collate.put("error", error);

	return collate;

	}
}
