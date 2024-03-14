module org.example.utilitymetter {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires static lombok;

    opens home to javafx.fxml;
    exports home;
    exports home.controllers;
    opens home.controllers to javafx.fxml;

    opens database.entity to javafx.base;
    exports database.entity;
}
