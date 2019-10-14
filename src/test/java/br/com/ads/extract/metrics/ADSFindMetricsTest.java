package br.com.ads.extract.metrics;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ADSFindMetricsTest {
	private ADSFindMetrics aDSFindMetrics; 
	
	@Before
	public void createADSFindMetrics() {
		this.aDSFindMetrics = new ADSFindMetrics();
	}
	
	@Test
	public void getStringFromProjectDependencies() {
		try {				
			String[] expectedComponentsAndClasses = {"br.com.dependencies.core",
					"br.com.dependencies.utils",
					"br.com.dependencies.net",
					"br.com.dependencies.data",
					"br.com.dependencies.services",
					"services.DataService",
					"services.NetService",
					"services.UserService",
					"net.HttpRequest",
					"net.TCPRequest",
					"core.Define",
					"core.Search",
					"core.Manager",
					"core.Filter",
					"core.Validate",
					"core.Convert",
					"core.Extract",
					"data.Repository",
					"data.SQLData",
					"data.MongoAccess",
					"utils.PaserXAML",
					"utils.ParserCSV",
					"ui.UserUI",
					"br.com.dependencies.ui"};
			
			
			String result = this.aDSFindMetrics.find("./src/test/resources/dependencies");
			
			for(int i = 0; i < expectedComponentsAndClasses.length; i++)
				assertTrue(result.contains(expectedComponentsAndClasses[i]));
			
		} catch (IOException e) {			
			e.printStackTrace();
			fail();			
		}
	}
}
