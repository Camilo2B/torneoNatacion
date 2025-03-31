package co.edu.uniquindio.poo.protectonataciondto_record.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class MenuViewController {

    @FXML
    private Button btnJugadores;

    @FXML
    private Button btnPuntajes;

    @FXML
    private Button btnResultado;

    @FXML
    private Button btnVolver;

    @FXML
    private Label txtMenuJuego;

    @FXML
    private void initialize() {
        btnJugadores.setOnAction(this::abrirVistaJugadores);
        btnPuntajes.setOnAction(this::abrirVistaPuntajes);
        btnResultado.setOnAction(this::abrirVistaResultado);
        btnVolver.setOnAction(this::regresarBienvenida);
    }

    private void abrirVistaJugadores(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/protectonataciondto_record/Participantes-View.fxml");
    }

    private void abrirVistaPuntajes(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/protectonataciondto_record/Puntajes-View.fxml");
    }

    private void abrirVistaResultado(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/protectonataciondto_record/Resultados-View.fxml");
    }

    @FXML
    private void regresarBienvenida(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/protectonataciondto_record/Bienvenida-View.fxml");
    }

    private void cambiarVista(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


