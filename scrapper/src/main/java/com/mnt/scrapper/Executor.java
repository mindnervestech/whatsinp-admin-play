package com.mnt.scrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Executor {
	static Properties prop = new Properties();
	static {
		try {
			InputStream input = null;
	
			input = Executor.class.getClassLoader()
	                .getResourceAsStream("app.properties");
			prop.load(input);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String root = prop.getProperty("rootPath");
	public static String pathToRootJs =  root + "scrapper"+File.separator+"js"+File.separator;
	public static String pathToOutput =  root + "scrapper"+File.separator+"output"+File.separator;
	public static String pathToPhantom =  root + "scrapper"+File.separator+"win"+File.separator+"phantomjs";
	public static String pathToPjscrape =  pathToRootJs + "pjscrape.js";
	public static ScraperConfig scraperConfig;
	
	static {
		
		String os = System.getProperty("os.name").toLowerCase();
		if(os.indexOf("win") >=0 ) {
			pathToPhantom =  root + "scrapper"+File.separator+"win"+File.separator+"phantomjs";
		} else if (os.indexOf("mac") >=0 ) {
			pathToPhantom =  root + "scrapper"+File.separator+"mac"+File.separator+"phantomjs";
		}
		
		InputStream srcapperConfig = Executor.class.getClassLoader().getResourceAsStream("scrapper.json");
		ObjectMapper jsonConfigMapper = new ObjectMapper();
		
		try {
			scraperConfig = jsonConfigMapper.readValue(srcapperConfig, ScraperConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} 
		
	}
	public static void main(String[] args) {
		List<Task> tasks = new ArrayList<>();
		ExecutorService svc = Executors.newFixedThreadPool(scraperConfig.thread);
		//Ebean.find(ScrappedData.class).findRowCount();
		for(ScraperConfig.Scrapper scrapper : scraperConfig.scrapper) {
			if(scrapper.active) {
				if(scrapper.urls != null && !scrapper.urls.isEmpty() ) {
					for(ScraperConfig.Urls urls : scrapper.urls) {
						List<String> siteUrl = new ArrayList<>();
						buildSiteUrl(urls, siteUrl);
						tasks.add(new Task(scrapper.site,siteUrl));
					}
				} else {
					tasks.add(new Task(scrapper.site));
				}
				
				for(Task t:tasks) {
					svc.execute(t);
				}
			}
		}
		
		svc.shutdown();
		
	}
	private static void buildSiteUrl(ScraperConfig.Urls urls, List<String> siteUrl) {
		String urlPattern = urls.pattern;
		int page = urls.page;
		if(urls.params != null) {
			int i=1;
			for(JsonNode node : urls.params) {
				String paramPattern = "{{p" + i + "}}";
				if(urlPattern.indexOf(paramPattern) !=-1) {
					JsonNode valNode = node.get("value");
					if(!valNode.isArray()) {
						urlPattern = urlPattern.replace(paramPattern,valNode.asText());
					} else {
						Iterator<JsonNode> iterator = valNode.elements();
						while(iterator.hasNext()) {
							String tempUrlPattern = urlPattern ;
							JsonNode valNodeCell = iterator.next();
							tempUrlPattern = tempUrlPattern.replace(paramPattern,valNodeCell.asText());
							if(urlPattern.indexOf("{{page}}") !=-1) {
								Integer pageIndex = page;
								while(pageIndex > 0) {
									String tempUrlPatternWithPage = tempUrlPattern;
									tempUrlPatternWithPage = tempUrlPatternWithPage.replace("{{page}}",pageIndex.toString());
									siteUrl.add(tempUrlPatternWithPage);
									pageIndex--;
								}
							} else {
								siteUrl.add(tempUrlPattern);
							}
						}
					}
				}
				i++;
			}
		}
		else {
			if(urlPattern.indexOf("{{page}}") !=-1) {
				Integer pageIndex = page;
				while(pageIndex > 0) {
					String tempUrlPatternWithPage = urlPattern;
					tempUrlPatternWithPage = tempUrlPatternWithPage.replace("{{page}}",pageIndex.toString());
					siteUrl.add(tempUrlPatternWithPage);
					pageIndex--;
				}
			} else {
				siteUrl.add(urlPattern);
			}
		}
		
	}
	
	

}
