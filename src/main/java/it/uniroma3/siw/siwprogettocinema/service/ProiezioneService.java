package it.uniroma3.siw.siwprogettocinema.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwprogettocinema.model.Film;
import it.uniroma3.siw.siwprogettocinema.model.Proiezione;
import it.uniroma3.siw.siwprogettocinema.model.Sala;
import it.uniroma3.siw.siwprogettocinema.repository.ProiezioneRepository;

@Service
public class ProiezioneService {
	
	@Autowired
	private ProiezioneRepository proiezioneRepostitory;
	
	public Proiezione save(Proiezione proiezione) {
		return this.proiezioneRepostitory.save(proiezione);
	}
	
	public List<Proiezione> findAll() {
		return (List<Proiezione>) this.proiezioneRepostitory.findAll();
	}
	
	public Proiezione findById(Long id) {
		return this.proiezioneRepostitory.findById(id).get();
	}
	
	public boolean existsByDataAndFilmAndSala(LocalDateTime data, Film film, Sala sala) {
		return this.proiezioneRepostitory.existsByDataAndFilmAndSala(data, film, sala);
	}
	
	public void deleteById(Long id) {
		this.proiezioneRepostitory.deleteById(id);
	}

}