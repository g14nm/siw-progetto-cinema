package it.uniroma3.siw.siwprogettocinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	@NotNull
	@Min(0)
	private Integer posti;

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

	public Integer getPosti() {
		return this.posti;
	}

	public void setPosti(Integer posti) {
		this.posti = posti;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Sala sala = (Sala)o;
		return this.nome.trim() == sala.getNome().trim();
	}
	
	@Override
	public int hashCode() {
		return this.nome.trim().hashCode();
	}
}