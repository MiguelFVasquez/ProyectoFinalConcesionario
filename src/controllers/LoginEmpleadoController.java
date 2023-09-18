package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Concesionario;
import application.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginEmpleadoController implements Initializable {
	Singleton singleton = Singleton.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtIdentificacionEmpleadp;


    @FXML
    private Button btnVolver;
    @FXML
    private Button txtCerrarLogin;
    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnRegistrar;

	private Aplicacion aplicacion;

	private Stage stage;

	private PrincipalController principioController;

	private Concesionario concesionario;

	private boolean validarDatos(String identificacion) {
		String notificacion = "";

		if (identificacion == null || identificacion.equals(""))
			notificacion += "El campo de identificacion no puede ser nulo\n";

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Empleado no encontrado", notificacion, AlertType.WARNING);
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

	/**
	 * Metodo para verificar si un empleado si existe, esto se utilzia para que si existe abra la ventana del empleado, en caso de que sea falso no se abre esta ventana y dira
	 *  que no esta registrado
	 * @param identificacion
	 * @return
	 */
	public boolean verificaQueExisteEmpleado(String identificacion) {
		boolean existe= false;
		if (validarDatos(identificacion)) {
			if (singleton.verificarEmpleado(identificacion)) {
				existe= true;
			}
		}

		return existe;
	}

	@FXML
    void showVentanaEmpleado(ActionEvent event) throws IOException {
		String identificacion= txtIdentificacionEmpleadp.getText();
		if (verificaQueExisteEmpleado(identificacion)) {
     		FXMLLoader loader= new FXMLLoader();
    		loader.setLocation(Aplicacion.class.getResource("../Views/EmpleadoView.fxml"));
    		AnchorPane anchorPane= (AnchorPane)loader.load();
    		EmpleadoController empleadoController = loader.getController();
    		empleadoController.setAplicacion(aplicacion);
    		Scene scene= new Scene(anchorPane);
    		Stage stage = new Stage();
    		stage.setTitle("Perfil empleado");
    		stage.setScene(scene);
    		empleadoController.init(stage, this);
    		stage.show();
    		this.stage.close();
		}else {
			mostrarMensaje("Inicio de sesion", "Empleado inexistente", "El empleado con la identificacion proporcionada no existe, asegurese de que la identificacion es correcta",
					AlertType.WARNING);
		}


    }

    @FXML
    void cerrarLogin(ActionEvent event) {
    	this.stage.close();
    }
    /**
     * Si el empleado esta intentando ingresar y no esta registrado, se da la opcion de que un admin lo registre, por lo tanto es necesrio saber que admin es, asi que se abre el
     * login del admin para que ingrese y lo registre
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void abrirLoginAdmin(ActionEvent event) throws IOException {
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(Aplicacion.class.getResource("../Views/LoginView.fxml"));
		AnchorPane anchorPane= (AnchorPane)loader.load();
		LoginController loginController = loader.getController();
		loginController.setAplicacion(aplicacion);
		Scene scene= new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Login Administrador");
		loginController.init(stage, this);
		stage.show();
		this.stage.close();
    }

    @FXML
    void showVentanaPrincipal(ActionEvent event) {
    	this.stage.close();
    	principioController.show();
    }

    public void show() {
 		stage.show();
 	}

	public TextField getTxtIdentificacionEmpleadp() {
		return txtIdentificacionEmpleadp;
	}

	public void setTxtIdentificacionEmpleadp(TextField txtIdentificacionEmpleadp) {
		this.txtIdentificacionEmpleadp = txtIdentificacionEmpleadp;
	}


	public void init(Stage stage, PrincipalController principalController) {
		this.principioController = principalController;
		//this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;
	}

    public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}

    @FXML
    void initialize() {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
