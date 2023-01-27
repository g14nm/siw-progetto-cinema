package it.uniroma3.siw.siwprogettocinema.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siwprogettocinema.model.Prenotazione;

@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {

	
	
}
