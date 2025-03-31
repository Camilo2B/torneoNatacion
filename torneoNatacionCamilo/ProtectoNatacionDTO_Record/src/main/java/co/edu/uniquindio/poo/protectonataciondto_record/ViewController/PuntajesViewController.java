package co.edu.uniquindio.poo.protectonataciondto_record.ViewController;

import co.edu.uniquindio.poo.protectonataciondto_record.Model.Categoria;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Participante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PuntajesViewController implements Initializable {

    @FXML
    private TableView<Participante> tablaResultados;

    @FXML
    private TableColumn<Participante, String> colNombre;

    @FXML
    private TableColumn<Participante, Integer> colEdad;

    @FXML
    private TableColumn<Participante, Integer> colApellido;

    @FXML
    private TableColumn<Participante, Integer> colTiempo;


    @FXML
    private TableColumn<Participante, String> colCategoria;

    private final ObservableList<Participante> listaParticipantes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));

        listaParticipantes.add(new Participante("Juan", "Carlos", 12, Categoria.ADULTO));
        listaParticipantes.add(new Participante("Juan", "Carlos", 12, Categoria.ADULTO));


        tablaResultados.setItems(listaParticipantes);
    }

    public void setParticipantes(List<Participante> participantes) {
        listaParticipantes.setAll(participantes);
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


    @FXML
    private void regresarMenu(ActionEvent event) {
        cambiarVista(event, "/co/edu/uniquindio/poo/protectonataciondto_record/Menu-View.fxml");
    }
}
