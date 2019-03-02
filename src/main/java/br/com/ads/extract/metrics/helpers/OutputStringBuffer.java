package br.com.ads.extract.metrics.helpers;

import gr.spinellis.ckjm.CkjmOutputHandler;
import gr.spinellis.ckjm.ClassMetrics;

public class OutputStringBuffer implements CkjmOutputHandler{

	StringBuffer stringBuffer = new StringBuffer(); 
    String header = "class_name,WMC, DIT, NOC, CBO, RFC, LCOM, Ca, Ce, NPM, LCOM3, LOC, DAM, MOA, MFA, CAM, IC, CBM, AMC\n";
    public OutputStringBuffer(){
    	stringBuffer.append(this.header);
    }
	
	@Override
	public void handleClass(String name, ClassMetrics classMetrics) {
		
		
		if(classMetrics != null) {
			String metrics = classMetrics.toString();											
			this.stringBuffer.append(name + "," + metrics.substring(0, metrics.indexOf("\n") + 1).replaceAll(",", ".").replaceAll(" ", ","));				
		}			
	}
	
	public String getBufferResult() {
		return this.stringBuffer.toString();
	}

}
