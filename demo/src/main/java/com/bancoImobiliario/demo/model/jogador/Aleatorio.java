package com.bancoImobiliario.demo.model.jogador;


import java.util.Random;

import com.bancoImobiliario.demo.model.Propriedade;

public class Aleatorio extends Jogador {

	@Override
	public Boolean compra(Propriedade propriedade) {
		return new Random().nextInt(2) == 0;
	}

}
