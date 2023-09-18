package controllers;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.AdministradorException;
import Exceptions.EmpleadoException;
import Model.Administrador;
import Model.Concesionario;
import Model.Empleado;
import Model.TipoTransaccion;
import Model.Transaccion;
import application.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdministradorController implements Initializable{

	Singleton singleton = Singleton.getInstance();

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    //Tablew view empleados
    @FXML
    private TableView<Empleado> tableViewEmpleado;

	@FXML
    private TableColumn<Empleado, String> columNombre;

	@FXML
    private TableColumn<Empleado, String> columApellidos;

	@FXML
    private TableColumn<Empleado, String> columCedula;
//table view reportes
	@FXML
    private TableView<Transaccion> tableViewListaReportes;
    @FXML
    private TableColumn<Transaccion, Double> columnTotal;

    @FXML
    private TableColumn<Transaccion, String> columnFecha;

    @FXML
    private TableColumn<Transaccion, TipoTransaccion > columnTipoTransaccion;

    @FXML
    private DatePicker datePickerFechaFinal;


    @FXML
    private DatePicker datePickerFechaInicial;


    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private TextField txtApellidoEmpleado;

    @FXML
    private TextField txtCedulaEmpleado;

    @FXML
    private Button btnLimpiarLista;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnBuscarReportes;

    @FXML
    private Button btnAgregarEmpleado;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;

	private PrincipalController principioController;

	private LoginController loginController;

	private Concesionario concesionario;

	private Administrador administrador;

	private Stage stage;

	private Aplicacion aplicacion;

	private Empleado empleadoSeleccion;

    //Crea las variables de fechas

    private String fechaInicial;
    private String fechaFinal;

	//Crea la lista de empleados que está en el Table View
	ObservableList<Empleado> listaEmpleados= FXCollections.observableArrayList();

    //Creo la lista de transacciones que se van a encontrar en la table view cuando se indique una fecha
    ObservableList<Transaccion> listaReportes = FXCollections.observableArrayList();


	/**
	 * capturo las credenciales del admin para saber cual es, y asi poder acceder al metodo que obtiene al administrador, y asi en la tableview saldra la lista de
	 *  empleados que ese admin maneja
	 *
	 * @return
	 */

	private ObservableList<Empleado> getListaEmpleados() {
		String idAdminString= loginController.getTxtIdentificacionLogin().getText();
		String contrasenia= loginController.getTxtContraseniaLogin().getText();

		administrador= singleton.obtenerAdministrador(idAdminString, contrasenia);

		listaEmpleados.addAll(administrador.getListaEmpleados());
		return listaEmpleados;
	}
    private ObservableList<Transaccion> getDatosTransacciones(String fechaIncial, String fechaFinal) {
    	listaReportes.addAll(singleton.getListaDatosReportes(fechaInicial, fechaFinal));
		return listaReportes;
	}

	private boolean validarDatos(String nombre, String apellido, String cedula) {
		String notificacion = "";

		if (nombre == null || nombre.equals(""))
			notificacion += "Nombre(s) invalido\n";

		if (apellido == null || apellido.equals(""))
			notificacion += "Apellido(s) inv�lido\n";

		if (cedula == null || cedula.equals("")) {
			notificacion += "N�mero de cedula invalido\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificaci�n", "Empleado no creado", notificacion, AlertType.WARNING);
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
    void actualizarEmpleado(ActionEvent event) throws AdministradorException, EmpleadoException {
		String nombre= txtNombreEmpleado.getText();
		String apellido= txtApellidoEmpleado.getText();
		String cedula= txtCedulaEmpleado.getText();

		String idAdmin= loginController.getTxtIdentificacionLogin().getText();

		if (empleadoSeleccion!=null) {
			if (validarDatos(nombre, apellido, cedula)) {
				singleton.actualizarEmpleado(idAdmin, nombre, apellido, cedula);
				//Se actualizan los valores que se muestran en la interfaz
				empleadoSeleccion.setNombre(nombre);
				empleadoSeleccion.setApellido(apellido);
				empleadoSeleccion.setIdentificacion(cedula);

				tableViewEmpleado.refresh();//Refresco la tabla
				mostrarMensaje("Actualizacion empleado", "Empleado actualizado", "Se ha actualizado correctamente el empleado", AlertType.INFORMATION);

			}
		}else {
			mostrarMensaje("Empleado seleccion", "Empleado seleccion", "No ha seleccioando ningun empleado para actualizar", AlertType.WARNING);
		}


    }

    @FXML
    void agregarEmpleado(ActionEvent event) throws EmpleadoException, AdministradorException {
		//Datos del empleado neuvo
    	String nombre = txtNombreEmpleado.getText();
    	String apellido= txtApellidoEmpleado.getText();
    	String identificacion= txtCedulaEmpleado.getText();
    	//Credenciales del admin
    	String identificacionAdmin= loginController.getTxtIdentificacionLogin().getText();
    	String contraseniaAdmin= loginController.getTxtContraseniaLogin().getText();

    	if (validarDatos(nombre, apellido, identificacion)) {
    		crearEmpleado(identificacionAdmin,contraseniaAdmin,nombre, apellido,identificacion);

    		txtNombreEmpleado.setText("");
    		txtApellidoEmpleado.setText("");
    		txtCedulaEmpleado.setText("");
		}
    }

    private void crearEmpleado(String identificacionAdmin,String contraseniaAdmin,String nombre, String apellido, String identificacion) throws EmpleadoException, AdministradorException {
    	try {
    		if (singleton.crearEmpleado(identificacionAdmin, contraseniaAdmin,nombre, apellido, identificacion)) {
    			tableViewEmpleado.getItems().clear();
    			tableViewEmpleado.setItems(getListaEmpleados());
    			mostrarMensaje("Notificacion empleado", "Empleado creado", "El empleado ha sido creado con exito", AlertType.INFORMATION);
    		}
		} catch (EmpleadoException empleadoException) {
			mostrarMensaje("Notificación Empleado", "Empleado no registrado", empleadoException.getMessage(), AlertType.INFORMATION);
		}
    }

    @FXML
    void limpiar(ActionEvent event) {
    	txtNombreEmpleado.setText("");
    	txtApellidoEmpleado.setText("");
    	txtCedulaEmpleado.setText("");
    	empleadoSeleccion=null;
    }
    /**
     * Se obtiene la identificacion del admin desde el login
     * Se verifica que el elemento seleccionado de la tabla no sea nulo
     * Sale un mensaje para verificar la decision de eliminar
     * Se llama al metodo que hay en aplicacion y si este retorna true, entonces elimina al empleado y lo quita de la tabla
     *
     * @param event
     * @throws AdministradorException
     * @throws EmpleadoException
     */
    @FXML
    void eliminarEmpleado(ActionEvent event) throws AdministradorException, EmpleadoException {
    	String identificacionAdmin= loginController.getTxtIdentificacionLogin().getText();
    	if (empleadoSeleccion!=null) {
    		try {
        		int confirmacion= JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este empleado?");
        		if (confirmacion==0) {
        			if (singleton.eliminarEmpleado(identificacionAdmin, empleadoSeleccion)) {
        				listaEmpleados.remove(empleadoSeleccion);
        				mostrarMensaje("Eliminacion empleado", "Empleado eliminado", "Se ha eliminado correctamente el empleado", AlertType.INFORMATION);
    				}
    			}
			} catch (EmpleadoException empleadoException) {
				mostrarMensaje("Eliminacion empleado", "Empleado no eliminado", empleadoException.getMessage(), AlertType.INFORMATION);
			}
		}else {
			mostrarMensaje("Empleado seleccion", "Empleado seleccion", "No ha seleccionado ningun empleado para eliminar", AlertType.INFORMATION);
		}
    }

    @FXML
    void buscarReportes(ActionEvent event) throws ParseException {
    	buscarReportes();
    }

    /*
     * Método para generar la lista de Reportes de x fecha a y fecha proporcionada.
     * Se verifica que no sea nula la fecha y que la fecha inicial no sea mayor a la fecha final
     * -> Se genera lista de reportes con todos los reportes que cumplan los requisitos
     */


    private void buscarReportes() {
    	if (fechaInicial != null && fechaFinal != null) {
    		try {
				if (singleton.validarFechas(fechaInicial, fechaFinal)) {
					tableViewListaReportes.getItems().clear();
					tableViewListaReportes.setItems(getDatosTransacciones(fechaInicial, fechaFinal));
				} else {
					mostrarMensaje("Notificación Fechas", "Fechas invalidas", "La fecha final no puede ser menor a la fecha inicial", AlertType.WARNING);
				}
			} catch (ParseException e) {
				mostrarMensaje("Notificación Fechas", "Fechas invalidas", "Ha ocurrido un problema con las fechas ingresadas", AlertType.WARNING);
			}
    	} else {
    		mostrarMensaje("Notificación fechas", "Fechas no seleccionadas", "Por favor seleccione una fecha", AlertType.WARNING);
    	}
    }


	@FXML
    void limpiarLista(ActionEvent event) {
		listaReportes.clear();
    }

    @FXML
    void showVentanaPrincipal(ActionEvent event) {
    	this.stage.close();
     	loginController.getTxtContraseniaLogin().setText("");
    	loginController.getTxtIdentificacionLogin().setText("");
    	loginController.show();
    }


    @FXML
    void initialize() {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columCedula.setCellValueFactory(new PropertyValueFactory<>("identificacion"));

		this.columnTipoTransaccion.setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion"));
		this.columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		this.columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


		tableViewEmpleado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				empleadoSeleccion= newSelection;
				//mostrarInformacionEmpleado();
			}
		});

		//Para poder obtener la fecha inicial
		datePickerFechaInicial.setOnAction(event -> {
			//Obtener la fecha seleccionada como un objeto LocalDate
			LocalDate date = datePickerFechaInicial.getValue();
		    // Crear un objeto DateTimeFormatter con el formato deseado
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Convertir la fecha a una cadena con el formato deseado
            fechaInicial = date.format(formato);
		});
		//Para poder obtener la fecha final
		datePickerFechaFinal.setOnAction(event -> {
			//Obtener la fecha seleccionada como un objeto LocalDate
			LocalDate date = datePickerFechaFinal.getValue();
		    // Crear un objeto DateTimeFormatter con el formato deseado
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Convertir la fecha a una cadena con el formato deseado
            fechaFinal = date.format(formato);
		});
	}


	public void init(Stage stage, LoginController loginController) {
		this.loginController = loginController;
		//this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;

	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}

}
