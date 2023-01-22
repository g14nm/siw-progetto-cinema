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

import it.uniroma3.siw.siwprogettocinema.controller.validator.ProiezioneValidator;
import it.uniroma3.siw.siwprogettocinema.model.Proiezione;
import it.uniroma3.siw.siwprogettocinema.service.FilmService;
import it.uniroma3.siw.siwprogettocinema.service.ProiezioneService;
import it.uniroma3.siw.siwprogettocinema.service.SalaService;

@Controller
public class AdminProiezioneController {
	
	@Autowired
	private ProiezioneValidator proiezioneValidator;
	
	@Autowired
	private ProiezioneService proiezioneService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping("/admin/proiezioneForm")
	public String getProiezioneForm(Model model) {
		model.addAttribute("proiezione", new Proiezione());
		model.addAttribute("films", this.filmService.findAll());
		model.addAttribute("sale", this.salaService.findAll());
		return "proiezioni/proiezioneForm";
	}
	
	@GetMapping("/admin/proiezioni")
	public String getProiezioni(Model model) {
		model.addAttribute("proiezioni", this.proiezioneService.findAll());
		return "proiezioni/proiezioni";
	}
	
	@PostMapping("/admin/proiezione")
	public String saveProiezione(@Valid @ModelAttribute("proiezione") Proiezione proiezione, BindingResult bindingResult, Model model) {
		this.proiezioneValidator.validate(proiezione, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.proiezioneService.save(proiezione);
			return "redirect:/admin/proiezioni";
		}
		model.addAttribute("films", this.filmService.findAll());
		model.addAttribute("sale", this.salaService.findAll());
		return "proiezioni/proiezioneForm";
	}
	
	@GetMapping("/admin/edit/proiezione/{id}")
	public String getEditProiezioneForm(@PathVariable Long id, Model model) {
		model.addAttribute("proiezione", this.proiezioneService.findById(id));
		model.addAttribute("films", this.filmService.findAll());
		model.addAttribute("sale", this.salaService.findAll());
		return "proiezioni/editProiezioneForm";
	}
	
	@PostMapping("/admin/edit/proiezione/{id}")
	public String editProiezione(@PathVariable Long id, @Valid @ModelAttribute("proiezione") Proiezione newProiezione, BindingResult bindingResult, Model model) {
		Proiezione proiezione = this.proiezioneService.findById(id);
		
		if(!proiezione.equals(newProiezione))
			this.proiezioneValidator.validate(newProiezione, bindingResult);
		if(!bindingResult.hasErrors()) {
			proiezione.setData(newProiezione.getData());
			proiezione.setFilm(newProiezione.getFilm());
			proiezione.setSala(newProiezione.getSala());
			this.proiezioneService.save(proiezione);
			return "redirect:/admin/proiezioni";
		}
		model.addAttribute("films", this.filmService.findAll());
		model.addAttribute("sale", this.salaService.findAll());
		return "proiezioni/editProiezioneForm";
	}
	
	@GetMapping("/admin/delete/proiezione/{id}")
	public String getDeleteProiezioneConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("proiezione", this.proiezioneService.findById(id));
		return "proiezioni/deleteProiezioneConfirm";
	}
	
	@PostMapping("/admin/delete/proiezione/{id}")
	public String deleteProiezione(@PathVariable Long id) {
		this.proiezioneService.deleteById(id);
		return "redirect:/admin/proiezioni";
	}

}