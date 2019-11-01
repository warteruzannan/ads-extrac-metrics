package br.com.ads.extract.metrics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import br.com.ads.extract.metrics.helpers.FilesFilterHelper;
import br.com.ads.extract.metrics.helpers.OutputStringBuffer;
import br.com.ads.extract.metrics.service.InfosFactory;
import br.com.ads.extract.metrics.service.JDependFindMetrics;
import gr.spinellis.ckjm.MetricsFilter;

public class ADSFindMetrics {
	private Gson gson = new Gson();
	
	public String find(String directory) throws IOException {
		
		OutputStringBuffer buffer = new OutputStringBuffer();
		JDependFindMetrics jDependFindMetrics = new JDependFindMetrics();
		
		String[] classes = FilesFilterHelper.searchInDirectory(directory);
		

		
		MetricsFilter.runMetrics(classes, buffer, false);

		List<Map<Object,Object>> packages  = jDependFindMetrics.findMetrics(directory);
		
		Map<Object,Object> infos = InfosFactory.create(directory);
		List<Map<Object,Object>> infos_classes = buffer.getBufferResult();
		
		// FIXME:Corrigir isso para que a classe já seja setada diretamente pelo acesso ao 'nome' do pacote
		for(Map<Object,Object> pck:packages) {
			List<Map<Object,Object>> pck_classes = infos_classes.stream()
													.filter(p -> p.get("package").equals(pck.get("name")))
													.collect(Collectors.toList());
			pck.put("classes", pck_classes);					
		}
		
		infos.put("classes", infos_classes);
		infos.put("packages", packages);
				
		
		return this.gson.toJson(infos);
	}
}
