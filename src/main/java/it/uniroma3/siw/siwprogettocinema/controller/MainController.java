package it.uniroma3.siw.siwprogettocinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwprogettocinema.authentication.model.Credentials;
import it.uniroma3.siw.siwprogettocinema.authentication.service.CredentialsService;
import it.uniroma3.siw.siwprogettocinema.service.FilmService;

@Controller
public class MainController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping("/")
	public String index(Model model) {
		Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
		if(!authenticated.getPrincipal().equals("anonymousUser")) {
			UserDetails userDetails = (UserDetails)authenticated.getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if(credentials.getRole().equals(Credentials.ADMIN_ROLE))
				return "redirect:/admin/home";
			return "redirect:/user/home";
		}
		model.addAttribute("films", this.filmService.findAll());
    	return "film/films";
	}
	
	@GetMapping("/admin/home")
	public String adminIndex(Model model) {
		return "indexes/adminIndex";
	}
	
	@GetMapping("/user/home")
	public String userIndex(Model model) {
		model.addAttribute("films", this.filmService.findAll());
		return "film/films";
	}
	
}