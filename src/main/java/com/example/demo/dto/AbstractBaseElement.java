package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public abstract class AbstractBaseElement {
	
	private Integer id;
	@NotEmpty(message = "{validation.richiamo.campoInvalido}")
	@Size(min=2, max=100, message = "{validation.richiamo.nomeNotInRange}")
	private String nome;
	private String cognome;
	private String note;
	private Integer giornoNascita;
	private Integer meseNascita;
	private Integer annoNascita;
	
	@Valid
	private List<Richiamo> richiami = new ArrayList<Richiamo>();
}
