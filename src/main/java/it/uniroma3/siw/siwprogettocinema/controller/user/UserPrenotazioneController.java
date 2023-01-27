package it.uniroma3.siw.siwprogettocinema.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwprogettocinema.authentication.model.User;
import it.uniroma3.siw.siwprogettocinema.authentication.service.CredentialsService;
import it.uniroma3.siw.siwprogettocinema.authentication.service.UserService;
import it.uniroma3.siw.siwprogettocinema.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.siwprogettocinema.model.Prenotazione;
import it.uniroma3.siw.siwprogettocinema.service.PrenotazioneService;
import it.uniroma3.siw.siwprogettocinema.service.ProiezioneService;

@Controller
public class UserPrenotazioneController {

	@Autowired
	private PrenotazioneValidator prenotazioneValidator;
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private ProiezioneService proiezioneService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/prenotazioni")
	public String getPrenotazioni(Model model) {
		model.addAttribute("prenotazioni", this.getUser().getPrenotazioni());
		return "prenotazioni/prenotazioni";
	}
	
	@GetMapping("/user/prenotazione/{id}")
	public String getPrenotazioneForm(@PathVariable Long id, Model model) {
		model.addAttribute("proiezione", this.proiezioneService.findById(id));
		model.addAttribute("prenotazione", new Prenotazione());
		return "prenotazioni/prenotazioneForm";
	}
	
	@PostMapping("/user/prenotazione/{id}")
	public String savePrenotazione(@PathVariable Long id, @Valid @ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult bindingResult, Model model) {
		prenotazione.setProiezione(this.proiezioneService.findById(id));
		this.prenotazioneValidator.validate(prenotazione, bindingResult);
		if(!bindingResult.hasErrors()) {
				User currentUser = this.getUser();
				currentUser.addPrenotazione(prenotazioneService.save(prenotazione));
				this.userService.saveUser(currentUser);
			return "redirect:/user/film";
		}
		model.addAttribute("proiezione", this.proiezioneService.findById(id));
		return "prenotazioni/prenotazioneForm";
	}
	
	@GetMapping("/user/prenotazione/delete/{id}")
	public String deletePrenotazione(@PathVariable Long id) {
		this.prenotazioneService.deleteById(id);
		return "redirect:/user/prenotazioni";
	}
	
	private User getUser() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return credentialsService.getCredentials(username).getUser();
	}
	
}