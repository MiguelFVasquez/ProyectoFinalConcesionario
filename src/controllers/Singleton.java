package controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.AdministradorException;
import Exceptions.AlquilerException;
import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Exceptions.TransaccionException;
import Exceptions.VehiculoException;
import Exceptions.VentaException;
import Model.Administrador;
import Model.Cliente;
import Model.Concesionario;
import Model.Empleado;
import Model.TipoCombustible;
import Model.TipoEstado;
import Model.TipoTransaccion;
import Model.TipoTransmicion;
import Model.Transaccion;
import Model.Vehiculo;

public class Singleton {

	Concesionario concesionario= null;

	private static class SingletonHolder{
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static Singleton eINSTANCE= new Singleton();
	}


	public static Singleton getInstance(){
		return SingletonHolder.eINSTANCE;
	}

	public Singleton(){
		inicializarDatos();
	}


	private void inicializarDatos() {
		concesionario = new Concesionario("UQ");
		Administrador administrador1= new Administrador("Miguel", "florez", "1010", "1234");
		Administrador administrador2= new Administrador("Miguel", "florez", "2314", "12345");

		Empleado empleado1= new Empleado("Juan", "Vasquez", "0000");
		Empleado empleado2= new Empleado("Santi", "Ovalei", "1111");

		Cliente cliente1= new Cliente("Miguel", "Florez", "1001", "2004-10-01");

		empleado1.getListaClientes().add(cliente1);
		concesionario.getListaAdministradores().add(administrador1);
		concesionario.getListaAdministradores().add(administrador2);

		concesionario.getListaEmpleados().add(empleado1);
		concesionario.getListaEmpleados().add(empleado2);

	}


