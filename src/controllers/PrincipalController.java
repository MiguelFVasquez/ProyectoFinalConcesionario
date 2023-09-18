package controllers;

import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Aplicacion;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrincipalController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEmpleado;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnAdministrador;


    @FXML
    private ImageView paneEmpleado;

    @FXML
    private ImageView paneAdmin;


	private Aplicacion aplicacion;

	private Stage stage;

    @FXML
    void abrirViewEmpleado(ActionEvent event) throws IOException {
 		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(Aplicacion.class.getResource("../Views/LoginEmpleado.fxml"));
		AnchorPane anchorPane= (AnchorPane)loader.load();
		LoginEmpleadoController loginEmpleadoController = loader.getController();
		loginEmpleadoController.setAplicacion(aplicacion);
		Scene scene= new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Login Empleado");
		loginEmpleadoController.init(stage, this);
		stage.show();
		this.stage.close();
    }

    @FXML
    void abrirViewAdministrador(ActionEvent event) throws IOException {
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
    void cerrarPrincipal(ActionEvent event) {
        stage.close();
    }

    @FXML
    void initialize() {

    }


	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}
	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
    public void show() {
 		stage.show();
 	}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	System.gc();
    	 btnEmpleado.setStyle("-fx-background-color:  #479B9B; -fx-text-fill: white;");
    	 btnAdministrador.setStyle("-fx-background-color:  #479B9B; -fx-text-fill: white;");
    	 btnCerrar.setStyle("-fx-background-color: #FF2D00; -fx-text-fill: white;");

    }


}
