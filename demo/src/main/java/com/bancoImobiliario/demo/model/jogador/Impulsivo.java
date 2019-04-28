package com.bancoImobiliario.demo.model.jogador;

import com.bancoImobiliario.demo.model.Propriedade;

public class Impulsivo extends Jogador {

	@Override
	public Boolean compra(Propriedade propriedade) {
		return true;
	}

}
