package home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Label paneLabel;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Pane pane;
    @FXML
    private Button calculateBillButton;
    @FXML
    private Button countersButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button exitButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == calculateBillButton) {
            paneLabel.setText("Calculate bill");
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(89,107,124), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(event.getSource() == countersButton) {
            paneLabel.setText("Counters");
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(93,124,89), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(event.getSource() == historyButton) {
            paneLabel.setText("History");
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(124,85,124), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void handleClose(ActionEvent event){
        if(event.getSource() == exitButton){
            System.exit(0);
        }
    }


}