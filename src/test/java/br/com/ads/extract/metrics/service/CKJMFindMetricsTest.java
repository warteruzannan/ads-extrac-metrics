package br.com.ads.extract.metrics.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CKJMFindMetricsTest {

	private CKJMFindMetrics cKJMFindMetrics;
	
	@Before
	public void createCKJMFindMetrics() {
		this.cKJMFindMetrics = new CKJMFindMetrics();
	}
	
	@Test
	public void asserSizeOfClassesFromProjectDependencies() {
		int expectedResult = 18;
		
		List<Map<Object, Object>> result = this.cKJMFindMetrics.findMetrics("./src/test/resources/dependencies");		
		
		assertEquals(expectedResult,result.size());
	}
	
	@Test(expected=RuntimeException.class)
	public void asserPathNonExistsFromProjectDependencies() {
		this.cKJMFindMetrics.findMetrics("non");					
	}
		
}
