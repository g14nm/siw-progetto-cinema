package it.uniroma3.siw.siwprogettocinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Min(0)
	private Integer numeroPostiPrenotati;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
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