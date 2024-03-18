package home.controllers;

import database.DAO.meters.CounterDAO;
import database.entity.Counter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CountersController implements Initializable {
    @FXML
    private TableView<Counter> countersTableView;
    @FXML
    private TableColumn<Counter, String> counterNumberTableColumn;
    @FXML
    private TableColumn<Counter, Double> currentDayConsumptionTableColumn;
    @FXML
    private TableColumn<Counter, Double> currentNightConsumptionTableColumn;
    @FXML
    private TextField counterTextField;

    private CounterDAO counterDAO;
    private ObservableList<Counter> countersObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        counterDAO = new CounterDAO();
        initializeTableView();
        setupSelectionListener();
    }

    private void initializeTableView() {
        countersObservableList.addAll(counterDAO.getItems());
        counterNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("counterNumber"));
        currentDayConsumptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("currentDayConsumptionValue"));
        currentNightConsumptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("currentNightConsumptionValue"));
        countersTableView.setItems(countersObservableList);
    }

    private void setupSelectionListener() {
        countersTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                counterTextField.setText(newSelection.getCounterNumber());
            }
        });
    }

    @FXML
    public void handleAddCounter(ActionEvent event) {
        String counterNumber = counterTextField.getText();
        counterDAO.add(counterNumber);
        refreshTableView();
    }

    @FXML
    public void handleRefreshTableView(ActionEvent event) {
        refreshTableView();
    }

    @FXML
    public void handleClearTableView(ActionEvent event) {
        countersTableView.getItems().clear();
        counterDAO.clear();
    }

    @FXML
    public void handleDeleteCounter(ActionEvent event) {
        Counter selectedCounter = countersTableView.getSelectionModel().getSelectedItem();
        if (selectedCounter != null) {
            countersTableView.getItems().remove(selectedCounter);
            counterDAO.delete(selectedCounter.getCounterID());
        }
    }

    @FXML
    public void handleEditCounter(ActionEvent event) {
        Counter selectedCounter = countersTableView.getSelectionModel().getSelectedItem();
        if (selectedCounter != null) {
            String newCounterNumber = counterTextField.getText();
            selectedCounter.setCounterNumber(newCounterNumber);
            counterDAO.edit(selectedCounter);
            refreshTableView();
        }
    }

    private void refreshTableView() {
        countersObservableList.clear();
        countersObservableList.addAll(counterDAO.getItems());
        countersTableView.setItems(countersObservableList);
    }
}
