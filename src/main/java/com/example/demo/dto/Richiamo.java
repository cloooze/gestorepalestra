package com.example.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Richiamo {
	
	private LocalDate dataProtocollo;
	
	@NotNull(message = "{validation.richiamo.campoInvalido}")
	private Integer protocollo;
	
	@NotEmpty(message = "{validation.richiamo.campoInvalido}")
	private String voceFascicolo;
	
	@NotEmpty(message = "{validation.richiamo.campoInvalido}")
	private String classifica;

}
