package it.uniroma3.siw.siwprogettocinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwprogettocinema.model.Film;
import it.uniroma3.siw.siwprogettocinema.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	public void save(Film film) {
		this.filmRepository.save(film);
	}
	
	public List<Film> findAll() {
		return (List<Film>)this.filmRepository.findAll();
	}
	
	public Film findById(Long id) {
		return this.filmRepository.findById(id).get();
	}
	
	public boolean existsByTitoloAndDurata(String titolo, String durata) {
		return this.filmRepository.existsByTitoloAndDurata(titolo, durata);
	}
	
	public void deleteById(Long id) {
		this.filmRepository.deleteById(id);
	}

}