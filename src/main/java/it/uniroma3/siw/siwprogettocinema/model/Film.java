package it.uniroma3.siw.siwprogettocinema.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String titolo;
	
	@NotBlank
	private String durata;
	
	private String descrizione;
	
	@OneToMany(mappedBy = "film")
	private List<Proiezione> proiezioni;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDurata() {
		return this.durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Proiezione> getProiezioni() {
		return this.proiezioni;
	}

	public void setProiezioni(List<Proiezione> proiezioni) {
		this.proiezioni = proiezioni;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Film film = (Film)o;
		return this.titolo == film.getTitolo() && this.durata == film.getDurata();
	}
	
	@Override
	public int hashCode() {
		return this.titolo.hashCode() + this.durata.hashCode();
	}
}