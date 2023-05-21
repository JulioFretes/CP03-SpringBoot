package br.com.fiap.cp03.dao;

import java.util.List;

import br.com.fiap.cp03.exception.CommitException;
import br.com.fiap.cp03.exception.EntidadeNaoEcontradaException;

public interface GenericDao<E,K> {

	void salvar(E entidade);
	void deletar(K id) throws EntidadeNaoEcontradaException;
	E buscar(K id) throws EntidadeNaoEcontradaException;
	void commit() throws CommitException;
	
	List<E> listar();
	
}