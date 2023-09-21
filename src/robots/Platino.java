package robots;

import ataques.Tipo;
import personajes.Estado;
import ataques.Metralleta;
import ataques.Acido;
import ataques.Fuego;
import ataques.Escudo;

public class Platino extends Robot{

	private Metralleta atq1;
	private Acido atq2;
	private Fuego atq3;
	private Escudo atq4;
	
	public Platino() {
		super("ROBOT DE PLATINO",Tipo.PLATINO,1200,1000,Estado.NORMAL);
		atq1 = new Metralleta();
		atq2 = new Acido();
		atq3 = new Fuego();
		atq4 = new Escudo();
	}
	
	public Metralleta getAtq1() {
		return atq1;
	}

	public Acido getAtq2() {
		return atq2;
	}
	
	public Fuego getAtq3() {
		return atq3;
	}
	
	public Escudo getAtq4() {
		return atq4;
	}
	
}
