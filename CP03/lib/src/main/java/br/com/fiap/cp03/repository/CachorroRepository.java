package br.com.fiap.cp03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.cp03.entity.Cachorro;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro,Integer>{

}