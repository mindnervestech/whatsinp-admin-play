package com.mnt.scrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mnt.scrapper.model.ScrappedData;

public class Task implements Runnable{

	private String pathToSiteConfig;
	private String pathToScraperOutput;
	private List<String> siteUrl;
	public Task(String site) {
		this(site, null);
	}
	public Task(String site, List<String> siteUrl) {
		this.pathToSiteConfig = Executor.pathToRootJs + site + ".js";
		this.pathToScraperOutput = Executor.pathToOutput + site +"_"+System.currentTimeMillis() + ".";
		this.siteUrl = siteUrl;
	}
	@Override
	public void run() {
		try {
			Process  p ;
			/*Process  p = Runtime.getRuntime().exec(
					Executor.pathToPhantom + " " 
			      + Executor.pathToPjscrape + " " 
			      + pathToSiteConfig + " "
			      + pathToScraperOutput);*/
			
			int ret=0;
			try{
				ProcessBuilder builder = new ProcessBuilder();
				if(siteUrl == null) {
				builder.command(Executor.pathToPhantom, 
			       Executor.pathToPjscrape, 
			       pathToSiteConfig,
			       pathToScraperOutput + "JSON");
				} else {
					List<String> commands = new ArrayList<>();
					commands.add(Executor.pathToPhantom);
					commands.add(Executor.pathToPjscrape);
					commands.add(pathToSiteConfig);
					commands.add(pathToScraperOutput + "JSON");
					commands.addAll(siteUrl);
					builder.command(commands);
					
				}
				p = builder/*.redirectInput(new File(pathToScraperOutput + "input"))*/
						.redirectOutput(new File(pathToScraperOutput + "log"))
						.redirectError(new File(pathToScraperOutput + "err")).start();
				System.out.println("Start: " + Thread.currentThread().getId());
				ret = p.waitFor();
			} catch(Exception ex) {
				System.out.println(ex);
			    throw new RuntimeException(ex);
			}
			
			if (ret == 0) {
				ObjectMapper mapper = new ObjectMapper();
				List<ScrappedData> data = mapper.readValue(new File(pathToScraperOutput+"JSON"), new TypeReference<List<ScrappedData>>() {});
				Ebean.save(data);
			}
			System.out.println("End: " + Thread.currentThread().getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
