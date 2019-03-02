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
	
	// FIXME: Teste redundante, remover..
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
		
		
		String[] expectedsResult = {"{name=br.com.dependencies.core, dependents=[br.com.dependencies.services, br.com.dependencies.net], metrics={efferentCoupling=2, concreteClassCount=7, afferentCoupling=2, distance=0.5, instability=0.5, afferents=2, efferents=2, volatility=1, abstractClassCount=0, containsCycle=0, abstractness=0.0}, dependencies=[br.com.dependencies.utils, br.com.dependencies.data]}",
									"{name=br.com.dependencies.utils, dependents=[br.com.dependencies.core], metrics={efferentCoupling=0, concreteClassCount=2, afferentCoupling=1, distance=1.0, instability=0.0, afferents=1, efferents=0, volatility=1, abstractClassCount=0, containsCycle=0, abstractness=0.0}, dependencies=[]}",
									"{name=br.com.dependencies.net, dependents=[br.com.dependencies.services], metrics={efferentCoupling=1, concreteClassCount=2, afferentCoupling=1, distance=0.5, instability=0.5, afferents=1, efferents=1, volatility=1, abstractClassCount=0, containsCycle=0, abstractness=0.0}, dependencies=[br.com.dependencies.core]}",
									"{name=br.com.dependencies.data, dependents=[br.com.dependencies.core], metrics={efferentCoupling=0, concreteClassCount=3, afferentCoupling=1, distance=1.0, instability=0.0, afferents=1, efferents=0, volatility=1, abstractClassCount=0, containsCycle=0, abstractness=0.0}, dependencies=[]}",
									"{name=br.com.dependencies.services, dependents=[br.com.dependencies.ui], metrics={efferentCoupling=2, concreteClassCount=3, afferentCoupling=1, distance=0.3333333, instability=0.6666667, afferents=1, efferents=2, volatility=1, abstractClassCount=0, containsCycle=0, abstractness=0.0}, dependencies=[br.com.dependencies.core, br.com.dependencies.net]}",
									"{name=br.com.dependencies.ui, dependents=[], metrics={efferentCoupling=1, concreteClassCount=1, afferentCoupling=0, distance=0.0, instability=1.0, afferents=0, efferents=1, volatility=1, abstractClassCount=0, containsCycle=0, abstractness=0.0}, dependencies=[br.com.dependencies.services]}"};
		
		for(int i = 0; i < componets.size(); i++) {
			assertEquals(componets.get(i).toString(),expectedsResult[i]);
		}			
	}
}
