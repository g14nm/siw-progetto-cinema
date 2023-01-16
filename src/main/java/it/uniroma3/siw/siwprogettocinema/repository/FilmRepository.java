package it.uniroma3.siw.siwprogettocinema.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.siwprogettocinema.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {
	
	public boolean existsByTitoloAndDurata(String nome, String durata);

}