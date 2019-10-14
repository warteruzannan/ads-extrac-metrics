package br.com.ads.extract.metrics.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.com.ads.extract.metrics.exceptions.InvalidDirectoryPathException;
import br.com.ads.extract.metrics.helpers.FilesFilterHelper;
import br.com.ads.extract.metrics.helpers.OutputStringBuffer;
import gr.spinellis.ckjm.MetricsFilter;

public class CKJMFindMetrics implements Detector{

	@Override
	public List<Map<Object, Object>> findMetrics(String path) throws InvalidDirectoryPathException {		
		try {
			OutputStringBuffer buffer = new OutputStringBuffer();
			String[] classes = FilesFilterHelper.searchInDirectory(path);
			MetricsFilter.runMetrics(classes, buffer, false);
			return buffer.getBufferResult();					
		} catch (IOException e) {			
			InvalidDirectoryPathException ex = new InvalidDirectoryPathException();
			ex.addSuppressed(e);
			throw ex;
		}		
	}

}
