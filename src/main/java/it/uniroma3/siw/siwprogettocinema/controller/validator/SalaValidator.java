package it.uniroma3.siw.siwprogettocinema.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwprogettocinema.model.Sala;
import it.uniroma3.siw.siwprogettocinema.service.SalaService;

@Component
public class SalaValidator implements Validator {
	
	@Autowired
	private SalaService salaService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Sala.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sala sala = (Sala)target;
		
		String nome = sala.getNome().trim();

		if(this.salaService.existsByNome(nome)) errors.rejectValue("nome", "duplicate");
	}

}