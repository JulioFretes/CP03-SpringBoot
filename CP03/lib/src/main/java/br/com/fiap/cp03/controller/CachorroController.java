package br.com.fiap.cp03.controller;


import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cp03.dao.CachorroDao;
import br.com.fiap.cp03.dao.impl.CachorroDaoImpl;
import br.com.fiap.cp03.entity.Cachorro;
import br.com.fiap.cp03.exception.CommitException;
import br.com.fiap.cp03.sigleton.EntityManagerFactorySingleton;
import br.com.fiap.cp03.exception.EntidadeNaoEcontradaException;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CachorroController {

	private EntityManager em = EntityManagerFactorySingleton
			.getInstance().createEntityManager();
	
	CachorroDao dao = new CachorroDaoImpl(em);
	
	private CachorroDao cachorroDao = new CachorroDaoImpl(em);	

	@GetMapping("/cachorro")
	ResponseEntity<List<Cachorro>> all() {
		List<Cachorro> cachorroList = cachorroDao.listar();
		return ResponseEntity.ok(cachorroList);
	}

	@PostMapping("/cachorro")
	public ResponseEntity<Cachorro> newCachorro(@RequestBody Cachorro newCachorro) {
		try {
			dao.salvar(newCachorro);
			dao.commit();
		} catch (CommitException e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok(newCachorro);
	}

	@GetMapping("/cachorro/{id}")
	public ResponseEntity<Cachorro> one(@PathVariable Integer id) {
		
		Cachorro cachorro = null;
		try {
			cachorro = dao.buscar(id);
		} catch (EntidadeNaoEcontradaException e) {
			
			e.printStackTrace();
		}
		if (cachorro == null) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.ok(cachorro);
	}

	@PutMapping("/cachorro/{id}")
	public ResponseEntity<Cachorro> replaceCachorro(@RequestBody Cachorro newCachorro, @PathVariable Integer id) {
		
		try {
			dao.salvar(newCachorro);
			dao.commit();
		} catch (CommitException e) {
			System.err.println(e.getMessage());
		}
		return ResponseEntity.ok(newCachorro);
	}

	@DeleteMapping("/cachorro/{id}")
	public ResponseEntity<Cachorro> deleteCachorro(@PathVariable Integer id) {
		try {
			dao.deletar(id);
			dao.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
}
