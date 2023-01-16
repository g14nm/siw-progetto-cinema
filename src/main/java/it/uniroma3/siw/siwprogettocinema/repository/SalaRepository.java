package it.uniroma3.siw.siwprogettocinema.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siwprogettocinema.model.Sala;

@Repository
public interface SalaRepository extends CrudRepository<Sala, Long> {

	public boolean existsByNome(String nome);
	
}