package br.com.ads.extract.metrics.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InfosFactory {
	
	public static Map<Object,Object> create(String path){
		Map<Object,Object> infos = new HashMap<>();
		
		infos.put("name", path);		
		infos.put("packages", Collections.emptyList());
		infos.put("classes", Collections.emptyList());
		
		return infos;
	}
	
	public static Map<Object,Object> create(String path,Object packages){
		Map<Object,Object> infos = create(path);				
		infos.put("packages", packages);				
		return infos;
	}
	
	public static Map<Object,Object> create(String path, Object packages, Object classes){
		Map<Object,Object> infos = create(path);				
		infos.put("packages", packages);
		infos.put("classes", classes);
		return infos;
	}
}
