package br.com.fiap.cp03.sigleton;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class EntityManagerFactorySingleton {

	@PersistenceContext(unitName = "CLIENTE_ORACLE")
	private static EntityManagerFactory emFactory;
	
	private EntityManagerFactorySingleton() {}
	
	public static EntityManagerFactory getInstance() {
		if (emFactory == null) {
			emFactory = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		}
		return emFactory;	
	}
	
}