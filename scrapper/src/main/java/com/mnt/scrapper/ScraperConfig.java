package com.mnt.scrapper;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class ScraperConfig {
	
	public int thread;
	public List<Scrapper> scrapper;
	
	
	
	public static class Scrapper {
		public boolean active;
		public String site;
		
		public List<Urls> urls;
	}
	
	public static class Urls {
		public String pattern;
		public List<JsonNode> params;
		public int page;
		
	}

}
