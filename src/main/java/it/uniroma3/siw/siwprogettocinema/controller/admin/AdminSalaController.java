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

import it.uniroma3.siw.siwprogettocinema.controller.validator.SalaValidator;
import it.uniroma3.siw.siwprogettocinema.model.Sala;
import it.uniroma3.siw.siwprogettocinema.service.SalaService;

@Controller
public class AdminSalaController {
	
	@Autowired
	private SalaValidator salaValidator;
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping("/admin/salaForm")
	public String getSalaForm(Model model) {
		model.addAttribute("sala", new Sala());
		return "sale/salaForm";
	}
	
	@GetMapping("/admin/sale")
	public String getSale(Model model) {
		model.addAttribute("sale", this.salaService.findAll());
		return "sale/sale";
	}
	
	@PostMapping("/admin/sala")
	public String saveSala(@Valid @ModelAttribute("sala") Sala sala, BindingResult bindingResult, Model model) {
		this.salaValidator.validate(sala, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.salaService.save(sala);
			return "redirect:/admin/sale";
		}
		return "sale/salaForm";
	}
	
	@GetMapping("/admin/edit/sala/{id}")
	public String getEditSalaForm(@PathVariable Long id, Model model) {
		model.addAttribute("sala", this.salaService.findById(id));
		return "sale/editSalaForm";
	}
	
	@PostMapping("/admin/edit/sala/{id}")
	public String editSala(@PathVariable Long id, @Valid @ModelAttribute("sala") Sala newSala, BindingResult bindingResult) {
		Sala sala = this.salaService.findById(id);
		
		if(!sala.equals(newSala))
			this.salaValidator.validate(newSala, bindingResult);
		if(!bindingResult.hasErrors()) {
			sala.setNome(newSala.getNome());
			sala.setPosti(newSala.getPosti());
			this.salaService.save(sala);
			return "redirect:/admin/sale";
		}
		else {
			return "sale/editSalaForm";
		}
	}
	
	@GetMapping("/admin/delete/sala/{id}")
	public String getDeleteSalaConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("sala", this.salaService.findById(id));
		return "sale/deleteSalaConfirm";
	}
	
	@PostMapping("/admin/delete/sala/{id}")
	public String deleteSala(@PathVariable Long id) {
		this.salaService.deleteById(id);
		return "redirect:/admin/sale";
	}
	
}