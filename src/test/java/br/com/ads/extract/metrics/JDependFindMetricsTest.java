package br.com.ads.extract.metrics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import br.com.ads.extract.metrics.service.JDependFindMetrics;


public class JDependFindMetricsTest {
	private JDependFindMetrics jDependFindMetrics;
	
	@Before
	public void createJDependFindMetrics() {
		this.jDependFindMetrics = new JDependFindMetrics();
	}
	
	
	@Test
	public void assertSizeComponetsFromProjectDependenciesTest() {						
		List<Map<Object, Object>> result  = this.jDependFindMetrics.findMetrics("./src/test/resources/dependencies");		
		assertEquals(result.size(), 6);		
	}
	
	
	@Test
	public void assertFoundComponetsFromProjectDependenciesTest() {						
		List<Map<Object, Object>> result  = this.jDependFindMetrics.findMetrics("./src/test/resources/dependencies");		
		String[] names = {"br.com.dependencies.core",
							"br.com.dependencies.utils",
							"br.com.dependencies.net",
							"br.com.dependencies.data",
							"br.com.dependencies.services",
							"br.com.dependencies.ui"};
		
		for(int i = 0; i < result.size(); i++) {
			assertEquals(result.get(i).get("name"),names[i]);
		}
	}
	
	@Test
	public void assertMetricsFromProjectDEpendenciesTest() {
		List<Map<Object, Object>> componets  = this.jDependFindMetrics.findMetrics("./src/test/resources/dependencies");
		
		String expectedResult = "{name=br.com.dependencies.core, dependents=[br.com.dependencies.services, br.com.dependencies.net], metrics={efferentCoupling=2, concreteClassCount=7, afferentCoupling=2, distance=0.5, instability=0.5, afferents=2, efferents=2, volatility=1, abstractClassCount=0, containsCycle=0, abstractness=0.0}, dependencies=[br.com.dependencies.utils, br.com.dependencies.data]}";		
		String result = componets.get(0).toString();
		
		assertEquals(expectedResult,result);
	}
}
