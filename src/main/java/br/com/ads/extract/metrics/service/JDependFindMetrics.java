package br.com.ads.extract.metrics.service;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

public class JDependFindMetrics implements Analyzer{
		
	public JDependFindMetrics() {	}

	/*@SuppressWarnings("unchecked")
	@Override
	public String findMetrics(String path) {
		JDepend jDepend = JDependBuilder.builder()
				.addDirectory(path)
				.build();

		jDepend.analyze();
		
		Iterator<JavaPackage> components = jDepend.getPackages().iterator();		
		
		List<Map<Object, Object>> allPackages = new ArrayList<>();
		Map<Object, Object> packages = new HashMap<>();
		Map<Object, Object> mestrics = new HashMap<>();
					
		List<String> header = Arrays.asList("name",
											"abstractness",
											"afferentCoupling",
											"efferentCoupling",
											"distance",
											"instability",
											"afferents", 
											"efferents", 
											"classCount",
											"volatility",
											"abstractClassCount", 
											"concreteClassCount", 
											"containsCycle",
											"afferentsPackages",
											"efferentsPackages");
							
		StringBuilder stringBuilder = new StringBuilder();
				
		
		stringBuilder.append(String.join(",", header) + "\n");
		
		while(components.hasNext()) {
			
			JavaPackage currentPackage = components.next();
			
			List<String> componentsInfos = new ArrayList<String>();
			
			componentsInfos.add(currentPackage.getName());
			componentsInfos.add(String.valueOf(currentPackage.abstractness()));
			componentsInfos.add(String.valueOf(currentPackage.afferentCoupling()));
			componentsInfos.add(String.valueOf(currentPackage.efferentCoupling()));
			componentsInfos.add(String.valueOf(currentPackage.distance()));
			componentsInfos.add(String.valueOf(currentPackage.instability()));
			componentsInfos.add(String.valueOf(currentPackage.getAfferents().size()));			
			componentsInfos.add(String.valueOf(currentPackage.getEfferents().size()));
			componentsInfos.add(String.valueOf(currentPackage.getClassCount()));
			componentsInfos.add(String.valueOf(currentPackage.getVolatility()));
			componentsInfos.add(String.valueOf(currentPackage.getAbstractClassCount()));
			componentsInfos.add(String.valueOf(currentPackage.getConcreteClassCount()));			
			componentsInfos.add(String.valueOf(currentPackage.containsCycle() ? 1 : 0));
			
			componentsInfos.add(this.iteratorToString(currentPackage.getAfferents().iterator()));
			componentsInfos.add(this.iteratorToString(currentPackage.getEfferents().iterator()));			
							
			
			stringBuilder.append(String.join(",", componentsInfos) + "\n");					
		}
		
		return stringBuilder.toString();
	}*/
	
	
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
	
	/*private String iteratorToString(Iterator<JavaPackage> iterator) {
		StringBuilder builder = new StringBuilder();		
		
		while(iterator.hasNext()) {
			JavaPackage next = iterator.next();
			builder.append(next.getName() + " " + 
					next.instability() + " " + 
					next.abstractness() + " " + 
					next.distance() + " " + 
					next.afferentCoupling() + " " + 
					next.efferentCoupling() + "-");
		} 					
				
		return builder.toString();
	}*/
	
	
	/**
	 * @param iterator
	 * @return
	 */
	private List<String> getDependenciesFromIterator(Iterator<JavaPackage> iterator) {
		
		List<String> relacioships = new ArrayList<>();									
		
		while(iterator.hasNext()) {
			JavaPackage next = iterator.next();
			relacioships.add(next.getName());			
		} 					
				
		return relacioships;
	}

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
	

}
