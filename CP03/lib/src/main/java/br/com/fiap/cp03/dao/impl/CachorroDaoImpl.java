package br.com.fiap.cp03.dao.impl;

import java.util.List;

import br.com.fiap.cp03.dao.CachorroDao;
import br.com.fiap.cp03.entity.Cachorro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CachorroDaoImpl  extends GenericDaoImpl<Cachorro, Integer> implements CachorroDao {

	public CachorroDaoImpl(EntityManager em) {
		super(em);
	}

	public List<Cachorro> listar() {
		TypedQuery<Cachorro> query = 
				em.createQuery("from Cachorro", Cachorro.class);
		return query.getResultList();
	}
}
