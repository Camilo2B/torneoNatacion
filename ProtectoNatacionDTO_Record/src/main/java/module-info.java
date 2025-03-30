module co.edu.uniquindio.poo.protectonataciondto_record {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens co.edu.uniquindio.poo.protectonataciondto_record to javafx.fxml;
    opens co.edu.uniquindio.poo.protectonataciondto_record.ViewController to javafx.fxml;
    opens co.edu.uniquindio.poo.protectonataciondto_record.Model to javafx.base; // Agregado

    exports co.edu.uniquindio.poo.protectonataciondto_record;
}