package it.uniroma3.siw.siwprogettocinema.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwprogettocinema.model.Prenotazione;

@Component
public class PrenotazioneValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Prenotazione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Prenotazione prenotazione = (Prenotazione)target;
		
		if(prenotazione.getNumeroPostiPrenotati() > prenotazione.getProiezione().getPostiRimasti())
			errors.rejectValue("numeroPostiPrenotati", "maxSeatsReached");
	}

}
