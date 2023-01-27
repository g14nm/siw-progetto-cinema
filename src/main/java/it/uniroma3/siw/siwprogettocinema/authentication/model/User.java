package it.uniroma3.siw.siwprogettocinema.authentication.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.uniroma3.siw.siwprogettocinema.model.Prenotazione;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String email;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "users_id")
	private List<Prenotazione> prenotazioni;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}
	
	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	
	public boolean addPrenotazione(Prenotazione prenotazione) {
		return this.prenotazioni.add(prenotazione);
	}
	
}