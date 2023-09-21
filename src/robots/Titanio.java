package robots;

import ataques.Tipo;
import personajes.Estado;
import ataques.Metralleta;
import ataques.Acido;
import ataques.Fuego;
import ataques.Interferencia;


public class Titanio extends Robot{
	
	private Metralleta atq1;
	private Acido atq2;
	private Fuego atq3;
	private Interferencia atq4;

	public Titanio() {
		super("ROBOT DE TITANIO",Tipo.TITANIO,1000,1500,Estado.NORMAL);
		atq1 = new Metralleta();
		atq2 = new Acido();
		atq3 = new Fuego();
		atq4 = new Interferencia();
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
	
	public Interferencia getAtq4() {
		return atq4;
	}
	
}
