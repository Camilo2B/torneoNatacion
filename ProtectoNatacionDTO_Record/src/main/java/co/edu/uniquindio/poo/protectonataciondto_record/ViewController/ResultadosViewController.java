package co.edu.uniquindio.poo.protectonataciondto_record.ViewController;

import co.edu.uniquindio.poo.protectonataciondto_record.Model.Participante;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Resultado;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Tiempo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ResultadosViewController {

    private Participante participante;
    private Tiempo tiempo;


    @FXML
    private Label lblGanador;

    private List<Resultado> listaResultados;


    @FXML
    private Button btnVolver;

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

    /**
     * Método para recibir los resultados de la vista de puntajes.
     */
    public void setResultados(List<Resultado> resultados) {
        this.listaResultados = resultados;
    }

    private int convertirAMilisegundos(Tiempo tiempo) {
        return (tiempo.getMinutos() * 60000) +
                (tiempo.getSegundos() * 1000) +
                tiempo.getMilisegundos();
    }

    /**
     * Método que determina el ganador basado en el menor tiempo registrado.
     */
    @FXML
    private void determinarGanador() {
        if (listaResultados == null || listaResultados.isEmpty()) {
            lblGanador.setText("No hay resultados registrados.");
            return;
        }

        Resultado mejorResultado = listaResultados.stream()
                .min((r1, r2) -> Integer.compare(
                        convertirAMilisegundos(r1.ganador().getTiempo()),
                        convertirAMilisegundos(r2.ganador().getTiempo())
                ))
                .orElse(null);


        if (mejorResultado != null) {
            Participante ganador = mejorResultado.ganador();
            Tiempo tiempoGanador = ganador.getTiempo();

            lblGanador.setText("Ganador: " + ganador.getNombre() + " con " +
                    tiempoGanador.getMinutos() + " min " +
                    tiempoGanador.getSegundos() + " seg " +
                    tiempoGanador.getMilisegundos() + " ms.");
        }
    }

}
