package br.com.ads.extract.metrics.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.spinellis.ckjm.CkjmOutputHandler;
import gr.spinellis.ckjm.ClassMetrics;

public class OutputStringBuffer implements CkjmOutputHandler{

	List<Map<Object,Object>> classes; 
	
    public OutputStringBuffer(){
    	this.classes = new ArrayList<>();
    }
	
	@Override
	public void handleClass(String name, ClassMetrics classMetrics) {		
		
		if(classMetrics != null) {						
			Map<Object,Object> classInfos = new HashMap<>();
			 			
			classInfos.put("name", name);
			
			////////////////////////////////
			if(name.contains("."))
				classInfos.put("package",name.substring(0,name.lastIndexOf(".")));
			else
				classInfos.put("package","Default");
			//////////////////////////////				
				
			classInfos.put("WMC", classMetrics.getWmc());
			classInfos.put("DIT", classMetrics.getDit());
			classInfos.put("NOC", classMetrics.getNoc());
			classInfos.put("CBO", classMetrics.getCbo());
			classInfos.put("RFC", classMetrics.getRfc());
			classInfos.put("LCOM", classMetrics.getLcom());
			classInfos.put("Ca", classMetrics.getCa());
			classInfos.put("Ce", classMetrics.getCe());
			classInfos.put("NPM", classMetrics.getNpm());
			classInfos.put("LCOM3", classMetrics.getLcom3());
			classInfos.put("LOC", classMetrics.getLoc());
			classInfos.put("DAM", classMetrics.getDam());
			classInfos.put("MOA", classMetrics.getMoa());
			classInfos.put("MFA", classMetrics.getMfa());
			classInfos.put("CAM", classMetrics.getCam());
			classInfos.put("IC", classMetrics.getIc());
			classInfos.put("CBM", classMetrics.getCbm());
			classInfos.put("AMC", classMetrics.getAmc());					
			
			////////////////////////////////////////////////////////////////////////
			int cc = 0;			
			for(String method : classMetrics.getMethodNames()) 
				cc += classMetrics.getCC(method);			
			
			classInfos.put("CC", cc);
			//////////////////////////////////////////////////////////////////////
								
			this.classes.add(classInfos);												
		}			
	}
	
	public List<Map<Object,Object>> getBufferResult() {
		return this.classes;
	}

}
