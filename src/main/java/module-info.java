module org.example.utilitymetter {
    requires javafx.controls;
    requires javafx.fxml;


    opens home to javafx.fxml;
    exports home;
    exports home.controllers;
    opens home.controllers to javafx.fxml;
}