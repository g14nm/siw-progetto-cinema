package it.uniroma3.siw.siwprogettocinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.siwprogettocinema.model.Prenotazione;
import it.uniroma3.siw.siwprogettocinema.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	@Transactional
	public Prenotazione save(Prenotazione prenotazione) {
		prenotazione.getProiezione().decreasePostiRimasti(prenotazione.getNumeroPostiPrenotati());
		return this.prenotazioneRepository.save(prenotazione);
	}
	
	public Prenotazione findById(Long id) {
		return this.prenotazioneRepository.findById(id).get();
	}
	
	@Transactional
	public void deleteById(Long id) {
		Prenotazione prenotazione = this.findById(id);
		prenotazione.getProiezione().increasePostiRimasti(prenotazione.getNumeroPostiPrenotati());
		this.prenotazioneRepository.deleteById(id);
		
	}
	
}