package it.uniroma3.siw.siwprogettocinema.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Proiezione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime data;
	
	@ManyToOne
	private Film film;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Sala sala;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getData() {
		return this.data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Proiezione proiezione = (Proiezione)o;
		return this.data.equals(proiezione.getData()) && this.film.getId() == proiezione.getFilm().getId() && this.sala.getId() == proiezione.getSala().getId();
	}
	
	@Override
	public int hashCode() {
		return this.data.hashCode() + this.film.hashCode() + this.sala.hashCode();
	}
	
}