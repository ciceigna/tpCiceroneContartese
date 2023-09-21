package ataques;

import personajes.Estado;
import personajes.Rival;
import personajes.Usuario;
import utilidades.Utiles;

public abstract class Ataque {

	private String nombre;
	private int energiaReq;
	private int danio;
	private int precision;
	private Tipo tipo;
	private Estado estado = Estado.NORMAL;
	private int duracionEstado = 0;

	public Ataque(String nombre, int energiaReq, int danio, int precision, Tipo tipo, Estado estado) {
		this.nombre = nombre;
		this.energiaReq = energiaReq;
		this.danio = danio;
		this.precision = precision;
		this.tipo = tipo;
		this.estado = estado;
	}

	public String getNombreAtq() {
		return nombre;
	}

	public int getEnergiaReq() {
		return energiaReq;
	}

	public void ejecutarAtaque(Rival oponente,Usuario usuario , int turno) {
		int prob = Utiles.r.nextInt(100)+1;
		float mult = 1;
		
		if (estado == Estado.ESCUDADO) {
            
			if(turno==0) {
				if(usuario.getRobot().getEstado()==Estado.ESCUDADO) {
					System.out.println("ERROR! EL " + usuario.getRobot().getNombreRobot() + "ALIADO YA ESTA UTILIZANDO ESCUDO.");
					duracionEstado -= 1;
				}else {
				usuario.getRobot().setEstado(Estado.ESCUDADO);
				duracionEstado = Utiles.r.nextInt(3)+1;
                System.out.println("El " + usuario.getRobot().getNombreRobot() + " ALIADO UTILIZÓ " + this.nombre
                        + " POR " + duracionEstado + " TURNOS. ");
				}
				
			} else {
				if(oponente.getRobot().getEstado()==Estado.ESCUDADO) {
					System.out.println("ERROR! EL " + usuario.getRobot().getNombreRobot() + " RIVAL YA ESTA UTILIZANDO ESCUDO.");
					duracionEstado -= 1;
				}else {
				oponente.getRobot().setEstado(Estado.ESCUDADO);
				duracionEstado = Utiles.r.nextInt(3)+1;
                System.out.println("El " + oponente.getRobot().getNombreRobot() + " RIVAL UTILIZÓ " + this.nombre
                        + " POR " + duracionEstado + " TURNOS. ");
				}
            }
			
		}else if (estado == Estado.INHABILITADO) {
             if (turno == 0) {
            	 oponente.getRobot().setEstado(Estado.INHABILITADO);
            	 duracionEstado = Utiles.r.nextInt(4)+1;
            	 System.out.println(oponente.getRobot().getNombreRobot() + " RIVAL ESTA INHABILITADO. No puede atacar por " + duracionEstado + " TURNOS");
             }else {
            	 oponente.getRobot().setEstado(Estado.INHABILITADO);
            	 duracionEstado = Utiles.r.nextInt(4)+1;
            	 System.out.println("El " + oponente.getRobot().getNombreRobot() + " ALIADO está bajo el efecto de INTERFERENCIA. No puede atacar por " + duracionEstado + " TURNOS");
             }
             
        }else {
        	if (turno==0) {
        		
            	if(this.precision>=prob) {
				mult = computarEfectividad(oponente.getRobot().getTipo());
				float danioReducido = (oponente.getRobot().getEstado()==Estado.ESCUDADO)?this.danio * mult / 2:this.danio * mult;
				if(mult==2) {
					System.out.println("Daño mayor. EFECTIVIDAD: 200% ! ! !");
				} else if(mult==0.5f) {
					System.out.println("Menor daño. EFECTIVIDAD: 50% : [");
				}
				
				oponente.getRobot().reducirVida((int)(danioReducido));
				System.out.println("TU " + usuario.getRobot().getNombreRobot() + " UTILIZÓ " + this.nombre + ". DAÑO CAUSADO AL "
							+ oponente.getRobot().getNombreRobot() + ": " + danioReducido);
	
				} else {
					System.out.println(usuario.getRobot().getNombreRobot() + " FALLÓ.");
				}
            	} else {
            		
					if(this.precision>=prob) {
						mult = computarEfectividad(oponente.getRobot().getTipo());
						float danioReducido = (usuario.getRobot().getEstado()==Estado.ESCUDADO)?this.danio * mult / 2:this.danio * mult;
						if(mult==2) {
							System.out.println("Daño mayor. EFECTIVIDAD: 200%");
						} else if(mult==0.5f) {
							System.out.println("Menor daño. EFECTIVIDAD: 50%");
						}
						usuario.getRobot().reducirVida((int)(danioReducido));
						System.out.println("EL " + oponente.getRobot().getNombreRobot() + " RIVAL UTILIZÓ " + this.nombre + ". DAÑO CAUSADO A " 
								+ usuario.getRobot().getNombreRobot() + ": " + danioReducido);
	
				} else {
					System.out.println(oponente.getRobot().getNombreRobot() + " FALLÓ.");
				}
			}
		}
	}


	@SuppressWarnings("incomplete-switch")
	public float computarEfectividad(Tipo roboTipo) {
		float mult = 1;
		
		switch(tipo) {
			case IMPACTO:
				if(roboTipo == Tipo.TITANIO) {
					mult = 0.5f;
				} else if(roboTipo == Tipo.PLATINO) {
					mult = 2;
				}
			break;
			case FUEGO:
				if(roboTipo == Tipo.TITANIO) {
					mult = 2;
				}
			break;
			case CORROSION:
				if(roboTipo == Tipo.PLATINO) {
					mult = 0.5f;
				}
			break;
		}
		return mult;
	}
}
