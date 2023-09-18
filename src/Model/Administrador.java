package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Exceptions.EmpleadoException;

public class Administrador extends Persona implements Serializable{
	private String credenciales;
	private List<Empleado> listaEmpleados = new ArrayList<>();

	public Administrador(){

	}

	public Administrador(String credenciales) {
		super();
		this.credenciales = credenciales;
	}

	public Administrador(String nombre, String apellido,String identificacion, String credenciales) {
		super(nombre,apellido,identificacion);
		this.credenciales= credenciales;
	}

	public String getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(String credenciales) {
		this.credenciales = credenciales;

	}


	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	@Override
	public String toString() {
		return super.toString()+"Administrador \nCredenciales=" + credenciales;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 *
	 * @param identificacion
	 * @return
	 */
	private boolean verificarEmpleado (String identificacion){
		boolean verificado= false;
		for (Empleado empleadoAux : listaEmpleados) {
			if(empleadoAux.verificarIdentificacion(identificacion)){
				verificado= true;
				return verificado;
			}
		}

		return verificado;
	}

	/**
	 *
	 * @param identificacion
	 * @return
	 */
	public Empleado obtenerEmpleado(String identificacion) {
		Empleado empleadoEncontrado= null;
		for (Empleado empleadoAux : listaEmpleados) {
			if (empleadoAux.verificarIdentificacion(identificacion))
				empleadoEncontrado= empleadoAux;
		}

		return empleadoEncontrado;
	}

	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws EmpleadoException
	 */
	public boolean crearEmpleado(Empleado newEmpleado) throws EmpleadoException{
		boolean creado= true;
		boolean empleadoEncontrado= verificarEmpleado(newEmpleado.getIdentificacion());
		if(empleadoEncontrado){
			creado=false;
			throw new EmpleadoException("El empleado ya se encuentra registrado");
		}
		listaEmpleados.add(newEmpleado);

		return creado;
	}


	/**
	 *
	 * @param identificacion
	 * @return
	 * @throws EmpleadoException
	 */
	public boolean eliminarEmpleado(Empleado empleadoEliminar)throws EmpleadoException{
		boolean eliminado= false;
		Empleado empleadoEncontrado= obtenerEmpleado(empleadoEliminar.getIdentificacion());
		if (empleadoEncontrado==null) {
			throw new EmpleadoException("El empleado que desea eliminar no se encuentra en el sistema");
		}else {
			eliminado= true;
			listaEmpleados.remove(empleadoEncontrado);
		}

		return eliminado;
	}

	/**
	 *
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return
	 * @throws EmpleadoException
	 */
	public boolean actualizarEmpleado(String nombre, String apellido, String identificacion)throws EmpleadoException{
		boolean actualizado= false;
		Empleado empleadoAux= obtenerEmpleado(identificacion);
		if (empleadoAux== null) {
			throw new EmpleadoException("El empleado no se ha encontrado");
		}else{
			actualizado=true;
			empleadoAux.setNombre(nombre);
			empleadoAux.setApellido(apellido);
			empleadoAux.setIdentificacion(identificacion);
		}
		return actualizado;
	}

	public boolean verificarIDContrasenia(String identificacion, String contrasenia){
		if (verificarIdentificacion(identificacion) && verificarContrasenia(contrasenia) ) {
			return true;
		}
		return false;
	}


	public boolean verificarIdentificacion(String identificacion){
		return this.getIdentificacion().equals(identificacion);
	}

	public boolean verificarContrasenia(String contrasenia) {
		return this.getCredenciales().equals(contrasenia);
	}

}