	public Concesionario getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}


	//-----------------------Metodos del Administrador---------------------------------------

		public boolean verificarAdmin(String identificacion, String contrasenia){
			return concesionario.verificarAdministrador(identificacion, contrasenia);
		}
		public Administrador obtenerAdministrador(String identificacion, String contrasenia){
			return concesionario.obtenerAdministrador(identificacion, contrasenia);
		}

		public boolean crearAdministrador(String nombre, String apellido, String cedula, String contrasenia) throws AdministradorException {
			Administrador nuevoAdministrador= new Administrador(nombre, apellido, cedula, contrasenia);
			return concesionario.crearAdministrador(nuevoAdministrador);
		}

		public boolean eliminarAdministrador(Administrador adminEliminar) throws AdministradorException{
			return concesionario.eliminarAdministrador(adminEliminar);
		}

		public boolean actualizarAdmin(String nombre, String apellido, String cedula, String contrasenia) throws AdministradorException {
			return concesionario.actualizarAdministrador(nombre, apellido, cedula, contrasenia);
		}



	//------------------------Metodos CRUD del cliente---------------------------------------
		public boolean crearCliente(String idEmpleado,String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException, ClienteException {
			Cliente newCliente= new Cliente(nombre, apellido, cedula, fechaNacimiento);
			return concesionario.crearCliente(idEmpleado, newCliente);
		}


		public boolean eliminarCliente(String idEmpleado, Cliente clienteEliminar) throws EmpleadoException, ClienteException{
			return concesionario.eliminarCliente(idEmpleado, clienteEliminar);
		}


		public boolean actualizarCliente(String idEmpleado,String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException, ClienteException{
			return concesionario.actualizarCliente(idEmpleado, nombre, apellido, cedula, fechaNacimiento);
		}


	//------------------------Metodos CRUD del empleado----------------------------------------

		public boolean verificarEmpleado(String identificacion){
			return concesionario.verificarEmpleado(identificacion);
		}

		public Empleado obtenerEmpleado(String identificacion) {
			return concesionario.obtenerEmpleado(identificacion);
		}

		public boolean crearEmpleado(String identificacionAdmin,String contraseniaAdmin,String nombre, String apellido, String identificacion) throws EmpleadoException, AdministradorException {
			Empleado empleadoNuevoEmpleado= new Empleado(nombre, apellido, identificacion);
			Administrador administradorEmpleado= concesionario.obtenerAdministrador(identificacionAdmin,contraseniaAdmin);

			return concesionario.crearEmpleado(administradorEmpleado, empleadoNuevoEmpleado);
		}

		public boolean eliminarEmpleado (String identificacionAdmin, Empleado empleadoEliminar) throws AdministradorException, EmpleadoException {
			return concesionario.eliminarEmpleado(identificacionAdmin, empleadoEliminar);
		}

		public boolean actualizarEmpleado(String idAdmin, String nombre, String apellido, String cedula) throws AdministradorException, EmpleadoException{
			return concesionario.actualizarEmpleado(idAdmin, nombre, apellido, cedula);
		}

//--------------------------------------------METODOS DE VEHICULOS----------------------------------------------------
		public boolean crearMoto(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,String autonimia, String tiempoPromedioCarga,
				boolean esEnchufable, boolean esHibridoLigero) throws EmpleadoException, VehiculoException{
			return concesionario.crearMoto(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero);

		}

		public boolean crearSedan(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
				String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
				String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
				String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril) throws VehiculoException, EmpleadoException{

			return concesionario.crearSedan(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril);

		}
		public boolean crearDeportivo(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
				String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
				String  num_pasajeros, String num_Puertas, String num_Bolsas, String num_Caballos_Fuerza, String tiempo_en_100KM) throws EmpleadoException, VehiculoException{

			return concesionario.crearDeportivo(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM);

		}
		public boolean crearCamioneta(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
				String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
				String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
				String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril,
				boolean esCuatroxCuatro) throws VehiculoException, EmpleadoException {

			return concesionario.crearCamioneta(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril, esCuatroxCuatro);
		}
		public boolean crearPickUp(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
				String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
				String  num_pasajeros, String num_Puertas, String num_Bolsas, String capacidadCarga, boolean aire_Acondicionado,
				boolean cam_Reversa, boolean vel_Crucero, boolean abs, boolean esCuatroPorCuatro) throws EmpleadoException, VehiculoException {

			return concesionario.crearPickUp(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro);


		}

		public boolean crearVan(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
				String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
				String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
				String num_Bolsas, boolean abs) throws EmpleadoException, VehiculoException {

			return concesionario.crearVan(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs);


		}
		public boolean crearBus(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
				String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
				String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
				boolean vel_Crucero, String num_Bolsas, boolean abs, String num_ejes, String num_salidas_emergencia) throws EmpleadoException, VehiculoException {

			return concesionario.crearBus(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas, abs, num_ejes, num_salidas_emergencia);

		}

		public boolean crearCamion(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
				TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
				String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
				boolean aire_Acondicionado, boolean abs, String num_ejes, String tipo_Camion) throws EmpleadoException, VehiculoException {

			return concesionario.crearCamion(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, aire_Acondicionado, abs, num_ejes, tipo_Camion);

		}

		public boolean eliminarVehiculo(Vehiculo vehiculoSeleccionado) throws VehiculoException{

			return concesionario.eliminarVehiculo(vehiculoSeleccionado);
		}

		public List<Vehiculo> getListaVehiculos(){
			return concesionario.getListaVehiculos();
		}


//---------------------------------TRANSACCIONES-----------------------------------------

		public boolean crearTransaccion(String identificacionEmpleado,TipoTransaccion tipoTransaccion, String fecha, double total , String codigo, String cantDias,
				String cedulaCliente) throws EmpleadoException, TransaccionException, ClienteException{
			return concesionario.crearTransaccion(identificacionEmpleado, tipoTransaccion, fecha, total, codigo, cantDias,cedulaCliente);
		}

		public boolean eliminarTransaccion(Transaccion transaccionEliminar) throws TransaccionException{
			return concesionario.eliminarTransaccion(transaccionEliminar);
		}

		public List<Transaccion> getListaTransacciones(){
			return concesionario.getListaTransacciones();
		}

//-------------------------------VENTA Y ALQUILER------------------------------------------------------------------------

		public boolean venderAuto(String idEmpleado, Vehiculo vehiculoVenta) throws VehiculoException, EmpleadoException, VentaException{
			return concesionario.venderVehiculo(idEmpleado, vehiculoVenta);
		}

		public boolean alquilarAuto(String idEmpleado, Vehiculo vehiculoAlquiler) throws EmpleadoException, AlquilerException, VehiculoException{
			return concesionario.alquilarVehiculo(idEmpleado, vehiculoAlquiler);
		}
//--------------------------------------------------------------

		public boolean validarFechas(String fechaInicial, String fechaFinal) throws ParseException {
			boolean esValido = concesionario.validarFechas(fechaInicial, fechaFinal);
			return esValido;
		}


		public List<Transaccion> getListaDatosReportes(String fechaInicial, String fechaFinal){
			return concesionario.getListaDatosReportes(fechaInicial, fechaFinal);
		}


}
