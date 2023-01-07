package it.uniroma3.siw.siwprogettocinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private Integer numeroPostiPrenotati;
	
	@OneToOne
	private Proiezione proiezione;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroPostiPrenotati() {
		return this.numeroPostiPrenotati;
	}

	public void setNumeroPostiPrenotati(Integer numeroPostiPrenotati) {
		this.numeroPostiPrenotati = numeroPostiPrenotati;
	}

	public Proiezione getProiezione() {
		return this.proiezione;
	}

	public void setProiezione(Proiezione proiezione) {
		this.proiezione = proiezione;
	}
}