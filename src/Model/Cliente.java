package Model;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable{
	private String fechaDeNacimiento;
	/**
	 *
	 */
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 */
	public Cliente(String nombre, String apellido, String identificacion) {
		super(nombre, apellido, identificacion);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param fechaDeNacimiento
	 */
	public Cliente(String nombre, String apellido, String identificacion, String fechaDeNacimiento) {
		super(nombre, apellido, identificacion);
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Cliente fecha de nacimiento: " + fechaDeNacimiento;
	}

	public boolean verificarIdentificacion(String identificacion) {
		return this.getIdentificacion().equals(identificacion);
	}

}
