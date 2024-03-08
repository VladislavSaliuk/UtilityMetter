module org.example.utilitymetter {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens home to javafx.fxml;
    exports home;
    exports home.controllers;
    opens home.controllers to javafx.fxml;
}