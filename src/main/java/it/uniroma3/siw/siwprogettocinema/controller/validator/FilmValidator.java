package it.uniroma3.siw.siwprogettocinema.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwprogettocinema.model.Film;
import it.uniroma3.siw.siwprogettocinema.service.FilmService;

@Component
public class FilmValidator implements Validator {
	
	@Autowired
	private FilmService filmService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Film.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Film film = (Film)target;
		
		String titolo = film.getTitolo().trim();
		String durata = film.getDurata().trim();

		if(this.filmService.existsByTitoloAndDurata(titolo, durata)) errors.rejectValue("titolo", "duplicate");
	}

}