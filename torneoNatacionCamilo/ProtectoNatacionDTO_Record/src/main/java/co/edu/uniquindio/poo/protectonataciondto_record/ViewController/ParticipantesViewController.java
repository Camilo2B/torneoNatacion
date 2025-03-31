package co.edu.uniquindio.poo.protectonataciondto_record.ViewController;

import co.edu.uniquindio.poo.protectonataciondto_record.Model.Clasificacion;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Participante;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Categoria;
import co.edu.uniquindio.poo.protectonataciondto_record.Model.Tiempo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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


    private ListView<String> listaClasificacion;
    private ObservableList<String> items;
    private TextField nombreField;
    private TextField apellidoField;
    private TextField edadField;
    private TextField categoriaField;
    private TextField tiempoField;


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

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Clasificación de Participantes");

        // Crear componentes de entrada
        nombreField = new TextField();
        apellidoField = new TextField();
        edadField = new TextField();
        categoriaField = new TextField();
        tiempoField = new TextField();
        Button agregarButton = new Button("Agregar");

        // Crear la ListView
        listaClasificacion = new ListView<>();
        items = FXCollections.observableArrayList();
        listaClasificacion.setItems(items);

        // Crear el layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Agregar componentes al layout
        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nombreField, 1, 0);
        grid.add(new Label("Apellido:"), 0, 1);
        grid.add(apellidoField, 1, 1);
        grid.add(new Label("Edad:"), 0, 2);
        grid.add(edadField, 1, 2);
        grid.add(new Label("Categoria:"), 0, 3);
        grid.add(categoriaField, 1, 3);
        grid.add(new Label("Tiempo:"), 0, 3);
        grid.add(tiempoField, 1, 4);
        grid.add(agregarButton, 1, 4);
        grid.add(listaClasificacion, 0, 5, 2, 1);

        // Configurar el botón agregar
        agregarButton.setOnAction(e -> agregarParticipante());

        // Crear la escena y mostrar la ventana
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void agregarParticipante() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String edad = edadField.getText();
        String categoria = categoriaField.getText();
        int tiempo = Integer.parseInt(tiempoField.getText());

        Participante participante = new Participante(nombre, apellido, 2, Categoria.ADULTO);
        mostrarClasificacion(participante);

        // Limpiar los campos de entrada
        nombreField.clear();
        apellidoField.clear();
        edadField.clear();
        categoriaField.clear();
        tiempoField.clear();
    }

    private void mostrarClasificacion(Participante participante) {
        // Agregar el participante a la lista existente
        List<Participante> participantes = items.stream()
                .map(item -> {
                    String[] parts = item.split("\\(");
                    String nombre = parts[0].trim();
                    String tiempoStr = parts[1].replace(")", "").trim();
                    String[] tiempoParts = tiempoStr.split(":");
                    return new Participante("a", " n", 2, Categoria.ADULTO);
                })
                .toList();
        participantes.add(participante);

        // Clasificar los participantes
        List<Participante> clasificacion = Clasificacion.clasificarParticipantes(participantes);

        // Actualizar la ObservableList
        items.clear();
        for (int i = 0; i < clasificacion.size(); i++) {
            items.add((i + 1) + ". " + clasificacion.get(i).toString());
        }

    }
}