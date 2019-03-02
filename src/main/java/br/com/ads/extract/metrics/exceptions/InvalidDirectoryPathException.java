package br.com.ads.extract.metrics.exceptions;

@SuppressWarnings("serial")
public class InvalidDirectoryPathException extends RuntimeException {
	public InvalidDirectoryPathException(){
		super("Diretório inválido");
	}
}
