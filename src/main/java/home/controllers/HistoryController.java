package home.controllers;

import database.DAO.history.HistoryDAO;
import database.entity.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private Button refreshButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<History> historyTableView;

    @FXML
    private TableColumn<History, String> meterNumberTableColumn;

    @FXML
    private TableColumn<History, Double> dayTariffTableColumn;

    @FXML
    private TableColumn<History, Double> nightTariffTableColumn;

    @FXML
    private TableColumn<History, Integer> markupTableColumn;

    @FXML
    private TableColumn<History, Double> billTableColumn;

    @FXML
    private TableColumn<History, String> payDateTableColumn;
    private HistoryDAO historyDAO;
    private ObservableList observableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyDAO = new HistoryDAO();
        observableList.addAll(historyDAO.getItems());
        meterNumberTableColumn.setCellValueFactory(new PropertyValueFactory<History, String>("meterNumber"));
        billTableColumn.setCellValueFactory(new PropertyValueFactory<History, Double>("billValue"));
        dayTariffTableColumn.setCellValueFactory(new PropertyValueFactory<History, Double>("dayTariff"));
        nightTariffTableColumn.setCellValueFactory(new PropertyValueFactory<History, Double>("nightTariff"));
        markupTableColumn.setCellValueFactory(new PropertyValueFactory<History,Integer>("markup"));
        payDateTableColumn.setCellValueFactory(new PropertyValueFactory<History, String>("payDate"));
        historyTableView.setItems(observableList);
    }

    @FXML
    public void refreshTable(ActionEvent event){
        observableList = FXCollections.observableArrayList(historyDAO.getItems());
        historyTableView.setItems(observableList);
    }

    @FXML
    public void delete(ActionEvent event){
        History selectedHistory = historyTableView.getSelectionModel().getSelectedItem();
        historyTableView.getItems().remove(selectedHistory);
        historyDAO.delete(selectedHistory.getHistoryID());
    }

    @FXML
    public void clearHistory(ActionEvent event){
        historyTableView.getItems().clear();
        historyDAO.clear();
    }

}
