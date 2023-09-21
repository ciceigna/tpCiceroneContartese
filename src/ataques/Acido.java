package ataques;

import personajes.Estado;

public class Acido extends Ataque{

	public Acido() {
		super("ACIDO", 100, 75, 80, Tipo.CORROSION, Estado.NORMAL);
	}

}
