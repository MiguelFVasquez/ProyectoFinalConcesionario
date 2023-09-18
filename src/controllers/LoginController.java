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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	Singleton singleton = Singleton.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtContraseniaLogin;

    @FXML
    private TextField txtIdentificacionLogin;

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCorreo;

    @FXML
    private Button txtCerrarLogin;

	private PrincipalController principioController;
	private LoginEmpleadoController loginEmpleadoController;

	private Aplicacion aplicacion;

	private Concesionario concesionario;

	private Stage stage;


	private boolean validarDatos(String identificacion, String contrasenia) {
		String notificacion = "";

		if (identificacion == null || identificacion.equals(""))
			notificacion += "El campo de identificacion no puede ser nulo\n";

		if (contrasenia == null || contrasenia.equals(""))
			notificacion += "El campo de la contraseña no puede ser nulo\n";


		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Administrador no encontrado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}
	/**
	 * Se verifica primero que los datos sean validos, luego estos se mandan al metodo de aplicacion, donde se llama a concesionario que verifica que realmente exista el
	 * administrador
	 * @param identificacion
	 * @param contrasenia
	 * @return
	 */
	public boolean verificarQueExiste(String identificacion, String contrasenia){
		boolean existe= false;
		if (validarDatos(identificacion,contrasenia)) {
			if (singleton.verificarAdmin(identificacion, contrasenia)) {
				existe= true;
			}
		}
		return existe;
	}


	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}


	/**
	 * Se capturan los datos, estos se mandan al metodo que verifica si el administrador existe
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void showVentanaAdministrador(ActionEvent event) throws IOException {
    	String identificacion= txtIdentificacionLogin.getText();
    	String contrasenia= txtContraseniaLogin.getText();
    	if (verificarQueExiste(identificacion, contrasenia)) {
     		FXMLLoader loader= new FXMLLoader();
    		loader.setLocation(Aplicacion.class.getResource("../Views/AdministradorView.fxml"));
    		AnchorPane anchorPane= (AnchorPane)loader.load();
    		AdministradorController administradorController = loader.getController();
    		administradorController.setAplicacion(aplicacion);
    		Scene scene= new Scene(anchorPane);
    		Stage stage = new Stage();
    		stage.setTitle("Perfil administrador");
    		stage.setScene(scene);
    		administradorController.init(stage, this);
    		stage.show();
    		this.stage.close();

		}else {
			mostrarMensaje("Incio de sesion", "Administrador no encontrado", "Parece ser que el administrador con las credenciales proporcionadas no existe", AlertType.WARNING);
		}

    }



    @FXML
    void abrirGestionAdmin(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader();
		loader.setLocation(Aplicacion.class.getResource("../Views/CrearAdminView.fxml"));
		AnchorPane anchorPane= (AnchorPane)loader.load();
		CrearAdminController crearAdminController = loader.getController();
		crearAdminController.setAplicacion(aplicacion);
		Scene scene= new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setTitle("Crear Administradores");
		stage.setScene(scene);
		crearAdminController.init(stage, this);
		stage.show();
		this.stage.close();
    }


    @FXML
    void recuperarConCorreo(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader();
		loader.setLocation(Aplicacion.class.getResource("../Views/CorreoView.fxml"));
		AnchorPane anchorPane= (AnchorPane)loader.load();
		CorreoController correoController= loader.getController();
		correoController.setAplicacion(aplicacion);
		Scene scene= new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setTitle("Correo");
		stage.setScene(scene);
		correoController.init(stage, this);
		stage.show();
		this.stage.close();
    }


    @FXML
    void cerrarLogin(ActionEvent event) {
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


    public TextField getTxtIdentificacionLogin() {
		return txtIdentificacionLogin;
	}
	public void setTxtIdentificacionLogin(TextField txtIdentificacionLogin) {
		this.txtIdentificacionLogin = txtIdentificacionLogin;
	}


    public PasswordField getTxtContraseniaLogin() {
		return txtContraseniaLogin;
	}
	public void setTxtContraseniaLogin(PasswordField txtContraseniaLogin) {
		this.txtContraseniaLogin = txtContraseniaLogin;
	}
	@FXML
    void initialize() {

    }

	public void init(Stage stage, PrincipalController principalController) {
		this.principioController = principalController;
		//this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;

	}

	public void init(Stage stage, LoginEmpleadoController loginEmpleadoController) {
		this.loginEmpleadoController = loginEmpleadoController;
		//this.concesionario = aplicacion.getConcesionario();
		this.stage = stage;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
