package br.com.fiap.cp03.dao;

import java.util.List;

import br.com.fiap.cp03.entity.Cachorro;

public interface CachorroDao extends GenericDao<Cachorro, Integer> {
	List<Cachorro> listar();
}
