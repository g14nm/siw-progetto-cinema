package it.uniroma3.siw.siwprogettocinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwprogettocinema.model.Sala;
import it.uniroma3.siw.siwprogettocinema.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	private SalaRepository salaRepository;
	
	public Sala save(Sala sala) {
		return this.salaRepository.save(sala);
	}
	
	public List<Sala> findAll() {
		return (List<Sala>)this.salaRepository.findAll();
	}
	
	public Sala findById(Long id) {
		return this.salaRepository.findById(id).get();
	}
	
	public boolean existsByNome(String nome) {
		return this.salaRepository.existsByNome(nome);
	}
	
	public void deleteById(Long id) {
		this.salaRepository.deleteById(id);
	}
	
}