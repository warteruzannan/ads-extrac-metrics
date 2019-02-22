package br.com.ads.extract.metrics.helpers;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FilesFilterHelper {

	/**
	 * Busca todos os arquivos .class em um diretório 
	 * A busca é feita recursivamente 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String[] searchInDirectory(String path) throws IOException{
		List<String> files = Files.walk(Paths.get(path))
								.map(f -> { return f.toAbsolutePath().toString(); })
								.filter(f -> f.endsWith(".class"))
								.collect(Collectors.toList());
		
		return files.toArray(new String[files.size()]);				
	}
	
	/**
	 * Busca todos os diretórios
	 * @param path
	 * @return
	 */
	public static String[] getAllInRepository(String path) {
		return new File(path).list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
			
		});
	}
}
