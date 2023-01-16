package it.uniroma3.siw.siwprogettocinema.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwprogettocinema.controller.validator.FilmValidator;
import it.uniroma3.siw.siwprogettocinema.model.Film;
import it.uniroma3.siw.siwprogettocinema.service.FilmService;

@Controller
public class AdminFilmController {
	
	@Autowired
	private FilmValidator filmValidator;
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping("/admin/filmForm")
	public String getFilmForm(Model model) {
		model.addAttribute("film", new Film());
		return "film/filmForm";
	}
	
	@GetMapping("/admin/film")
	public String getSale(Model model) {
		model.addAttribute("films", this.filmService.findAll());
		return "film/film";
	}
	
	@PostMapping("/admin/film")
	public String createFilm(@Valid @ModelAttribute("film") Film film, BindingResult bindingResult, Model model) {
		this.filmValidator.validate(film, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.filmService.save(film);
			return "redirect:/admin/film";
		}
		else return "film/filmForm";
	}
	
	@GetMapping("/admin/edit/film/{id}")
	public String getEditFilmForm(@PathVariable Long id, Model model) {
		model.addAttribute("film", this.filmService.findById(id));
		return "film/editFilmForm";
	}
	
	@PostMapping("/admin/edit/film/{id}")
	public String editFilm(@PathVariable Long id, @Valid @ModelAttribute("film") Film newFilm, BindingResult bindingResult) {
		Film film = this.filmService.findById(id);
		
		if(!film.equals(newFilm))
			this.filmValidator.validate(newFilm, bindingResult);
		if(!bindingResult.hasErrors()) {
			film.setTitolo(newFilm.getTitolo());
			film.setDurata(newFilm.getDurata());
			film.setDescrizione(newFilm.getDescrizione());
			this.filmService.save(film);
			return "redirect:/admin/film";
		}
		else {
			return "film/editFilmForm";
		}
	}
	
	@GetMapping("/admin/delete/film/{id}")
	public String getDeleteFilmConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("film", this.filmService.findById(id));
		return "film/deleteFilmConfirm";
	}
	
	@PostMapping("/admin/delete/film/{id}")
	public String deleteFilm(@PathVariable Long id) {
		this.filmService.deleteById(id);
		return "redirect:/admin/film";
	}

}