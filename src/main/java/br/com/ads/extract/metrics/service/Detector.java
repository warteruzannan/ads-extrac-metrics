package br.com.ads.extract.metrics.service;

import java.util.List;
import java.util.Map;

public interface Detector {

	public List<Map<Object, Object>> findMetrics(String path);	
}
