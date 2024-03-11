package home.controllers;

import home.UtilityMetterApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;


public class CountersController {
    @FXML
    private TableView countersTableView;

    @FXML
    public void addCounterButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(UtilityMetterApplication.class.getResource("add-counter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        stage.setTitle("Utility metter");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
