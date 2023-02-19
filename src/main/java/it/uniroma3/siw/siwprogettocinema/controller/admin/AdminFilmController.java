package it.uniroma3.siw.siwprogettocinema.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.siwprogettocinema.FileUploadUtil;
import it.uniroma3.siw.siwprogettocinema.controller.validator.FilmValidator;
import it.uniroma3.siw.siwprogettocinema.model.Film;
import it.uniroma3.siw.siwprogettocinema.service.FilmService;

@Controller
public class AdminFilmController {
	
	final String UPLOAD_DIRECTORY = "locandine/";  
	
	@Autowired
	private FilmValidator filmValidator;
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping("/admin/film")
	public String getFilms(Model model) {
		model.addAttribute("films", this.filmService.findAll());
		return "film/adminFilm";
	}
	
	@GetMapping("/admin/film/{id}")
	public String getFilm(@PathVariable Long id, Model model) {
		model.addAttribute("film", this.filmService.findById(id));
		return "film/film";
	}
	
	@GetMapping("/admin/filmForm")
	public String getFilmForm(Model model) {
		model.addAttribute("film", new Film());
		return "film/filmForm";
	}
	
	@PostMapping("/admin/film")
	public String saveFilm(@RequestParam("image") MultipartFile multipartFile, @Valid @ModelAttribute("film") Film film, BindingResult bindingResult, Model model)
			throws IOException {
		this.filmValidator.validate(film, bindingResult);
		if(!bindingResult.hasErrors()) {
			Film savedFilm = this.filmService.save(film);
			
	        FileUploadUtil.saveFile(UPLOAD_DIRECTORY, savedFilm.getId().toString(), multipartFile);
			return "redirect:/admin/film";
		}
		return "film/filmForm";
	}
	
	@GetMapping("/admin/edit/film/{id}")
	public String getEditFilmForm(@PathVariable Long id, Model model) {
		model.addAttribute("film", this.filmService.findById(id));
		return "film/editFilmForm";
	}
	
	@PostMapping("/admin/edit/film/{id}")
	public String editFilm(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id, @Valid @ModelAttribute("film") Film newFilm, BindingResult bindingResult)
			throws IOException{
		Film film = this.filmService.findById(id);
		
		if(!film.equals(newFilm))
			this.filmValidator.validate(newFilm, bindingResult);
		if(!bindingResult.hasErrors()) {
			film.setTitolo(newFilm.getTitolo());
			film.setDurata(newFilm.getDurata());
			film.setRegista(newFilm.getRegista());
			film.setGenere(newFilm.getGenere());
			film.setTrama(newFilm.getTrama());
			this.filmService.save(film);
			if(!multipartFile.isEmpty()) FileUploadUtil.saveFile(UPLOAD_DIRECTORY, film.getId().toString(), multipartFile);
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
	public String deleteFilm(@PathVariable Long id) throws IOException{
		this.filmService.deleteById(id);
		FileUploadUtil.deleteFile(UPLOAD_DIRECTORY, id.toString());
		return "redirect:/admin/film";
	}

}