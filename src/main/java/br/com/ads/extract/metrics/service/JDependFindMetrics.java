package br.com.ads.extract.metrics.service;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

public class JDependFindMetrics implements Detector{			
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<Object, Object>> findMetrics(String path) {
		JDepend jDepend = JDependBuilder.builder()
				.addDirectory(path)
				.build();

		jDepend.analyze();
		
		Iterator<JavaPackage> components = jDepend.getPackages().iterator();		
		
		List<Map<Object,Object>> packages = new ArrayList<>();
		
		while(components.hasNext()) {		
			JavaPackage currentPackage = components.next();
			Map<Object, Object> infos = new HashMap<>();
			
			infos.put("name", currentPackage.getName());
			infos.put("metrics", this.getMetrics(currentPackage));
			infos.put("dependencies", this.getDependenciesFromIterator(currentPackage.getEfferents().iterator()));
			infos.put("dependents", this.getDependenciesFromIterator(currentPackage.getAfferents().iterator()));
		
			packages.add(infos);
		}

		return packages;
	}
		
	private Map<Object, Object> getMetrics(JavaPackage component){
		Map<Object, Object> metrics = new HashMap<>();
		
		metrics.put("abstractness", component.abstractness());
		metrics.put("afferentCoupling", component.afferentCoupling());
		metrics.put("efferentCoupling", component.efferentCoupling());
		metrics.put("distance", component.distance());
		metrics.put("instability", component.instability());
		metrics.put("afferents", component.getAfferents().size());
		metrics.put("efferents", component.getEfferents().size());
		metrics.put("volatility", component.getVolatility());
		metrics.put("abstractClassCount", component.getAbstractClassCount());
		metrics.put("concreteClassCount", component.getConcreteClassCount());
		metrics.put("containsCycle", component.containsCycle() ? 1 : 0);		
		
		return metrics;
	}
		
		
	private List<String> getDependenciesFromIterator(Iterator<JavaPackage> iterator) {
		
		List<String> relacioships = new ArrayList<>();									
		
		while(iterator.hasNext()) 			
			relacioships.add(iterator.next().getName());			
		
				
		return relacioships;
	}

	
	

}
