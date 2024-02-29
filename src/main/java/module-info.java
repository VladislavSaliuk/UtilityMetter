module org.example.utilitymetter {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;


    opens org.example.utilitymetter to javafx.fxml;
    exports org.example.utilitymetter;
}
