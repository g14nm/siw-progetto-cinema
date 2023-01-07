package it.uniroma3.siw.siwprogettocinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Proiezione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String giorno;
	
	@NotBlank
	private String ora;
	
	@ManyToOne
	private Film film;
	
	@OneToOne
	private Sala sala;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGiorno() {
		return this.giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public String getOra() {
		return this.ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
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

	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Proiezione proiezione = (Proiezione)o;
		return this.giorno == proiezione.getGiorno() && this.ora == proiezione.getOra() && this.film == proiezione.getFilm();
	}
	
	@Override
	public int hashCode() {
		return this.giorno.hashCode() + this.ora.hashCode() + this.film.hashCode();
	}
}