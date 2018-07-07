package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;

@Entity(name = "UTENTE")
@Table(name = "UTENTE")
public class User {
	
	@NotNull
	@NotEmpty(message = "{validation.user.codiceFiscale}")
	@Id
	@Column(name = "CODICE_FISCALE")
	private String codiceFiscale;
	
	@NotNull
	@Column(name = "ID_TESSERA")
	private Long idTessera;
	
	@NotNull
	@NotEmpty
	@Column(name = "NOME")
	private String nome;
	
	@NotNull
	@NotEmpty
	@Column(name = "COGNOME")
	private String cognome;
	
	@NotNull
	@NotEmpty
	@Email
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "INDIRIZZO")
	private String indirizzo;
	
	@Column(name = "CITTA")
	private String citta;
	
	@NotNull
	@NotEmpty(message = "{validation.user.numeroTelefono}")
	@Pattern(regexp="(^$|[0-9]{10})", message = "{validation.user.numeroTelefono}")
	@Column(name = "TELEFONO")
	private String numeroTelefono;
	
	@Transient
	private Date dataNascita;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Long getIdTessera() {
		return idTessera;
	}
	public void setIdTessera(Long idTessera) {
		this.idTessera = idTessera;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
}
