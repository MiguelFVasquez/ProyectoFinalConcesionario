
package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Exceptions.TransaccionException;
import Exceptions.VehiculoException;
import Model.Cliente;
import Model.Concesionario;
import Model.Empleado;
import Model.TipoCombustible;
import Model.TipoEstado;
import Model.TipoTransaccion;
import Model.TipoTransmicion;
import Model.TipoVehiculo;
import Model.Transaccion;
import Model.Vehiculo;
import application.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class EmpleadoController implements Initializable {

	Singleton singleton = Singleton.getInstance();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


//Table view clientes
    @FXML
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableColumn<Cliente, String> columNombreCliente;

    @FXML
    private TableColumn<Cliente, String> columApellidosCliente;

    @FXML
    private TableColumn<Cliente, String> columCedulaCliente;

    @FXML
    private TableColumn<Cliente, String> columFechaNacimiento;

//Table view Vehiculos
    @FXML
    private TableView<Vehiculo> tableViewVehiculos;
    @FXML
    private TableColumn<Vehiculo, String> columMarca;
    @FXML
    private TableColumn<Vehiculo, String> columModelo;
    @FXML
    private TableColumn<Vehiculo, Double> columPrecio;
    @FXML
    private TableColumn<Vehiculo, String> columnVehiculo;

    @FXML
    private TableColumn<Vehiculo, String> columEstado;
    @FXML
    private TableColumn<Vehiculo, String> columTipoCombustible;


//Table view transacciones

    @FXML
    private TableView<Transaccion> tableViewTransaccion;
    @FXML
    private TableColumn<Transaccion, TipoTransaccion> columTipoTransaccion;
    @FXML
    private TableColumn<Transaccion, String> columCodigo;
    @FXML
    private TableColumn<Transaccion, String>columCedulaFactura;
    @FXML
    private TableColumn<Transaccion, String> columFecha;
    @FXML
    private TableColumn<Transaccion, Double>columTotal;


    @FXML
    private Button btnRegistrarTransaccion;
    @FXML
    private Button btnNuevaTransaccion;

    @FXML
    private Button btnAniadirImagen;

//Buttons
    @FXML
    private Button btnVolver;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVender;

    @FXML
    private Button btnAlquilar;
    @FXML
    private Button btnEliminarAuto;


//TXTFields de cliente
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtApellidoCliente;
    @FXML
    private TextField txtCedulaCliente;
    @FXML
    private DatePicker datePickerFechaNacimiento;

//TxtFields de factura

    @FXML
    private TextField txtCodigo;
    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtDiasAlquiler;

    @FXML
    private TextField txtCedulaTransaccion;


//TxtFields de vehiculo
    @FXML
    private TextField txtMarcaVehiculo;
    @FXML
    private TextField txtModeloVehiculo;
    @FXML
    private TextField txtCaballosFuerza;

    @FXML
    private TextField txtSalidasEmergencia;

    @FXML
    private TextField txt100km;

    @FXML
    private TextField txtCap_Maletero;
    @FXML
    private TextField txtTipoCamion;
    @FXML
    private TextField txtNumPasajeros;
    @FXML
    private TextField txtVelMaxima;

    @FXML
    private TextField txtNum_bolsas;

    @FXML
    private TextField txtCapacidadCarga;

    @FXML
    private TextField txtNumPuertas;

    @FXML
    private TextField txtNumEjes;

    @FXML
    private TextField txtCilindraje;

    @FXML
    private TextField txtCant_CambioVehiculo;

    @FXML
    private TextField txtAutonomiaCarga;
    @FXML
    private TextField txtPromedioCarga;
    @FXML
    private TextField txtPrecio;


//CheckBox de vehiculo
    @FXML
    private CheckBox checkAsistenteCarril;
    @FXML
    private CheckBox checkSensColision;

    @FXML
    private CheckBox checkCamReversa;

    @FXML
    private CheckBox checkABS;

    @FXML
    private CheckBox checkCuatroPorCuatro;

    @FXML
    private CheckBox checkSensTrafico;

    @FXML
    private CheckBox checkAireAcondicionado;
    @FXML
    private CheckBox checkVelCrucer;

    @FXML
    private CheckBox chekHibridoLigero;

    @FXML
    private CheckBox checkEnchufable;

//ComboBox's
    @FXML
    private ComboBox<TipoTransmicion> comboBoxTipoTransmicion;
    @FXML
    private ComboBox<TipoVehiculo> comboBoxTipoVehiculo;
    @FXML
    private ComboBox<TipoEstado> comboBoxEstado;

    @FXML
    private ComboBox<TipoTransaccion> comboBoxTipoTransaccion;
    @FXML
    private ComboBox<TipoCombustible> comboBoxCombustible;

	private PrincipalController principioController;
	private LoginEmpleadoController loginEmpleadoController;

	private Concesionario concesionario;

	private Stage stage;

	private Aplicacion aplicacion;

//Items seleccionados de sus respectivas tableView
	private Cliente clienteSeleccion;
	private Vehiculo vehiculoSeleccion;
	private Transaccion transaccionSeleccion;

	/*Las listas necesarias para que se muestren en su respectiva table view
	 * */
	ObservableList<Cliente> listaClientes= FXCollections.observableArrayList();
	ObservableList<Vehiculo> listaVehiculos= FXCollections.observableArrayList();
	ObservableList<Transaccion> listaTransacciones= FXCollections.observableArrayList();


	private ObservableList<Cliente> getListaClientes(){
		String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();
		Empleado empleadoAux= singleton.obtenerEmpleado(idEmpleado);
		listaClientes.addAll(empleadoAux.getListaClientes());
		return listaClientes;

	}
	private ObservableList<Vehiculo> getListaVehiculos(){
		listaVehiculos.addAll(singleton.getListaVehiculos());
		return listaVehiculos;
	}

	private ObservableList<Transaccion> getListaTransacciones() {
		listaTransacciones.addAll(singleton.getListaTransacciones());
		return listaTransacciones;
	}

	private boolean validarDatos(String nombre, String apellido, String cedula, String fechaNacimiento) {
		String notificacion = "";

		if (nombre == null || nombre.equals(""))
			notificacion += "Nombre invalido\n";

		if (apellido == null || apellido.equals(""))
			notificacion += "Apellido(s) inválido\n";

		if (cedula == null || cedula.equals("")) {
			notificacion += "Número de cedula invalido\n";
		}

//		if(fechaNacimiento == null || fechaNacimiento.equals(""))
//			notificacion+= "fecha de nacimiento invalida\n";

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Cliente no creado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	/**
	 * Este metodo lo que hace es verificar si los datos ingresados en la pestaña de gestion de vehiculos son valores validos, este verifica las variables
	 * que comparten todos los vehiculos
	 * Se tiene en cuenta que si el combustible es electrico hay que verificar que los atributos de autonomia y promedio de carga no pueden ser nulos
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param precio
	 * @param autonomia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
	 * @return
	 */
	private boolean verificarDatos(String marca, String modelo, String cambios, String velMaxima, TipoTransmicion transmicion, TipoCombustible combustible,
			TipoEstado estado, Double precio, String autonomia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero ){


		String notificacion= "";

		if (marca==null || marca.equals("")) {
			notificacion+= "Marca invalida\n";
		}
		if (modelo==null || modelo.equals("")) {
			notificacion+= "Modelo invalido\n";
		}
		if (cambios==null || cambios.equals("")) {
			notificacion+= "Cambios invalidos\n";
		}
		if (velMaxima.equals("") || velMaxima== null) {
			notificacion+= "La velocidad maxima no puede ser negativa ni nula\n";
		}
		if (transmicion==null || transmicion.equals("")) {
			notificacion+= "Seleccione una transmicion\n";
		}
		if (combustible==null || combustible.equals("")) {
			notificacion+= "Seleccione un tipo de combustible\n";
		}else if (combustible==TipoCombustible.ELECTRICO) {

			if (autonomia==null || autonomia.equals("")) {
					notificacion+= "Autonomia invalida";
			}

			if (tiempoPromedioCarga==null || tiempoPromedioCarga.equals("")) {
				notificacion+= "Tiempo promedio de carga invalido";
			}
		}

		if (estado==null || estado.equals("")) {
			notificacion+= "Seleccione el estado del vehiculo\n";
		}
		if (precio<=0 || (precio+"").equals("") || (precio+"") == null) {
			notificacion+= "El precio debe ser mayor que cero. No puede ser nulo\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;


	}

	private boolean verificarDatosComunes(String  num_pasajeros, String num_Puertas,String num_Bolsas,String cilindraje){
		String notificacion= "";
		if (cilindraje==null || cilindraje.equals("")) {
			notificacion+= "El cilindraje no puede estar vacio\n";
		}

		if ( num_pasajeros==null || num_pasajeros.equals("") ) {
			notificacion+= "La cantidad de pasajeros debe ser mayor que 0\n";
		}

		if (num_Puertas==null || num_Puertas.equals("") ) {
			notificacion+= "La cantidad de puertas debe ser mayor que 0\n";
		}

		if (num_Bolsas==null || num_Bolsas.equals("") ) {
			notificacion+= "Las bolsas de aire deben ser mayores que 0\n";
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}


		return true;
	}

	/**
	 * Este metodo verifica que la informacion ingresada para el tipo de vehiculo sedan sea valida, revisa que los valores que requieran de una cantidad sean valores positivos mayores que cero, tambien llama inicialemente a la funcion de verificarDatos para comprobar que los datos genericos sean correctos
	 * @param marca
	 * @param modelo
	 * @param cambios
	 * @param velMaxima
	 * @param cilindraje
	 * @param transmicion
	 * @param combustible
	 * @param estado
	 * @param precio
	 * @param autonomia
	 * @param tiempoPromedioCarga
	 * @param esEnchufable
	 * @param esHibridoLigero
	 * @param num_pasajeros
	 * @param num_Puertas
	 * @param cap_Maletero
	 * @param aire_Acondicionado
	 * @param cam_Reversa
	 * @param num_Bolsas
	 * @param abs
	 * @param sen_Colision
	 * @param sen_Trafico_Cruzado
	 * @param asistente_Carril
	 * @return
	 */
	private boolean verificarDatosSedan(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonomia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril){


		String notificacion= "";
		if (verificarDatos(marca, modelo, cambios, velMaxima, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga,
				esEnchufable, esHibridoLigero)) {
			if (verificarDatosComunes(num_pasajeros, num_Puertas, num_Bolsas, cilindraje)) {
				if ( cap_Maletero==null || cap_Maletero.equals("") ) {
					notificacion+= "La capacidad del maletero debe de ser mayor que 0\n";
				}
			}
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}
		return true;
	}


    private boolean verificarDatosFactura(String codigo, String fecha, Double totalFactura, String  cedulaCliente, String cantDias) {
    	String notificacion= "";
    	if (codigo==null || codigo.equals(null)) {
    		notificacion += "El código de la transacción es invalido\n";
		}
    	if (fecha==null || fecha.equals(null)) {
    		notificacion += "La fecha de la transacción es invalido\n";
		}
    	if (totalFactura<=0|| (totalFactura+"") == null || (totalFactura+"").equals("")) {
    		notificacion += "El total de la transacción es invalido\n";
		}
    	if (cedulaCliente==null || cedulaCliente.equals(null)) {
    		notificacion += "La cédula del cliente es invalida transacción es invalido\n";
		}
    	if (cantDias==null || cantDias.equals(null)) {
    		notificacion += "La cantidad de Dias de alquiler es invalida\n";
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Transaccion no generada", notificacion, AlertType.WARNING);
			return false;
		}
		return true;
	}


	private static String mezclarPalabra(String palabra) {
	        char[] letras = palabra.toCharArray();
	        Random random = new Random();

	        for (int i = 0; i < letras.length; i++) {
	            int indiceAleatorio = random.nextInt(letras.length);
	            char temp = letras[i];
	            letras[i] = letras[indiceAleatorio];
	            letras[indiceAleatorio] = temp;
	        }

	        return new String(letras);
	    }

	private boolean verificarDatosDeportivo(String marca, String modelo, String cambios, String  velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String num_Bolsas, String num_Caballos_Fuerza, String tiempo_en_100KM){

		String notificacion="";
		if (verificarDatos(marca, modelo, cambios, velMaxima, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero)) {

			if (verificarDatosComunes(num_pasajeros, num_Puertas, num_Bolsas, cilindraje)) {
				if (num_Caballos_Fuerza==null || num_Caballos_Fuerza.equals("") ) {
					notificacion+= "Los caballos de fuerza deben de ser mayores que 0\n";
				}
				if (tiempo_en_100KM==null || tiempo_en_100KM.equals("")) {
					notificacion+= "El tiempo en el que alcanza los 100 k/H debe de ser mayor que 0";
				}
			}
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}


	private boolean  verificarDatosCamioneta(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril,
			boolean esCuatroxCuatro) {

		return verificarDatosSedan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril);
	}

	private boolean verificarDatosPickUp(String marca, String modelo, String cambios, String  velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String num_Bolsas, String capacidadCarga, boolean aire_Acondicionado,
			boolean cam_Reversa, boolean vel_Crucero, boolean abs, boolean esCuatroPorCuatro) {

		String notificacion="";

		if (verificarDatos(marca, modelo, cambios, velMaxima, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero)) {
			if (verificarDatosComunes(num_pasajeros, num_Puertas, num_Bolsas, cilindraje)) {
				if (capacidadCarga==null || capacidadCarga.equals("")  ) {
					notificacion+= "La capacidad de carga debe de ser mayor a 0\n";
				}
			}
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}


	private boolean verificarDatosVan(String marca, String modelo, String cambios, String  velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs) {
		String notificacion="";

		if (verificarDatos(marca, modelo, cambios, velMaxima, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero)) {
			if (verificarDatosComunes(num_pasajeros, num_Puertas, num_Bolsas, cilindraje)) {
				if (cap_Maletero==null || cap_Maletero.equals("")  ) {
					notificacion+= "La capacidad de carga debe de ser mayor a 0\n";
				}
			}
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}



		return true;
	}

	private boolean verificarDatosBus(String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			boolean vel_Crucero, String num_Bolsas, boolean abs, String num_ejes, String num_salidas_emergencia){

		String notificacion="";

		if (verificarDatos(marca, modelo, cambios, velMaxima, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero)) {
			if (verificarDatosComunes(num_pasajeros, num_Puertas, num_Bolsas, cilindraje)) {

				if (num_ejes==null || num_ejes.equals("")) {
					notificacion+= "El número de ejes debe de ser mayor a 0\n";
				}
				if (cap_Maletero==null || cap_Maletero.equals("")  ) {
					notificacion+= "La capacidad del maletero debe de ser mayor a 0\n";
				}
				if (num_salidas_emergencia==null || num_salidas_emergencia.equals("")) {
					notificacion+= "Las salidas de emergencia deben de ser mayores que cero";
				}
			}
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}



		return true;
	}

	private boolean verificarDatosCamion(String marca, String modelo, String cambios, String  velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			boolean aire_Acondicionado, boolean abs, String num_ejes, String tipo_Camion) {
		String notificacion= "";
		if (verificarDatos(marca, modelo, cambios, velMaxima, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero)) {

			if (num_ejes==null || num_ejes.equals("")) {
				notificacion+= "El número de ejes debe de ser mayor a 0\n";
			}

			if (cilindraje== null || cilindraje.equals("")) {
				notificacion+= "El cilindraje no puede estar vacio";
			}
			if (tipo_Camion==null || tipo_Camion.equals("")) {
				notificacion+= "Indique el tipo de camion";
			}
		}
		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Vehiculo no registrado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}



	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}



    @FXML
    void nuevoCliente(ActionEvent event) {
    	limpiarInfoCliente(event);
    }


	@FXML
    void actualizarCliente(ActionEvent event) throws EmpleadoException, ClienteException {
		String nombre= txtNombreCliente.getText();
		String apellido= txtApellidoCliente.getText();
		String cedula= txtCedulaCliente.getText();
		//LocalDate date= datePickerFecha.getValue();
		//String fechaNacimiento= date.toString();

		String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();

		if (clienteSeleccion!=null) {
			if (validarDatos(nombre, apellido, cedula, null)) {
				singleton.actualizarCliente(idEmpleado, nombre, apellido, cedula, null);
				//Se setean los datos que se muestran en la interfaz
				clienteSeleccion.setNombre(nombre);
				clienteSeleccion.setApellido(apellido);
				clienteSeleccion.setIdentificacion(cedula);

				tableViewClientes.refresh();//Refresco la tabla
				mostrarMensaje("Actualizacion cliente", "Cliente actualizado", "Se ha actualizado correctamente el cliente", AlertType.INFORMATION);
			}
		}else {
			mostrarMensaje("Cliente seleccion", "Cliente seleccion", "Ningun cliente ha sido seleccionado", AlertType.WARNING);
		}

    }

    @FXML
    void agregarCliente(ActionEvent event) throws EmpleadoException, ClienteException {
    	String nombre= txtNombreCliente.getText();
    	String apellido= txtApellidoCliente.getText();
    	String cedula= txtCedulaCliente.getText();
    	LocalDate date= datePickerFechaNacimiento.getValue();
    	String fechaNacimiento= date.toString();

    	String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();
    	if (validarDatos(nombre,apellido,cedula,fechaNacimiento)) {
    		crearCliente(idEmpleado,nombre,apellido,cedula,fechaNacimiento);
    		limpiarInfoCliente(event);

		}
    }

    private void crearCliente(String idEmpleado,String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException, ClienteException {

    	try {
        	if (singleton.crearCliente(idEmpleado, nombre, apellido, cedula, fechaNacimiento)) {
        		tableViewClientes.getItems().clear();
        		tableViewClientes.setItems(getListaClientes());
        		mostrarMensaje("Notificacion cliente", "Cliente registrado", "El cliente "+ nombre+ " ha sido registrado con exito", AlertType.INFORMATION);
    		}
		} catch (ClienteException cException) {
			mostrarMensaje("Notificación Cliente", "Cliente no registrado", cException.getMessage(), AlertType.WARNING);
		}
	}

    @FXML
    void limpiarInfoCliente(ActionEvent event) {
    	txtNombreCliente.setText("");
    	txtApellidoCliente.setText("");
    	txtCedulaCliente.setText("");
    	txtCedulaCliente.setEditable(true);
    	datePickerFecha.setEditable(true);
    	datePickerFechaNacimiento.setValue(null);
    	clienteSeleccion=null;
    }

    @FXML
    void eliminarCliente(ActionEvent event) throws EmpleadoException, ClienteException {
    	String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();
    	if (clienteSeleccion!=null) {
    		try {
        		int confirmacion= JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este cliente");
        		if (confirmacion==0) {
        			if (singleton.eliminarCliente(idEmpleado, clienteSeleccion)) {
    					listaClientes.remove(clienteSeleccion);
    					mostrarMensaje("Eliminacion cliente", "Cliente eliminado", "Se ha eliminado correctamente al cliente", AlertType.INFORMATION);
    				}
    			}
			} catch (ClienteException clienteException) {
				mostrarMensaje("Eliminacion cliente", "Cliente no eliminado", clienteException.getMessage(), AlertType.INFORMATION);
			}
		}else {
			mostrarMensaje("Cliente seleccion", "Cliente seleccion", "No ha seleccionado ningun cliente, por lo tanto no lo puede eliminar", AlertType.WARNING);
		}
    }
//----------------------------------------------------VEHICULOS-----------------------------------------------------------------------
    /**
     * Se obtiene el tipo de vehiculo que selecciono del comboBox, esto para habilitar los diferentes campos de texto
     * No se setea ningun comboBox porque no es necesario, ya que de todos los vehiculos se necesita la informacion que dan estos
     * @param event
     */
    @FXML
    void seleccionVehiculo(ActionEvent event) {

    	TipoVehiculo vehiculoSeleccionado= comboBoxTipoVehiculo.getSelectionModel().getSelectedItem();

		txtMarcaVehiculo.setDisable(false);
		txtModeloVehiculo.setDisable(false);
		txtCant_CambioVehiculo.setDisable(false);
		txtCilindraje.setDisable(false);
		txtVelMaxima.setDisable(false);
		txtPrecio.setDisable(false);

    	//Revisa si el vehiculo es una moto para solo habilitar los campos que necesitan
    	if (vehiculoSeleccionado.equals(TipoVehiculo.MOTO)) {
    		//Habilito los campos necesarios

    		//Seteo los campos de texto que no utiliza este tipo de vehiculo
			txtNum_bolsas.setDisable(true);
			txtCap_Maletero.setDisable(true);
			txtNum_bolsas.setDisable(true);
			txtNumPasajeros.setDisable(true);
			txtNumPuertas.setDisable(true);
			txtCaballosFuerza.setDisable(true);
			txt100km.setDisable(true);
			txtCapacidadCarga.setDisable(true);
			txtNumEjes.setDisable(true);
			txtSalidasEmergencia.setDisable(true);
			txtTipoCamion.setDisable(true);
			//Seteo los checkBoox
			checkABS.setDisable(true);
			checkVelCrucer.setDisable(true);
			checkAireAcondicionado.setDisable(true);
			checkCamReversa.setDisable(true);
			checkCuatroPorCuatro.setDisable(true);
			checkAsistenteCarril.setDisable(true);
			checkSensColision.setDisable(true);
			checkSensTrafico.setDisable(true);
		}
    	//Revisa si el vehiculo es un sedan para habilitar sus campos
    	if (vehiculoSeleccionado.equals(TipoVehiculo.SEDAN)) {

    		//Habilito todos los campos que necesita el sedan

    		//Habilito los checkBox que necesito
    		checkAireAcondicionado.setDisable(false);
    		checkCamReversa.setDisable(false);
    		checkVelCrucer.setDisable(false);
    		checkABS.setDisable(false);
    		checkSensColision.setDisable(false);
    		checkSensTrafico.setDisable(false);
    		checkAsistenteCarril.setDisable(false);
    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtCap_Maletero.setDisable(false);
    		txtNum_bolsas.setDisable(false);

    		//inhabilito todos los demas campos
    		txt100km.setDisable(true);
    		txtCaballosFuerza.setDisable(true);
    		txtSalidasEmergencia.setDisable(true);
    		txtTipoCamion.setDisable(true);

    		checkCuatroPorCuatro.setDisable(true);
		}
    	if (vehiculoSeleccionado.equals(TipoVehiculo.DEPORTIVO)) {


    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtNum_bolsas.setDisable(false);
    		txtCaballosFuerza.setDisable(false);
    		txt100km.setDisable(false);

    		//Deshabilito los textField que no necesito
      		txtSalidasEmergencia.setDisable(true);
    		txtTipoCamion.setDisable(true);
    		txtNumEjes.setDisable(true);
    		checkABS.setDisable(true);
			checkVelCrucer.setDisable(true);
			checkAireAcondicionado.setDisable(true);
			checkCamReversa.setDisable(true);
			checkCuatroPorCuatro.setDisable(true);
			checkAsistenteCarril.setDisable(true);
			checkSensColision.setDisable(true);
			checkSensTrafico.setDisable(true);
			checkCuatroPorCuatro.setDisable(true);
		}

    	if (vehiculoSeleccionado.equals(TipoVehiculo.CAMIONETA)) {
    		//Habilito todos los campos que necesita el sedan

    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtCap_Maletero.setDisable(false);
    		txtNum_bolsas.setDisable(false);
    		//Habilito los checkBox que necesito
    		checkAireAcondicionado.setDisable(false);
    		checkCamReversa.setDisable(false);
    		checkVelCrucer.setDisable(false);
    		checkABS.setDisable(false);
    		checkSensColision.setDisable(false);
    		checkSensTrafico.setDisable(false);
    		checkAsistenteCarril.setDisable(false);
    		checkCuatroPorCuatro.setDisable(false);
    		//inhabilito todos los demas campos
    		txt100km.setDisable(true);
    		txtCaballosFuerza.setDisable(true);
    		txtSalidasEmergencia.setDisable(true);
    		txtTipoCamion.setDisable(true);
		}
    	if (vehiculoSeleccionado.equals(TipoVehiculo.PICKUP)) {
    		//TxtField necesarios

    		txtNumPasajeros.setDisable(false);
    		txtNumPuertas.setDisable(false);
    		txtNum_bolsas.setDisable(false);
    		txtCapacidadCarga.setDisable(false);

    		//Cheack box necesarias
    		checkABS.setDisable(false);
    		checkCuatroPorCuatro.setDisable(false);
    		checkAireAcondicionado.setDisable(false);
    		checkCamReversa.setDisable(false);
		    checkVelCrucer.setDisable(false);

    		//txtFields innecesarios
		    txtCaballosFuerza.setDisable(true);
		    txtSalidasEmergencia.setDisable(true);
		    txt100km.setDisable(true);
		    txtCap_Maletero.setDisable(true);
		    txtTipoCamion.setDisable(true);
		    txtNumEjes.setDisable(true);

		    //checkBox innecesarias
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkSensTrafico.setDisable(true);

		}

    	if (vehiculoSeleccionado.equals(TipoVehiculo.VAN)) {
    		//txt necesarios

    	    txtNumPasajeros.setDisable(false);
		    txtNumPuertas.setDisable(false);
		    txtCap_Maletero.setDisable(false);
		    txtNum_bolsas.setDisable(false);
		    //checkbox necesarias
		    checkAireAcondicionado.setDisable(false);
		    checkCamReversa.setDisable(false);
		    checkABS.setDisable(false);

		    //CheckBox innecesarias
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkCuatroPorCuatro.setDisable(true);
		    checkSensTrafico.setDisable(true);
		    checkVelCrucer.setDisable(true);


    	    //txt innecesarios
		    txtCaballosFuerza.setDisable(true);
		    txtSalidasEmergencia.setDisable(true);
		    txt100km.setDisable(true);
		    txtTipoCamion.setDisable(true);
		    txtCapacidadCarga.setDisable(true);
		    txtNumEjes.setDisable(true);

		}
    	if (vehiculoSeleccionado.equals(TipoVehiculo.BUS)) {
    		//Txtfields necesarios
			txtNumPasajeros.setDisable(false);
			txtNumPuertas.setDisable(false);
			txtCap_Maletero.setDisable(false);
			txtNumEjes.setDisable(false);
			txtSalidasEmergencia.setDisable(false);

			//checkBox necesarios
		    checkAireAcondicionado.setDisable(false);
		    checkCamReversa.setDisable(false);
		    checkABS.setDisable(false);
		    checkVelCrucer.setDisable(false);
		    txtNum_bolsas.setDisable(false);;

			//check box innecesarios
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkCuatroPorCuatro.setDisable(true);
		    checkSensTrafico.setDisable(true);


			//txtFields innecesarios
		    txtCaballosFuerza.setDisable(true);
		    txt100km.setDisable(true);
		    txtTipoCamion.setDisable(true);
		    txtNum_bolsas.setDisable(false);;
		    txtCapacidadCarga.setDisable(true);

		}


    	if (vehiculoSeleccionado.equals(TipoVehiculo.CAMION)) {

    		//txtFields necesarios
			txtCapacidadCarga.setDisable(false);
			txtTipoCamion.setDisable(false);
			txtNumEjes.setDisable(false);

			//checkBox neecesarias
			checkAireAcondicionado.setDisable(false);
			checkABS.setDisable(false);
			//txtfields Innecesarios
		    txtCaballosFuerza.setDisable(false);
		    txtSalidasEmergencia.setDisable(false);
		    txt100km.setDisable(true);
		    txtCap_Maletero.setDisable(true);
		    txtNumPasajeros.setDisable(true);
		    txtNum_bolsas.setDisable(false);;
		    txtNumPuertas.setDisable(true);;

		    //checkBox innecesarios
		    checkAsistenteCarril.setDisable(true);
		    checkSensColision.setDisable(true);
		    checkCamReversa.setDisable(true);
		    checkCuatroPorCuatro.setDisable(true);
		    checkSensTrafico.setDisable(true);
		    checkVelCrucer.setDisable(true);

    	}
    }


    /*
     * Se obtiene el tipo de combustible que selecciono del comboBox, esto para habilitar o inhabilitar los diferentes campos de texto
     *
     * */
    @FXML
    void seleccionCombustible(ActionEvent event) {
    	TipoCombustible combustibleSeleccionado= comboBoxCombustible.getSelectionModel().getSelectedItem();

    	if (combustibleSeleccionado.equals(TipoCombustible.DIESEL) || combustibleSeleccionado.equals(TipoCombustible.GASOLINA) ) {

    		//TxtFields no necesarios
    		txtAutonomiaCarga.setDisable(true);
	    	txtPromedioCarga.setDisable(true);
	    	//checkBos no necesaario
	    	chekHibridoLigero.setDisable(true);
	    	checkEnchufable.setDisable(true);
		}

    	if (combustibleSeleccionado.equals(TipoCombustible.ELECTRICO)) {

    		//TxtFields necesarios
    		txtAutonomiaCarga.setDisable(false);
	    	txtPromedioCarga.setDisable(false);
	    	//checkBos no necesaario
	    	chekHibridoLigero.setDisable(true);
	    	checkEnchufable.setDisable(true);
		}

    	if (combustibleSeleccionado.equals(TipoCombustible.HIBRIDO)) {

    		//TxtFields innecesarios
    		txtAutonomiaCarga.setDisable(true);
	    	txtPromedioCarga.setDisable(true);
	    	//Check necesario
	    	checkEnchufable.setDisable(false);

	    	//Si es enchufable significa no se necesita saber si es hibrido ligero o no, en cambio si no es enchufable hay que indicar si es hibrido ligero
	    	   checkEnchufable.selectedProperty().addListener((observable, oldValue, newValue) -> {
	               if (newValue) {
	            	   chekHibridoLigero.setDisable(true);
	               } else {
	            	   chekHibridoLigero.setDisable(false);
	               }
	           });

		}


    }



    @FXML
    void registrarVehiculo(ActionEvent event) throws EmpleadoException, VehiculoException {
    	TipoVehiculo vehiculoSeleccionado= comboBoxTipoVehiculo.getSelectionModel().getSelectedItem();
    	TipoTransmicion transmicion= comboBoxTipoTransmicion.getSelectionModel().getSelectedItem();
    	TipoCombustible combustible= comboBoxCombustible.getSelectionModel().getSelectedItem();
    	TipoEstado estado= comboBoxEstado.getSelectionModel().getSelectedItem();

    	Double precio= Double.parseDouble(txtPrecio.getText());

    	String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();
    	String marca= txtMarcaVehiculo.getText();
    	String modelo= txtModeloVehiculo.getText();
    	String cambios= txtCant_CambioVehiculo.getText();
    	String cilindraje= txtCilindraje.getText();
    	String autonomia= txtAutonomiaCarga.getText();
    	String tiempoPromedioCarga= txtPromedioCarga.getText();
    	String tipo_Camion= txtTipoCamion.getText();
    	String velMaxima= txtVelMaxima.getText();
    	String  num_Caballos_Fuerza= txtCaballosFuerza.getText();
    	String tiempo_en_100KM= txt100km.getText();
    	String num_pasajeros= txtNumPasajeros.getText();
    	String num_Puertas= txtNumPuertas.getText();
    	String  cap_Maletero= txtCap_Maletero.getText();
    	String num_Bolsas= txtNum_bolsas.getText();
    	String capacidadCarga= txtCapacidadCarga.getText();
    	String num_ejes= txtNumEjes.getText();
    	String num_salidas_emergencia= txtSalidasEmergencia.getText();


    	boolean esEnchufable= checkEnchufable.isSelected();
    	boolean esHibridoLigero= chekHibridoLigero.isSelected();
    	boolean aire_Acondicionado= checkAireAcondicionado.isSelected();
    	boolean abs= checkABS.isSelected();
    	boolean sen_Colision= checkSensColision.isSelected();
    	boolean sen_Trafico_Cruzado= checkSensTrafico.isSelected();
    	boolean asistente_Carril= checkAsistenteCarril.isSelected();
    	boolean cam_Reversa= checkCamReversa.isSelected();
    	boolean esCuatroPorCuatro= checkCuatroPorCuatro.isSelected();
    	boolean vel_Crucero= checkVelCrucer.isSelected();


		if (vehiculoSeleccionado!=null) {
	    	switch (vehiculoSeleccionado) {
			case MOTO:
				if (verificarDatos(marca, modelo, cambios, velMaxima, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero)) {
					crearMoto(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero);
					setearCamposDeVehiculo();
				}
				break;
			case SEDAN:
				if (verificarDatosSedan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia,
						 tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa,
						 num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril)) {
					 crearSedan(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril);
					 setearCamposDeVehiculo();
				}
				break;
			case DEPORTIVO:
				if (verificarDatosDeportivo(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM)) {
					crearDeportivo(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM);
					setearCamposDeVehiculo();
				}
				break;
			case CAMIONETA:
				if (verificarDatosCamioneta(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril, esCuatroPorCuatro)) {
					crearCamioneta(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril, esCuatroPorCuatro);
					setearCamposDeVehiculo();
				}
				break;
			case PICKUP:
				if (verificarDatosPickUp(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro)) {
					crearPickUp(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro);
					setearCamposDeVehiculo();
				}
				break;
			case VAN:
				if (verificarDatosVan(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs)) {
					crearVan(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs);
					setearCamposDeVehiculo();
				}
				break;
			case BUS:
				if (verificarDatosBus(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas, abs, num_ejes, num_salidas_emergencia)) {
					crearBus(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas, abs, num_ejes, num_salidas_emergencia);
					setearCamposDeVehiculo();
				}
				break;
			case CAMION:
				if (verificarDatosCamion(marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, aire_Acondicionado, abs, num_ejes, tipo_Camion)) {
					crearCamion(idEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, aire_Acondicionado, abs, num_ejes, tipo_Camion);
					setearCamposDeVehiculo();
				}
				break;
			default:
				mostrarMensaje("Selección vehiculo", "Vehiculo no seleccionado", "Por favor, asegurese de seleccionar un tipo de vehiculo", AlertType.WARNING);
				setearCamposDeVehiculo();
				break;
			}
		}else {
			mostrarMensaje("Selección vehiculo", "Vehiculo no seleccionado", "Por favor, asegurese de seleccionar un tipo de vehiculo", AlertType.WARNING);
		}
    }

    private void setearCamposDeVehiculo() {
    	txtMarcaVehiculo.setText("");
    	txtModeloVehiculo.setText("");
    	txtCant_CambioVehiculo.setText("");
    	txtCilindraje.setText("");
    	txtAutonomiaCarga.setText("");
    	txtPromedioCarga.setText("");
    	txtTipoCamion.setText("");

    	txtPrecio.setText("");
    	txtCaballosFuerza.setText("");
    	txt100km.setText("");
    	txtVelMaxima.setText("");

    	checkEnchufable.setSelected(false);
    	chekHibridoLigero.setSelected(false);
    	checkAireAcondicionado.setSelected(false);
    	checkABS.setSelected(false);
    	checkSensColision.setSelected(false);
    	checkSensTrafico.setSelected(false);
    	checkAsistenteCarril.setSelected(false);
    	checkCamReversa.setSelected(false);
    	checkCuatroPorCuatro.setSelected(false);
    	checkVelCrucer.setSelected(false);

    	txtNumPasajeros.setText("");
    	txtNumPuertas.setText("");
    	txtCap_Maletero.setText("");
    	txtNum_bolsas.setText("");
    	txtCapacidadCarga.setText("");
    	txtNumEjes.setText("");
    	txtSalidasEmergencia.setText("");
	}


    private void crearMoto(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio, String autonomia, String tiempoPromedioCarga,
			boolean esEnchufable, boolean esHibridoLigero) throws EmpleadoException, VehiculoException{

    	if (singleton.crearMoto(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonomia, tiempoPromedioCarga, esEnchufable, esHibridoLigero)) {
    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);

    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}

    }
    private void crearSedan(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril) throws VehiculoException, EmpleadoException{

    	if (singleton.crearSedan(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril)) {

    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);

    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}

    }
    private void crearDeportivo(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String num_Bolsas, String num_Caballos_Fuerza, String tiempo_en_100KM) throws EmpleadoException, VehiculoException{

    	if (singleton.crearDeportivo(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, num_Caballos_Fuerza, tiempo_en_100KM)) {
    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);

    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}

    }
    private void crearCamioneta(String identificacionEmpleado,String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String  num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs, boolean sen_Colision, boolean sen_Trafico_Cruzado, boolean asistente_Carril,
			boolean esCuatroxCuatro) throws VehiculoException, EmpleadoException{

    	if (singleton.crearCamioneta(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs, sen_Colision, sen_Trafico_Cruzado, asistente_Carril, esCuatroxCuatro)) {

    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);

    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}


    }
    private void crearPickUp(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String num_Bolsas, String capacidadCarga, boolean aire_Acondicionado,
			boolean cam_Reversa, boolean vel_Crucero, boolean abs, boolean esCuatroPorCuatro) throws EmpleadoException, VehiculoException{

    	if (singleton.crearPickUp(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, num_Bolsas, capacidadCarga, aire_Acondicionado, cam_Reversa, vel_Crucero, abs, esCuatroPorCuatro)) {

    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);
    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}

    }
    private void crearVan(String identificacionEmpleado, String marca, String modelo, String cambios, String  velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			String num_Bolsas, boolean abs) throws EmpleadoException, VehiculoException{

    	if (singleton.crearVan(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, num_Bolsas, abs)) {
    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);

    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);


    	}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}

    }
    private void crearBus(String identificacionEmpleado, String marca, String modelo, String cambios, String  velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			String num_pasajeros, String num_Puertas, String cap_Maletero, boolean aire_Acondicionado, boolean cam_Reversa,
			boolean vel_Crucero, String num_Bolsas, boolean abs, String num_ejes, String num_salidas_emergencia) throws EmpleadoException, VehiculoException {
    	if (singleton.crearBus(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, num_pasajeros, num_Puertas, cap_Maletero, aire_Acondicionado, cam_Reversa, vel_Crucero, num_Bolsas, abs, num_ejes, num_salidas_emergencia)) {

    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);
    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);
    	}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}

	}
    private void crearCamion(String identificacionEmpleado, String marca, String modelo, String cambios, String velMaxima, String cilindraje,
			TipoTransmicion transmicion, TipoCombustible combustible, TipoEstado estado, Double precio,
			String autonimia, String tiempoPromedioCarga, boolean esEnchufable, boolean esHibridoLigero,
			boolean aire_Acondicionado, boolean abs, String  num_ejes, String tipo_Camion) throws EmpleadoException, VehiculoException {
    	if (singleton.crearCamion(identificacionEmpleado, marca, modelo, cambios, velMaxima, cilindraje, transmicion, combustible, estado, precio, autonimia, tiempoPromedioCarga, esEnchufable, esHibridoLigero, aire_Acondicionado, abs, num_ejes, tipo_Camion)) {

    		tableViewVehiculos.getItems().clear();
    		tableViewVehiculos.setItems(getListaVehiculos());

    		comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.COMPRA);
    		comboBoxTipoTransaccion.setDisable(true);

    		datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		    txtCodigo.setText(mezclarPalabra(marca+ modelo));
			txtCodigo.setEditable(false);

			txtTotal.setText(precio+"");
			txtTotal.setEditable(false);

			txtCedulaTransaccion.setDisable(false);

    		mostrarMensaje("Notificación vehiculo", "Vehiculo registrado", "El vehiculo ha sido registrado con exito, Dirijase a la pestaña de transacciones para registrar la compra", AlertType.INFORMATION);

    	}else {
			mostrarMensaje("Notificación vehiculo", "Vehiculo no registrado", "El vehiculo no se ha podido registrar", AlertType.INFORMATION);
		}

	}
    @FXML

    void venderAuto(ActionEvent event) throws VehiculoException {
    	if (vehiculoSeleccion==null) {

    		mostrarMensaje("Vehiculo Selección", "Selección de vehiculo", "Si quiere vender un vehiculo asegurese de seleccionarlo primero", AlertType.WARNING);

		}else {
			mostrarMensaje("Vehiculo vendido", "Venta de vehiculo", "Dirijase a la pestaña de transacciones para terminar el registro de la venta", AlertType.INFORMATION);
			singleton.eliminarVehiculo(vehiculoSeleccion);
			listaVehiculos.remove(vehiculoSeleccion);
			txtDiasAlquiler.setEditable(false);

			//Seteo los campos con la informacion que se puede obtener directamente, y hago que estos no se puedan editar, asi se evitan imprevistos
			txtTotal.setText(vehiculoSeleccion.getPrecio()+"");
			txtTotal.setEditable(false);

			txtCodigo.setText(mezclarPalabra(vehiculoSeleccion.getMarca()+ vehiculoSeleccion.getModelo()));
			txtCodigo.setEditable(false);

		    comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.VENTA);
		    comboBoxTipoTransaccion.setDisable(true);


		    datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);
		}
    }
    /*
     * moto: 10k
     * Sedan: 20k
     * deportivo: 35k
     * camioneta: 40k
     * pickUp: 40k
     * van: 40k
     * bus: 50k
     * camion: 50k
     *
     * */

    @FXML
    void alquilarAuto(ActionEvent event) {
    	if (vehiculoSeleccion==null) {
    		mostrarMensaje("Vehiculo seleccion", "Seleccion de vehiculo", "Si quiere alquilar un vehiculo asegurese de alquilarlo primero", AlertType.WARNING);
    	}else {
		    int diasAlquiler= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de dias del alquiler"));
    		mostrarMensaje("Vehiculo alquilado", "Vehiculo alquilado", "Dirijase a la pestaña de transaccion para terminar el registro del alquiler", AlertType.INFORMATION);


    		TipoVehiculo tipoVehiculo= vehiculoSeleccion.getTipoVehiculo();


    		txtCodigo.setText(mezclarPalabra(vehiculoSeleccion.getMarca()+ vehiculoSeleccion.getModelo()));
			txtCodigo.setEditable(false);

		    comboBoxTipoTransaccion.getSelectionModel().select(TipoTransaccion.ALQUILER);
		    comboBoxTipoTransaccion.setDisable(true);


		    datePickerFecha.setValue(LocalDate.now());
		    datePickerFecha.setDisable(true);

		   // int diasAlquiler= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de dias del alquiler"));


			txtDiasAlquiler.setDisable(false);
			txtDiasAlquiler.setText(diasAlquiler+"");
			//int diasAlquiler= Integer.parseInt(txtDiasAlquiler.getText());
			double total=0;

			switch (tipoVehiculo) {
				case MOTO:
					total= 10*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				case SEDAN:
					total= 20*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				case DEPORTIVO:
					total= 35*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				case CAMIONETA:
					total= 40*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				case PICKUP:
					total= 40*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				case VAN:
					total= 40*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				case BUS:
					total= 50*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				case CAMION:
					total= 50*diasAlquiler;
					txtTotal.setText(total+"");
					txtTotal.setEditable(false);
					break;
				default:
					total= 10*diasAlquiler;
					txtTotal.setEditable(false);
					break;
			}

		}

    }


    @FXML
    void eliminarAuto(ActionEvent event) throws VehiculoException {
	   	if (vehiculoSeleccion!=null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setTitle("Confirmación");
				alert.setContentText("¿Estas seguro de confirmar la acción?");
				Optional<ButtonType> action = alert.showAndWait();
				if (action.get() == ButtonType.OK) {
					if (singleton.eliminarVehiculo(vehiculoSeleccion)) {
						listaVehiculos.remove(vehiculoSeleccion);
						mostrarMensaje("Eliminar vehiculo", "Vehiculo eliminado", "Se ha eliminado correctamente el vehiculo", AlertType.INFORMATION);
					}else {
						mostrarMensaje("Eliminar vehiculo", "Vehiculo no eliminado", "No se ha podido eliminar correctamente el vehiculo", AlertType.WARNING);
					}
				}
			}else{
				mostrarMensaje("Selección vehiculo", "Seleccione un vehiculo", "Seleccione un vehiculo para eliminarlo", AlertType.WARNING);
			}
   }

