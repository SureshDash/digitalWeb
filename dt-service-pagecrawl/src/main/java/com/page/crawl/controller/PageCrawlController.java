package com.page.crawl.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.page.crawl.constants.PageCrawlGlobalConstants;
import com.page.crawl.dto.AppVO;
import com.page.crawl.dto.PagesVO;
import com.page.crawl.dto.WSResponseStatus;
import com.page.crawl.exceptions.PageCrawlExceptions;
import com.page.crawl.service.PageCrawlService;

@RestController
@RequestMapping(value = "/page")
public class PageCrawlController {
	private static final Logger logger = Logger.getLogger(PageCrawlController.class);

	@Autowired
	PageCrawlService pageCrawlService;

	
	@RequestMapping(value = "/crawl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<WSResponseStatus> pageCrawl(@RequestBody AppVO appVO) throws PageCrawlExceptions {
		WSResponseStatus wsResponseStatus = null;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		AppVO AppVo;
		try {
			Map<String, Set<String>> collate = pageCrawlService.processPageCrawl(appVO);

			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(PageCrawlGlobalConstants.SUCCESS);
			wsResponseStatus.setStatusCode(PageCrawlGlobalConstants.SUCCESS_CODE);
			wsResponseStatus.setStatusMessage("page crawl data  extracted successfully");
			wsResponseStatus.setCollate(collate);
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.OK);
		} catch (PageCrawlExceptions e) {
			logger.error(PageCrawlGlobalConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(PageCrawlGlobalConstants.FAILURE);
			wsResponseStatus.setStatusCode(PageCrawlGlobalConstants.ERR_CODE_BAD_REQUEST);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(PageCrawlGlobalConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(PageCrawlGlobalConstants.FAILURE);
			wsResponseStatus.setStatusCode(PageCrawlGlobalConstants.ERR_CODE_INT_SERVER_ERR);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
