package ataques;

import personajes.Estado;

public class Escudo extends Ataque {
	
	public Escudo() {
		super("ESCUDO", 50, 0, 40, Tipo.EFECTO, Estado.ESCUDADO);
	}
}
