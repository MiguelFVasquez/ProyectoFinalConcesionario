package controllers;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.AdministradorException;
import Model.Administrador;
import Model.Concesionario;
import application.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CrearAdminController implements Initializable{
	Singleton singleton = Singleton.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Administrador> tableViewAdministradores;
    @FXML
    private TableColumn<Administrador, String> columApellidosAdmin;

    @FXML
    private TableColumn<Administrador, String> columNombreAdmin;

    @FXML
    private TableColumn<Administrador, String> columCedulaAdmin;

    @FXML
    private TextField txtCedulaAdministrador;

    @FXML
    private TextField txtNombreAdministrador;

    @FXML
    private TextField txtApellidoAdministrador;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEliminar;


    @FXML
    private Button btnActualizar;

	private LoginController loginController;

	private Aplicacion aplicacion;

	private Concesionario concesionario;

	private Stage stage;

	private Administrador administradorSelection;//Se crea para obtenr el elemento seleccionado de la tabla de admins


	ObservableList<Administrador> listaAdministradores= FXCollections.observableArrayList();//Crea la lista que se insertara en la tabla

	private ObservableList<Administrador> getListaAdministradores() {
		listaAdministradores.addAll(concesionario.getListaAdministradores());
		return listaAdministradores;
	}
//-------------------Metodos funcionales, o sea que son usaddos en la mayoria de los demas metodos----------------------------------------

	private boolean validarDatos(String nombre, String apellido, String cedula, String contrasenia) {
		String notificacion = "";

		if (nombre == null || nombre.equals(""))
			notificacion += "Nombre invalido\n";

		if (apellido == null || apellido.equals(""))
			notificacion += "Apellido(s) inválido\n";

		if (cedula == null || cedula.equals("")) {
			notificacion += "Número de cedula invalido\n";
		}

		if(contrasenia == null || contrasenia.equals(""))
			notificacion+= "Contraseña invalida\n";

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Administrador no creado", notificacion, AlertType.WARNING);
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
    void nuevoAdmin(ActionEvent event) {

    }

    @FXML
    void actualizarAdmin(ActionEvent event) throws AdministradorException {
    	String nombre= txtNombreAdministrador.getText();
    	String apellido= txtApellidoAdministrador.getText();
    	String cedula= txtCedulaAdministrador.getText();
    	String contrasenia= txtContrasenia.getText();
    	if (administradorSelection!=null) {
			if (validarDatos(nombre, apellido, cedula, contrasenia)) {
				singleton.actualizarAdmin(nombre, apellido, cedula, contrasenia);

				//Se actualiza la interfaz
				administradorSelection.setNombre(nombre);
				administradorSelection.setApellido(apellido);
				administradorSelection.setIdentificacion(cedula);
				administradorSelection.setCredenciales(contrasenia);

				tableViewAdministradores.refresh();//Refresco tabla
				mostrarMensaje("Actualizacion administracion", "Administrador actualizado", "Se ha actualizado correctamente el cliente", AlertType.INFORMATION);
			}
		}else {
			mostrarMensaje("Administracion seleccion", "Administrador seleccion", "No ha seleccionado ningun administrador para actualizarlo", AlertType.WARNING);
		}
    }

    @FXML
    void agregarAdmin(ActionEvent event) throws AdministradorException {
    	String nombre= txtNombreAdministrador.getText();
    	String apellido= txtApellidoAdministrador.getText();
    	String cedula= txtCedulaAdministrador.getText();
    	String contrasenia= txtContrasenia.getText();

    	if (validarDatos(nombre, apellido, cedula, contrasenia)) {
    		crearAdmin(nombre, apellido, cedula, contrasenia);
    		txtNombreAdministrador.setText("");
    		txtApellidoAdministrador.setText("");
    		txtCedulaAdministrador.setText("");
    		txtContrasenia.setText("");

		}
    }


    public void crearAdmin(String nombre, String apellido, String cedula, String contrasenia) throws AdministradorException{
    	if (singleton.crearAdministrador(nombre, apellido, cedula, contrasenia)) {
    		tableViewAdministradores.getItems().clear();
    		tableViewAdministradores.setItems(getListaAdministradores());
    		mostrarMensaje("Notificacion Administrador", "Administrador creado", "Administrador creado con exito", AlertType.INFORMATION);
		}else {
			mostrarMensaje("Notificacion Administrador", "Administrador no creado", "El administrador no pudo ser creado", AlertType.INFORMATION);
		}

    }

    @FXML
    void eliminarAdmin(ActionEvent event) throws AdministradorException {
    	if (administradorSelection!=null) {
    		int confirmacion= JOptionPane.showConfirmDialog(null, "Seguro que desea borrar a este administrador?");
    		if (confirmacion==0) {
				if (singleton.eliminarAdministrador(administradorSelection)) {
					listaAdministradores.remove(administradorSelection);
					mostrarMensaje("Eliminacion administrador", "Administrador eliminado", "Se ha eliminado correctamente el administrador", AlertType.CONFIRMATION);
				}else {
					mostrarMensaje("Eliminacion administrador", "Administrador no eliminado", "No ha sido posible eliminar el administrador", AlertType.INFORMATION);
				}
			}
		}else {
			mostrarMensaje("Administrador seleccion", "Administrador seleccion", "No ha seleccionado ningun administrador", AlertType.WARNING);
		}
    }

    @FXML
    void limpiarInfoAdmin(ActionEvent event) {
    	txtNombreAdministrador.setText("");
    	txtApellidoAdministrador.setText("");
    	txtCedulaAdministrador.setText("");
    	txtContrasenia.setText("");
    }

    @FXML
    void mostrarVentanPrincipal(ActionEvent event) {
    	this.stage.close();
    	loginController.getTxtContraseniaLogin().setText("");
    	loginController.getTxtIdentificacionLogin().setText("");
    	loginController.show();



    }

    @FXML
    void initialize() {

    }
	public void init(Stage stage, LoginController loginController) {
		this.loginController = loginController;
		//this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;

	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.columNombreAdmin.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columApellidosAdmin.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columCedulaAdmin.setCellValueFactory(new PropertyValueFactory<>("identificacion"));

		tableViewAdministradores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				administradorSelection= newSelection;
				//mostrarInformacionAdmin();
			}
		});
	}
}
