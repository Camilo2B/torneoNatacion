package co.edu.uniquindio.poo.protectonataciondto_record.ViewController;

import co.edu.uniquindio.poo.protectonataciondto_record.Model.Participante;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Categoria;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Tiempo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ParticipantesViewController {

    @FXML
    private TextField txtNombre, txtApellido, txtTiempoMinutos, txtTiempoSegundos, txtTiempoMilisegundos, txtEdad, txtCategoria;

    @FXML
    private Button btnRegistrar, btnVerResultados;

    @FXML
    private TextArea txtListaParticipantes;

    private final LinkedList<Participante> participantes = new LinkedList<>();

    /**
     * Método para cambiar de vista y permitir el paso de datos.
     */
    private void cambiarVista(ActionEvent event, FXMLLoader loader) {
        try {
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Regresa al menú principal.
     */
    @FXML
    private void regresarMenu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/protectonataciondto_record/Menu-View.fxml"));
        cambiarVista(event, loader);
    }

    /**
     * Cambia a la vista de puntajes y pasa la lista de participantes registrados.
     */
    @FXML
    private void cambiarVistaPuntajes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/protectonataciondto_record/PuntajesView.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la vista de puntajes
            PuntajesViewController controller = loader.getController();
            controller.setParticipantes(participantes); // Pasar los participantes registrados

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registra un nuevo participante.
     */
    @FXML
    private void registrarParticipante() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String edadStr = txtEdad.getText().trim();
        String categoriaStr = txtCategoria.getText().trim();
        String minutosStr = txtTiempoMinutos.getText().trim();
        String segundosStr = txtTiempoSegundos.getText().trim();
        String milisegundosStr = txtTiempoMilisegundos.getText().trim();

        // Validaciones de campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || edadStr.isEmpty() || categoriaStr.isEmpty()
                || minutosStr.isEmpty() || segundosStr.isEmpty() || milisegundosStr.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            int minutos = Integer.parseInt(minutosStr);
            int segundos = Integer.parseInt(segundosStr);
            int milisegundos = Integer.parseInt(milisegundosStr);

            if (edad <= 0) {
                mostrarAlerta("Error", "La edad debe ser un número positivo.");
                return;
            }

            if (minutos < 0 || segundos < 0 || segundos >= 60 || milisegundos < 0 || milisegundos >= 1000) {
                mostrarAlerta("Error", "Tiempo inválido. Verifica los valores ingresados.");
                return;
            }

            // Convertir categoría
            Categoria categoria;
            try {
                categoria = Categoria.valueOf(categoriaStr.toUpperCase()); // Asegura que el string coincida con la enumeración
            } catch (IllegalArgumentException e) {
                mostrarAlerta("Error", "Categoría inválida. Ingresa un valor válido.");
                return;
            }

            // Crear el objeto Participante
            Participante participante = new Participante(nombre, apellido, edad, categoria);
            participante.registrarTiempo(new Tiempo(minutos, segundos, milisegundos));

            // Agregar a la lista
            participantes.add(participante);
            actualizarLista();

            // Limpiar los campos después del registro
            txtNombre.clear();
            txtApellido.clear();
            txtEdad.clear();
            txtCategoria.clear();
            txtTiempoMinutos.clear();
            txtTiempoSegundos.clear();
            txtTiempoMilisegundos.clear();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese valores numéricos válidos en edad y tiempo.");
        }
    }

    /**
     * Muestra la lista de participantes en el TextArea.
     */
    private void actualizarLista() {
        StringBuilder lista = new StringBuilder();
        for (Participante p : participantes) {
            lista.append("Nombre: ").append(p.getNombre()).append(" ").append(p.getApellido())
                    .append(" | Edad: ").append(p.getEdad())
                    .append(" | Categoría: ").append(p.getCategoria())
                    .append(" | Tiempo: ").append(p.getTiempo().obtenerFormatoTiempo()).append("\n");
        }
        txtListaParticipantes.setText(lista.toString());
    }

    /**
     * Muestra una alerta en la pantalla.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
