package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CorreoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnAceptartContrase�a;

    @FXML
    private TextField txtNuevaContrase�a;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCorreo;

    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblIdentificacion;

    @FXML
    private Label lblContrase�a;

    @FXML
    private TextField txtIdentificacion;

	private LoginController loginController;

	private Stage stage;

	private Aplicacion aplicacion;

    @FXML
    void enviarCodigo(ActionEvent event) {

    }

    @FXML
    void validarCodigo(ActionEvent event) {

    }

    @FXML
    void abrirLoginAdministrador(ActionEvent event) {

    }
	public void init(Stage stage, LoginController loginController) {
		this.loginController = loginController;
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
	public void initialize(URL location, ResourceBundle resources) {
		txtCodigo.setVisible(false);
		txtIdentificacion.setVisible(false);
		txtNuevaContrase�a.setVisible(false);

		lblCodigo.setVisible(false);
		lblContrase�a.setVisible(false);
		lblIdentificacion.setVisible(false);

		btnAceptar.setVisible(false);
		btnAceptartContrase�a.setVisible(false);

	}
}

