module org.example.utilitymetter {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens home to javafx.fxml;
    opens home.controllers to javafx.fxml;
}