package com.bancoImobiliario.demo.model.jogador;

import lombok.Getter;
import lombok.Setter;

public abstract class Jogador implements Comportamento {
	
	private @Getter @Setter Integer coins;
	private @Getter @Setter Boolean ativo;
	private @Getter @Setter Integer posicao;
	private @Getter @Setter Integer vitorias;
	
	public Jogador() {
		this.coins = 300;
		this.ativo = true;
		this.posicao = null;
		this.vitorias = 0;
	}

}
