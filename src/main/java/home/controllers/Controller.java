package home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML
    private GridPane calculateBillGridPane;
    @FXML
    private GridPane countersGridPane;
    @FXML
    private GridPane historyGridPane;
    @FXML
    private TableView countersTableView;
    @FXML
    private TableView historyTableView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColumnHeaderColor(countersTableView, "#5D7C59FF");
        setTableColumnHeaderColor(historyTableView, "#7C557CFF");
    }

    private void setTableColumnHeaderColor(TableView<?> tableView, String color) {
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            column.setStyle("-fx-background-color: " + color + ";");
        }
    }
    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == calculateBillButton) {
            paneLabel.setText("Calculate bill");
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(89,107,124), CornerRadii.EMPTY, Insets.EMPTY)));
            calculateBillGridPane.toFront();
        }
        else if(event.getSource() == countersButton) {
            paneLabel.setText("Counters");
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(93,124,89), CornerRadii.EMPTY, Insets.EMPTY)));
            countersGridPane.toFront();
        }
        else if(event.getSource() == historyButton) {
            paneLabel.setText("History");
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(124,85,124), CornerRadii.EMPTY, Insets.EMPTY)));
            historyGridPane.toFront();
        }
    }

    public void handleClose(ActionEvent event){
        if(event.getSource() == exitButton){
            System.exit(0);
        }
    }


}