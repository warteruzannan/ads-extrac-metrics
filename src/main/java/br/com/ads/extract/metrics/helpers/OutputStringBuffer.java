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
			//String metrics = classMetrics.toString();						
			//int indexOfSlashN = metrics.indexOf("\n") + 1;
			//indexOfSlashN = indexOfSlashN > metrics.length() ? indexOfSlashN - 1:indexOfSlashN;
			
			//String[] splitedMetrics = metrics.substring(0, indexOfSlashN).replaceAll(",", ".").replaceAll("\n", "").split(" ");
			
			Map<Object,Object> classInfos = new HashMap<>();
			
			// FIXME: APENAS PEGAR A MÉTRICA NA INTERFACE FORNECIDA POR ClassMetrics
			classInfos.put("name", name);		
			classInfos.put("package",name.substring(0,name.lastIndexOf(".")));
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
			
			/*classInfos.put("WMC", Float.parseFloat(splitedMetrics[0]));
			classInfos.put("DIT", Float.parseFloat(splitedMetrics[1]));
			classInfos.put("NOC", Float.parseFloat(splitedMetrics[2]));
			classInfos.put("CBO", Float.parseFloat(splitedMetrics[3]));
			classInfos.put("RFC", Float.parseFloat(splitedMetrics[4]));
			classInfos.put("LCOM", Float.parseFloat(splitedMetrics[5]));
			classInfos.put("Ca", Float.parseFloat(splitedMetrics[6]));
			classInfos.put("Ce", Float.parseFloat(splitedMetrics[7]));
			classInfos.put("NPM", Float.parseFloat(splitedMetrics[8]));
			classInfos.put("LCOM3", Float.parseFloat(splitedMetrics[9]));
			classInfos.put("LOC", Float.parseFloat(splitedMetrics[10]));
			classInfos.put("DAM", Float.parseFloat(splitedMetrics[11]));
			classInfos.put("MOA", Float.parseFloat(splitedMetrics[12]));
			classInfos.put("MFA", Float.parseFloat(splitedMetrics[13]));
			classInfos.put("CAM", Float.parseFloat(splitedMetrics[14]));
			classInfos.put("IC", Float.parseFloat(splitedMetrics[15]));
			classInfos.put("CBM", Float.parseFloat(splitedMetrics[16]));
			classInfos.put("AMC", Float.parseFloat(splitedMetrics[17]));*/
			
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
