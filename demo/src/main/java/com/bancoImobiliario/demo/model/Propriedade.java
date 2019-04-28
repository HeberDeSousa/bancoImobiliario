package com.bancoImobiliario.demo.model;

import com.bancoImobiliario.demo.model.jogador.Jogador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Propriedade {
	
	private @Getter @Setter Integer preco;
	private @Getter @Setter Integer aluguel;
	private @Getter @Setter Jogador proprietario;

}
