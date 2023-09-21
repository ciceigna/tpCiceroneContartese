//Trabajo Practico Robots - Ignacio Cicerone, Valentina Contartese
package tpCiceroneContartese;

import personajes.Estado;
import personajes.Rival;
import personajes.Usuario;
import robots.Platino;
import robots.Robot;
import robots.Titanio;
import utilidades.Utiles;


public class Principal {

	public static void main(String[] args) {
		
		Usuario usuario;
		Rival npc;
		Robot robot;
		int turno = Utiles.r.nextInt(2);
		
		System.out.println("B I E N V E N I D O");
		System.out.println("Por favor, ingrese su nombre");
		String nombre = Utiles.s.nextLine();
		System.out.println("Ingrese 1 para luchar como el ROBOT DE TITANIO o 2 para luchar como el ROBOT DE PLATINO");
		int opc = Utiles.ingresarEntero(1,2);
		robot = (opc==1?new Titanio():new Platino());
		usuario = new Usuario(nombre, robot);
		
		int randRobot = Utiles.r.nextInt(2);
		robot = (randRobot==0?new Titanio():new Platino());
		npc = new Rival(robot);
		
		System.out.println(usuario.getNombre() + " va a jugar con " + usuario.getRobot().getNombreRobot());
		System.out.println("El " + npc.getNombre() + " va a jugar con " + npc.getRobot().getNombreRobot());
		
		System.out.println("Determinando primer turno");
		Utiles.esperar(1500);
		System.out.println("LISTO! Empieza " + ((turno==0)?usuario.getNombre():npc.getNombre()));
		
		while(usuario.getRobot().getVive() && npc.getRobot().getVive()){
			
			if(turno==0) {
				System.out.println("TURNO DE " + usuario.getRobot().getNombreRobot() + " ALIADO");
				usuario.getRobot().mostrarEstadisticas();
				boolean error = false;
				boolean puedeAtacar = (usuario.getRobot().getEstado() == Estado.INHABILITADO)?false:true;
				
		        if (usuario.getRobot().getEstado() == Estado.INHABILITADO) {
		            System.out.println(usuario.getNombre() + " ALIADO ESTÁ DESHABILITADO. NO PUEDE ATACAR."); 
		            usuario.getRobot().setTurnosRestantes(usuario.getRobot().getTurnosRestantes() - 1);
		            
		            if (usuario.getRobot().getTurnosRestantes() <= 0) {
		                usuario.getRobot().setEstado(Estado.NORMAL);
		                System.out.println(npc.getNombre() + " ALIADO HABILITADO NUEVAMENTE.");
		            }
		            error = true;
		            
		        }else if (usuario.getRobot().getEstado() == Estado.ESCUDADO) {
		            usuario.getRobot().setTurnosRestantes(usuario.getRobot().getTurnosRestantes() - 1);
		            
		            if (usuario.getRobot().getTurnosRestantes() <= 0) {
		                usuario.getRobot().setEstado(Estado.NORMAL);
		            }
		            
		            error = true;
		        }
		        
				do {
					error = false;
					System.out.println("Elija un ataque ");
					System.out.println("1) " + usuario.getRobot().getAtq1().getNombreAtq() + " - ENERGIA REQUERIDA: " + usuario.getRobot().getAtq1().getEnergiaReq());
					System.out.println("2) " + usuario.getRobot().getAtq2().getNombreAtq() + " - ENERGIA REQUERIDA: " + usuario.getRobot().getAtq2().getEnergiaReq());
					System.out.println("3) " + usuario.getRobot().getAtq3().getNombreAtq() + " - ENERGIA REQUERIDA: " + usuario.getRobot().getAtq3().getEnergiaReq());
					System.out.println("4) " + usuario.getRobot().getAtq4().getNombreAtq() + " - ENERGIA REQUERIDA: " + usuario.getRobot().getAtq4().getEnergiaReq());
				    
					opc = Utiles.ingresarEntero(1,4);
					
					switch(opc) {
					case 1:
						if(usuario.getRobot().getAtq1().getEnergiaReq()>usuario.getRobot().getEnergia()) {
							error = true;
							System.out.println("NO HAY ENERGIA SUFICIENTE EN LAS RESERVAS.");
						} else {
							usuario.getRobot().getAtq1().ejecutarAtaque(npc, usuario, turno);
						} 
						break;
					case 2:
						if(usuario.getRobot().getAtq2().getEnergiaReq()>usuario.getRobot().getEnergia()) {
							error = true;
							System.out.println("NO HAY ENERGIA SUFICIENTE EN LAS RESERVAS.");
						} else {
							usuario.getRobot().getAtq2().ejecutarAtaque(npc, usuario, turno);
						} 
						break;
					case 3:
						if(usuario.getRobot().getAtq3().getEnergiaReq()>usuario.getRobot().getEnergia()) {
							error = true;
							System.out.println("NO HAY ENERGIA SUFICIENTE EN LAS RESERVAS.");
						} else {
							usuario.getRobot().getAtq3().ejecutarAtaque(npc, usuario, turno);
						} 
						break;
					case 4:
						if(usuario.getRobot().getAtq4().getEnergiaReq()>usuario.getRobot().getEnergia()) {
							error = true;
							System.out.println("NO HAY ENERGIA SUFICIENTE EN LAS RESERVAS.");
						} else {
	                        
							usuario.getRobot().getAtq4().ejecutarAtaque(npc, usuario, turno);
						} 
						break;
					}
				} while(error && puedeAtacar);
			}else {
				System.out.println("TURNO DEL " + npc.getNombre());
				npc.getRobot().mostrarEstadisticas();
				System.out.println(npc.getNombre() + " ESTÁ POR ATACAR. . .");
				Utiles.esperar(2000);
				boolean error = false;
				do {
					error = false;
					opc = Utiles.r.nextInt(4)+1;
					
			        if (npc.getRobot().getEstado() == Estado.INHABILITADO) {
			            System.out.println(npc.getNombre() + " ESTÁ DESHABILITADO. NO PUEDE ATACAR."); 
			            npc.getRobot().setTurnosRestantes(npc.getRobot().getTurnosRestantes() - 1);
			            
			            if (npc.getRobot().getTurnosRestantes() <= 0) {
			                npc.getRobot().setEstado(Estado.NORMAL);
			                System.out.println(npc.getNombre() + " ENEMIGO HABILITADO NUEVAMENTE.");
			            }
			            
			            error = true;
			        }else if (usuario.getRobot().getEstado() == Estado.ESCUDADO) {
			            usuario.getRobot().setTurnosRestantes(usuario.getRobot().getTurnosRestantes() - 1);
			            
			            if (usuario.getRobot().getTurnosRestantes() <= 0) {
			                usuario.getRobot().setEstado(Estado.NORMAL);
			            }
			            
			            error = true;
			        }

					switch(opc) {
					case 1:
						if(npc.getRobot().getAtq1().getEnergiaReq()>npc.getRobot().getEnergia()) {
							error = true;
						} else {
							npc.getRobot().getAtq1().ejecutarAtaque(npc, usuario, turno);
						}
						break;
					case 2:
						if(npc.getRobot().getAtq2().getEnergiaReq()>npc.getRobot().getEnergia()) {
							error = true;
						} else {
							npc.getRobot().getAtq2().ejecutarAtaque(npc, usuario, turno);
						}
						break;
					case 3:
						if(npc.getRobot().getAtq3().getEnergiaReq()>npc.getRobot().getEnergia()) {
							error = true;
						} else {
							npc.getRobot().getAtq3().ejecutarAtaque(npc, usuario, turno);
						}
						break;
					case 4:
						if(npc.getRobot().getAtq4().getEnergiaReq()>npc.getRobot().getEnergia()) {
							error = true;
						} else {
							npc.getRobot().getAtq4().ejecutarAtaque(npc, usuario, turno);
						}
						break;
					}
				} while(error);
			}	
			turno = (turno==0)?1:0;	
		}
		
		if(usuario.getRobot().getVive()) {
			System.out.println("F E L I C I D A D E S ! ! !");
			System.out.println("G A N A S T E   :-D");
		} else {
			System.out.println("H A S     P E R D I D O");
			System.out.println("mal ahi :[");
		}
	}
	
}
//ojala nos apruebe gracias