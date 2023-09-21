package robots;

import ataques.Ataque;
import ataques.Tipo;
import personajes.Estado;

public abstract class Robot {
	
	private String nombre;
	private Tipo tipo;
	private int vida;
	private int energia;
	boolean vive = true;
	boolean energizado = true;
    private Estado estado = Estado.NORMAL;
    private int turnosEstadoRestantes = 0;
    private boolean estaEscudado;
    
	public Robot(String nombre, Tipo tipo, int vida, int energia, Estado estado) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.vida = vida;
		this.energia = energia;
		this.estado = estado;
		this.setEstaEscudado(false);
	}

	public String getNombreRobot() {
		return nombre;
	}
	
	public boolean getEstaEscudado() {
		return estaEscudado;
	}
	
	public void setEstaEscudado(boolean estaEscudado) {
		this.estaEscudado = estaEscudado;
	}

	public abstract Ataque getAtq1();
	public abstract Ataque getAtq2();
	public abstract Ataque getAtq3();
	public abstract Ataque getAtq4();
	
	public void reducirVida(int cant) {
		if(vive) {
			vida -= cant;
			if(this.vida<=0) {
				vida = 0;
				this.vive = false;
			}
		}
	}
	
	public void reducirEnergia(int cant) {
		if(vive) {
			energia -= cant; 
	        if (energia <= 0) {
	            energia = 0; 
	            this.energizado = false;
	        }
		}
    }
	
	public void aumentarEnergia(int cant) {
		energia += cant; 
	}
	
	public int getEnergia() {
		return energia;
	}
	
	public int getVida() {
		return vida;
	}
	
	public boolean getVive() {
		return this.vive;
	}
	
	public void mostrarEstadisticas() {
		System.out.println(this.nombre + " -  VIDA: " + this.vida + " - ENERGIA: " + this.energia + " - ESTADO: " + this.estado);
	}

	public Tipo getTipo() {
		return tipo;
	}
	
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getTurnosRestantes() {
        return turnosEstadoRestantes;
    }

    public void setTurnosRestantes(int turnos) {
        this.turnosEstadoRestantes = turnos;
    }

}
