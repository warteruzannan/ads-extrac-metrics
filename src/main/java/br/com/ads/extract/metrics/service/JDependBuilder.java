package br.com.ads.extract.metrics.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import br.com.ads.extract.metrics.exceptions.InvalidDirectoryPathException;
import jdepend.framework.JDepend;
import jdepend.framework.PackageFilter;

public final class JDependBuilder {
	
	private Collection<String>  filters = new ArrayList<String>();
	private String directory;
	
	protected JDependBuilder() {}
	
	public static JDependBuilder builder(){
		return new JDependBuilder();
	}
	
	public JDependBuilder addFilter(String filter) {
		this.filters.add(filter);
		return this;
	}
	
	public JDependBuilder addDirectory(String directory) {					
		this.directory = directory;		
		
		directoryIsValid();
		
		return this;
	}
	
	
	protected void directoryIsValid() throws InvalidDirectoryPathException{
								
		if(this.directory == null || this.directory.isEmpty() || !Files.exists(Paths.get(this.directory))) {
			throw new InvalidDirectoryPathException();
		}			
	}
	
	
	public JDepend build() {
		directoryIsValid();
		
		if(this.filters.isEmpty()) {
			this.filters.add("java.*");
			this.filters.add("javax.*");			
			this.filters.add("org.junit");
			this.filters.add("org.junit.*");
			this.filters.add("junit.*");
			this.filters.add("com.google.android.*");			
			this.filters.add("com.google.firebase");
			this.filters.add("com.google.firebase.*");
			this.filters.add("android.support.*");			
			this.filters.add("com.google.maps");
			this.filters.add("com.google.maps.*");
			this.filters.add("com.google.ads");
			this.filters.add("com.google.ads.*");
			this.filters.add("com.bumptech.glide.*");
			this.filters.add("com.bluejamesbond.*");
			
			
			
		}
		
		PackageFilter filter 	= new PackageFilter(filters);		
		JDepend jDepend 		= new JDepend(filter);
		
		if(null != this.directory) {
			try {
				jDepend.addDirectory(this.directory);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
			
		return jDepend;								
	}		
}
