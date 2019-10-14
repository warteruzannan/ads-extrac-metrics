package br.com.ads.extract.metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import br.com.ads.extract.metrics.helpers.CommandLineParser;
import br.com.ads.extract.metrics.helpers.FilesFilterHelper;

public class Application {
		
	public static void main(String[] args) {		
		CommandLineParser cmdLineParser = new CommandLineParser(args);
		
		
				
		String toPath = cmdLineParser.getRepository();
		
		
		/**
		 * Caso o usuário passe -p
		 */
		if(cmdLineParser.useDefaultOutput()) {
			try {
				Application.analisysToRepository(toPath, null, true);				
			} catch (Exception e) {
				e.printStackTrace();
			}			
			return;
		}
		
		
		String[] directories = FilesFilterHelper.getAllInRepository(toPath);
		
		for(int i = 0; i < directories.length; i++) {							
			System.out.println("Analisando " + i + " de " + directories.length + ". Agudarde..");
			try {
				Application.analisysToRepository(toPath + directories[i], cmdLineParser.getNameForResultName(directories[i]), false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}				
	}
	
	
	public static void analisysToRepository(String repository, String fileName, boolean print) throws IOException, RuntimeException {
		System.out.println("Analisando: " + repository + "...");
		ADSFindMetrics adsFindMetrics = new ADSFindMetrics();
		
		if(print)  {
			System.out.println(adsFindMetrics.find(repository));
			return;
		}
		
		Files.write(Paths.get(fileName),adsFindMetrics.find(repository).getBytes());		
		System.out.println("Salvo em: " + fileName);
		
	}
}
