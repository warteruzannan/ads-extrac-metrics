package br.com.ads.extract.metrics.exceptions;

@SuppressWarnings("serial")
public class GeneralException extends RuntimeException{
	public GeneralException(){
		super("Extract metrics fail");
	}
}
