module com.example.ejerciciod {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicioe to javafx.fxml;
    exports com.example.ejercicioe;
}