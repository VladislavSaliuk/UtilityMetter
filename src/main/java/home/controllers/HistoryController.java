package home.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private TableView historyTableView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColumnHeaderColor(historyTableView, "#7C557CFF");
    }
    private void setTableColumnHeaderColor(TableView<?> tableView, String color) {
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            column.setStyle("-fx-background-color: " + color + ";");
        }
    }
}
