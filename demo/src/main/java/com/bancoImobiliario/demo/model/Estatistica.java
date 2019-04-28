package com.bancoImobiliario.demo.model;

import lombok.Getter;
import lombok.Setter;

public class Estatistica {

	private @Getter @Setter Integer timeout;
	private @Getter @Setter Double mediaTurnos;
	private @Getter @Setter Double percAleatorio;
	private @Getter @Setter Double percCauteloso;
	private @Getter @Setter Double percExigente;
	private @Getter @Setter Double percImpulsivo;
	private @Getter @Setter String vencedor;
	private @Getter @Setter String logPartidas;

	public Estatistica() {
		this.timeout = 0;
		this.mediaTurnos = 0.0;
		this.percAleatorio = 0.0;
		this.percCauteloso = 0.0;
		this.percExigente = 0.0;
		this.percImpulsivo = 0.0;
		this.vencedor = null;
		this.logPartidas = "";
	}

}
