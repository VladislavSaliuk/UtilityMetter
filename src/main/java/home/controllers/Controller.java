package home.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Stage stage;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label exitLabel;
    @FXML
    void initialize(){
        exitLabel.setOnMouseClicked(event -> {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        });
    }

}