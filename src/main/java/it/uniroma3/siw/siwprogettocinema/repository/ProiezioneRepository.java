package it.uniroma3.siw.siwprogettocinema.repository;

import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siwprogettocinema.model.Film;
import it.uniroma3.siw.siwprogettocinema.model.Proiezione;
import it.uniroma3.siw.siwprogettocinema.model.Sala;

@Repository
public interface ProiezioneRepository extends CrudRepository<Proiezione, Long> {

	boolean existsByDataAndFilmAndSala(LocalDateTime data, Film film, Sala sala);
	
}