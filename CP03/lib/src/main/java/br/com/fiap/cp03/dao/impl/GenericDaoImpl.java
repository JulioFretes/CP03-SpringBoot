package br.com.fiap.cp03.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import jakarta.persistence.EntityManager;
import br.com.fiap.cp03.dao.GenericDao;
import br.com.fiap.cp03.exception.CommitException;
import br.com.fiap.cp03.exception.EntidadeNaoEcontradaException;

public abstract class GenericDaoImpl<E,K> implements GenericDao<E, K>{

	protected EntityManager em;
	
	private Class<E> clazz;
	
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl(EntityManager em) {
		this.em = em;
		this.clazz = (Class<E>) ((ParameterizedType) 
				getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	public void salvar(E entidade) {
		em.merge(entidade);
	};

	public void deletar(K id) throws EntidadeNaoEcontradaException {
		E entidade = buscar(id);
		em.remove(entidade);
	}

	public E buscar(K id) throws EntidadeNaoEcontradaException {
		E entidade = em.find(clazz, id);
		if (entidade == null) {
			throw new EntidadeNaoEcontradaException();
		}
		return entidade;
	}

	public void commit() throws CommitException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new CommitException();
		}
	}

	public List<E> listar() {		
		return em.createQuery("from " + clazz.getName(), clazz)
				.getResultList();
	}
}
