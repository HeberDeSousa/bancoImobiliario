package com.bancoImobiliario.demo.model.jogador;

import com.bancoImobiliario.demo.model.Propriedade;

public class Exigente extends Jogador {

	@Override
	public Boolean compra(Propriedade propriedade) {
		if (propriedade.getAluguel() > 50) {
			return true;
		}
		return false;
	}

}
