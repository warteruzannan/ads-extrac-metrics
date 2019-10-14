package br.com.ads.extract.metrics.helpers;

import java.util.Arrays;
import java.util.List;

public class CommandLineParser {	
	private List<String> args;
	
	public CommandLineParser(String[] args) {		
		this.args = Arrays.asList(args);
	}
	
	public List<String> getArgs() {
		return this.args;
	}
	
	public boolean analisysAllInRepository() {
		return  this.args.contains("r");			
	}	
	
	public boolean useDefaultOutput() {
		return  this.args.contains("-p");			
	}
	
	public String getRepository() {
		return this.args.get(0);
	}
	
	public String getNameForResultName(String directory) {
		return "./metrics/" + directory  + ".txt";		
	}
}
