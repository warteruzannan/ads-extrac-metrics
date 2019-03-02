package br.com.ads.extract.metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import br.com.ads.extract.metrics.helpers.CommandLineParser;
import br.com.ads.extract.metrics.helpers.FilesFilterHelper;
import br.com.ads.extract.metrics.helpers.OutputStringBuffer;
import br.com.ads.extract.metrics.service.JDependFindMetrics;
import gr.spinellis.ckjm.MetricsFilter;


public class Application {
	public static void main(String[] args) {
		
		CommandLineParser cmdLineParser = new CommandLineParser(args);
				
		String toPath = cmdLineParser.getRepository();
		String[] directories = FilesFilterHelper.getAllInRepository(toPath);
		
		for(int i = 0; i < directories.length; i++) {							
			System.out.println("Analisando " + i + " de " + directories.length + ". Agudarde..");
			try {
				Application.analisysToRepository(toPath + directories[i], cmdLineParser.getNameForResultName(directories[i]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
	}

	public static void analisysToRepository(String repository, String fileName) throws IOException, RuntimeException {
		System.out.println("Analisando: " + repository + "...");
		OutputStringBuffer buffer = new OutputStringBuffer();

		String[] classes = FilesFilterHelper.searchInDirectory(repository);
		MetricsFilter.runMetrics(classes, buffer, false);

		String jdecorator = "";//(new JDependFindMetrics()).findMetrics(repository);
		String ckmjExtend = buffer.getBufferResult();

		Files.write(Paths.get(fileName), (jdecorator + ckmjExtend).getBytes());
		
		System.out.println("Salvo em: " + fileName);
		
	}
}
