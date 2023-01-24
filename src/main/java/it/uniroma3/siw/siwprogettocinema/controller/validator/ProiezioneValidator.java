package it.uniroma3.siw.siwprogettocinema.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwprogettocinema.model.Proiezione;
import it.uniroma3.siw.siwprogettocinema.service.ProiezioneService;

@Component
public class ProiezioneValidator implements Validator {
	
	@Autowired
	ProiezioneService proiezioneService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Proiezione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Proiezione proiezione = (Proiezione)target;
		
		if(proiezioneService.existsByDataAndSala(proiezione.getData(), proiezione.getSala()))
			errors.rejectValue("data", "duplicate");
	}

}