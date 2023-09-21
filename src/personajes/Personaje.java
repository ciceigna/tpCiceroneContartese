package personajes;

import robots.Robot;

public abstract class Personaje {
	
	private String nombre;
	private Robot robot;
	
	
	public Personaje(String nombre, Robot robot) {
		this.nombre = nombre;
		this.robot = robot;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Robot getRobot() {
		return robot;
	}
}
