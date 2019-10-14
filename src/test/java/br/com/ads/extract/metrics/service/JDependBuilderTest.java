package br.com.ads.extract.metrics.service;

import org.junit.Test;

import br.com.ads.extract.metrics.exceptions.InvalidDirectoryPathException;
import br.com.ads.extract.metrics.service.JDependBuilder;
import jdepend.framework.JDepend;

public class JDependBuilderTest {

	@Test(expected=InvalidDirectoryPathException.class)
	public void nonExistFolderToAnalisysTest() {
		
		JDepend jDepend = JDependBuilder.builder()
						.addDirectory("nonExists")
						.build();
		jDepend.analyze();
	}
	
	@Test
	public void existFolderToAnalisysTest() {
		
		JDepend jDepend = JDependBuilder.builder()
						.addDirectory("./src/test/resources/dependencies")
						.build();		
		jDepend.analyze();
	}
	
	
}