//---------------------------------------------------------------TRANSACCIONES---------------------------------------------------------------------{

    @FXML
    void registrarTransaccion(ActionEvent event) throws EmpleadoException, TransaccionException, ClienteException {
    	TipoTransaccion tipoTransaccion = comboBoxTipoTransaccion.getSelectionModel().getSelectedItem();
    	String codigo= txtCodigo.getText();
    	LocalDate date= datePickerFecha.getValue();
    	String fecha= date.toString();
    	Double totalFactura= Double.parseDouble(txtTotal.getText());
    	String cedulaTransaccion= txtCedulaTransaccion.getText();
    	String cantDias= txtDiasAlquiler.getText();
    	String idEmpleado= loginEmpleadoController.getTxtIdentificacionEmpleadp().getText();

    	if (verificarDatosFactura(codigo, fecha, totalFactura, cedulaTransaccion, cantDias)) {
    		crearFacturaVenta(idEmpleado, tipoTransaccion, codigo, fecha, totalFactura, cedulaTransaccion, cantDias);
    		nuevaTransaccion(event);
		}
    }

    private void crearFacturaVenta(String idEmpleado, TipoTransaccion tipoTransaccion, String codigo, String fecha, Double total, String cedulaCliente, String cantDias) throws EmpleadoException, TransaccionException, ClienteException{

    	if (singleton.crearTransaccion(idEmpleado, tipoTransaccion, fecha, total, codigo, cantDias, cedulaCliente)) {
    		tableViewTransaccion.getItems().clear();
    		tableViewTransaccion.setItems(getListaTransacciones());
    		mostrarMensaje("Transacción realizada", "Transaccion terminada", "La transacción fue finalizada con exito", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Transacción no realizada", "Transaccion interrumpida", "La transacción no pudo ser finalizada", AlertType.WARNING);
		}
    }


    @FXML
    void nuevaTransaccion(ActionEvent event) {
    	txtCodigo.setText("");
    	datePickerFecha.setValue(null);
    	txtTotal.setText("");
    	txtDiasAlquiler.setText("");
    	txtCedulaTransaccion.setText("");
    }



    @FXML
    void aniadirImagen(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Seleccionar imagen");
         fileChooser.getExtensionFilters().addAll(
                 new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg", "*.jpeg", "*.png"),
                 new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
         );
         // Mostrar el cuadro de diálogo para seleccionar un archivo
         Stage stage = (Stage) btnAniadirImagen.getScene().getWindow();
         java.io.File selectedFile = fileChooser.showOpenDialog(stage);
         if (selectedFile != null) {
             // Aquí puedes realizar alguna acción con el archivo seleccionado
             System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
         }
     }


    @FXML
    void mostrarVentanPrincipal(ActionEvent event) {
    	this.stage.close();
    	 loginEmpleadoController.show();
    }

    @FXML
    void initialize() {

    }
	public void init(Stage stage, LoginEmpleadoController loginEmpleadoController) {
		this.loginEmpleadoController = loginEmpleadoController;
		//this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;

	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}

/**
 * Se inicializan los valores de los combo box
 * Se organizan los valores de las tablas
 *
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.gc();

		/*Añado los itemes al comboBox de vehiculosy tambien edito el texto para cuando no hay nada seleccionadp*/
		comboBoxTipoVehiculo.getItems().addAll(TipoVehiculo.MOTO,TipoVehiculo.SEDAN,TipoVehiculo.PICKUP,
				TipoVehiculo.DEPORTIVO,TipoVehiculo.CAMIONETA,TipoVehiculo.BUS,TipoVehiculo.CAMION,TipoVehiculo.VAN);
		comboBoxTipoVehiculo.setPromptText("Vehiculos");

		/*Añado los itemes al comboBox de transmiciones y tambien edito el texto para cuando no hay nada seleccionadp*/
		comboBoxTipoTransmicion.getItems().addAll(TipoTransmicion.AUTOMATICA,TipoTransmicion.MANUAL);
		comboBoxTipoTransmicion.setPromptText("Transmición del vehiculo");

		/*Añado los itemes al comboBox de estado del vehiculo y tambien edito el texto para cuando no hay nada seleccionadp*/
		comboBoxEstado.getItems().addAll(TipoEstado.NUEVO, TipoEstado.USADO);
		comboBoxEstado.setPromptText("Estado del vehiculo");
		/*Añado los itemes al comboBox de combustibles y tambien edito el texto para cuando no hay nada seleccionadp*/
		comboBoxCombustible.getItems().addAll(TipoCombustible.DIESEL,TipoCombustible.GASOLINA, TipoCombustible.ELECTRICO,TipoCombustible.HIBRIDO);
		comboBoxCombustible.setPromptText("Tipo de combustible");

		/*Añado los itemes al comboBox de transacciones y tambien edito el texto para cuando no hay nada seleccionadp*/
		comboBoxTipoTransaccion.getItems().addAll(TipoTransaccion.VENTA, TipoTransaccion.ALQUILER);
		comboBoxTipoTransaccion.setPromptText("Tipo de transacción");

		//Inicializar las columnas de la tableView de clientes
		this.columNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columApellidosCliente.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
		this.columFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaDeNacimiento"));
		/*Se usa para poder seleccionar un item de la tabla*/
		tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				clienteSeleccion= newSelection;
				txtCedulaCliente.setText(clienteSeleccion.getIdentificacion());
				txtCedulaCliente.setEditable(false);

				datePickerFechaNacimiento.setEditable(false);
			}
		});

		//Inicializar las columnas de la tableview de vehiculos
		this.columnVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
		this.columMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		this.columModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		this.columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		this.columEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columTipoCombustible.setCellValueFactory(new PropertyValueFactory<>("combustible"));

		tableViewVehiculos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				vehiculoSeleccion= newSelection;
			}
		});

		//Inicializar las columnas de las facturas de la tableView de transacciones

		this.columTipoTransaccion.setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion"));
		this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columCedulaFactura.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));
		this.columFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		this.columTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

		tableViewTransaccion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				transaccionSeleccion= newSelection;
				//mostrarInformacionCliente();
			}
		});


		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0,
                c -> c.getControlNewText().matches("\\d*") ? c : null);
        txtPrecio.setTextFormatter(textFormatter);


	}

}
