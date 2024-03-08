package home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.MetterCalculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private Label paneLabel;
    @FXML
    private Pane pane;
    @FXML
    private Button exitButton;
    @FXML
    private GridPane calculateBillGridPane;
    @FXML
    private GridPane countersGridPane;
    @FXML
    private GridPane historyGridPane;
    @FXML
    private void handleClicks(ActionEvent event){
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        switch (buttonId) {
            case "calculateBillButton":
                handleCalculateBill();
                break;
            case "countersButton":
                handleCounters();
                break;
            case "historyButton":
                handleHistory();
                break;
            default:
                break;
        }
    }

    private void handleCalculateBill() {
        paneLabel.setText("Calculate bill");
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(89, 107, 124), CornerRadii.EMPTY, Insets.EMPTY)));
        calculateBillGridPane.toFront();
    }

    private void handleCounters() {
        paneLabel.setText("Counters");
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(93, 124, 89), CornerRadii.EMPTY, Insets.EMPTY)));
        countersGridPane.toFront();
    }

    private void handleHistory() {
        paneLabel.setText("History");
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(124, 85, 124), CornerRadii.EMPTY, Insets.EMPTY)));
        historyGridPane.toFront();
    }

    public void handleClose(ActionEvent event){
        if(event.getSource() == exitButton){
            System.exit(0);
        }
    }

}