package br.com.ads.extract.metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.ads.extract.metrics.helpers.CommandLineParser;
import br.com.ads.extract.metrics.helpers.FilesFilterHelper;
import br.com.ads.extract.metrics.helpers.OutputStringBuffer;
import br.com.advanse.jdecorator.service.AnalyzerMetrics;
import gr.spinellis.ckjm.MetricsFilter;


public class Application {
	public static void main(String[] args) {		
		CommandLineParser cmdLineParser = new CommandLineParser(args);
				
		String toPath = cmdLineParser.getRepository();
		for (String  directory : FilesFilterHelper.getAllInRepository(toPath)) {
			final String pathProject = directory; 
			
			Thread thread = new Thread() {
				public void run() {
					try {
						Application.analisysToRepository(toPath + pathProject, cmdLineParser.getNameForResultName(pathProject));
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}				
			};	
			
			thread.start();
		}		
	}

	public static void analisysToRepository(String repository, String fileName) throws IOException, RuntimeException {
		System.out.println("Analisando: " + repository + "...");
		OutputStringBuffer buffer = new OutputStringBuffer();

		String[] classes = FilesFilterHelper.searchInDirectory(repository);
		MetricsFilter.runMetrics(classes, buffer, false);

		String jdecorator = (new AnalyzerMetrics()).findMetrics(repository);
		String ckmjExtend = buffer.getBufferResult();

		Files.write(Paths.get(fileName), (jdecorator + ckmjExtend).getBytes());
		
		System.out.println("Salvo em: " + fileName);
		
	}
}
