package it.uniroma3.siw.siwprogettocinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.siwprogettocinema.service.FilmService;

@Controller
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@GetMapping("/film")
	public String getFilms(Model model) {
		model.addAttribute("films", this.filmService.findAll());
		return "film/films";
	}
	
	@GetMapping("/film/{id}")
	public String getFilms(@PathVariable Long id, Model model) {
		model.addAttribute("film", this.filmService.findById(id));
		return "film/film";
	}
	
}