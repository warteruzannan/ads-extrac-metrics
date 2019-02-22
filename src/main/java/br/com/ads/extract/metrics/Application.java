package br.com.ads.extract.metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.ads.extract.metrics.helpers.CommandLineParser;
import br.com.ads.extract.metrics.helpers.FilesFilterHelper;
import br.com.ads.extract.metrics.helpers.OutputStringBuffer;
import br.com.advanse.jdecorator.service.AnalyzerMetrics;
import gr.spinellis.ckjm.MetricsFilter;

/**
 * Hello world!
 *
 */
public class Application {
	public static void main(String[] args) {		
		CommandLineParser cmdLineParser = new CommandLineParser(args);

		
		if (cmdLineParser.analisysAllInRepository()) {
			String toPath = cmdLineParser.getRepository();
			for (String directory : FilesFilterHelper.getAllInRepository(toPath)) {				
				try {
					Application.analisysToRepository(toPath + directory, cmdLineParser.getNameForResultName(directory));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				Application.analisysToRepository(cmdLineParser.getRepository(), cmdLineParser.getNameForResultName(cmdLineParser.getRepository()));
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

		String jdecorator = (new AnalyzerMetrics()).findMetrics(repository);
		String ckmjExtend = buffer.getBufferResult();

		Files.write(Paths.get(fileName), (jdecorator + ckmjExtend).getBytes());
		
		System.out.println("Salvo em: " + fileName);
		
	}
}
