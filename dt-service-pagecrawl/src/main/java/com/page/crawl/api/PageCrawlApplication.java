package com.page.crawl.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.page.crawl")
@SpringBootApplication
public class PageCrawlApplication {

  
    public static void main(String[] args) {
        SpringApplication.run(PageCrawlApplication.class, args);
    }

}
