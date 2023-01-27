package it.uniroma3.siw.siwprogettocinema.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwprogettocinema.service.FilmService;

@Controller
public class UserFilmController {

	@Autowired
	private FilmService filmService;
	
	@GetMapping("/user/film")
	public String getFilm(Model model) {
		model.addAttribute("films", this.filmService.findAll());
		return "film/film";
	}
	
}