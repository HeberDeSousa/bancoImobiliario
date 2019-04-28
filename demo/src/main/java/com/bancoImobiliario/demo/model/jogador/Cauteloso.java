package com.bancoImobiliario.demo.model.jogador;

import com.bancoImobiliario.demo.model.Propriedade;

public class Cauteloso extends Jogador {

	@Override
	public Boolean compra(Propriedade propriedade) {
		if (this.getCoins() - propriedade.getPreco() >= 80) {
			return true;
		}
		return false;
	}

}
