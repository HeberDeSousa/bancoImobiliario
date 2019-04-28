package com.bancoImobiliario.demo.model;

import java.util.Random;

public final class Dado {
	
	public static Integer rolar() {
		return new Random().nextInt(6)+1;
	}

}
