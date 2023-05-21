package br.com.fiap.cp03.exception;

@SuppressWarnings("serial")
public class EntidadeNaoEcontradaException extends Exception {
	
	public EntidadeNaoEcontradaException() {
		super("Entidade não encontrada");
	}
	
	public EntidadeNaoEcontradaException(String msg) {
		super(msg);
	}

}
