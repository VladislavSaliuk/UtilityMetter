package home.controllers;

import database.DAO.meters.CounterDAO;
import database.entity.Counter;
import home.UtilityMetterApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CountersController implements Initializable {
    @FXML
    private TableView<Counter> countersTableView;

    @FXML
    private TableColumn<Counter, String> counterNumberTableColumn;

    @FXML
    private TableColumn<Counter, Double> dayTariffTableColumn;

    @FXML
    private TableColumn<Counter, Double> nightTariffTableColumn;
    @FXML
    private TextField countersSearchFilter;
    private CounterDAO counterDAO;
    private ObservableList<Counter> observableList =  FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        counterDAO = new CounterDAO();
        observableList.addAll(counterDAO.getItems());
        counterNumberTableColumn.setCellValueFactory(new PropertyValueFactory<Counter, String>("counterNumber"));
        dayTariffTableColumn.setCellValueFactory(new PropertyValueFactory<Counter, Double>("dayTariff"));
        nightTariffTableColumn.setCellValueFactory(new PropertyValueFactory<Counter,Double>("nightTariff"));
        countersTableView.setItems(observableList);

        FilteredList<Counter> filteredList = new FilteredList<>(observableList, b -> true);
        countersSearchFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(meter -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return meter.getCounterNumber().toLowerCase().contains(lowerCaseFilter) ||
                        Double.toString(meter.getDayTariff()).toLowerCase().contains(lowerCaseFilter) ||
                        Double.toString(meter.getNightTariff()).toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Counter> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(countersTableView.comparatorProperty());
        countersTableView.setItems(sortedData);
    }

    @FXML
    public void addCounterButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(UtilityMetterApplication.class.getResource("add-counter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        stage.setTitle("Add counter");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void refreshTable(ActionEvent event){
        observableList =  FXCollections.observableArrayList(counterDAO.getItems());
        countersTableView.setItems(observableList);
    }
    @FXML
    public void clearTable(ActionEvent event){
        countersTableView.getItems().clear();
        counterDAO.clear();
    }
    @FXML
    public void deleteCounter(ActionEvent event){
        Counter selectedCounter = countersTableView.getSelectionModel().getSelectedItem();
        if(selectedCounter != null) {
            countersTableView.getItems().remove(selectedCounter);
            counterDAO.delete(selectedCounter.getCounterID());
        }
    }
    @FXML
    public void editCounter(ActionEvent event) throws IOException {
        Counter selectedCounter = countersTableView.getSelectionModel().getSelectedItem();
        if(selectedCounter != null) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(UtilityMetterApplication.class.getResource("edit-counter.fxml"));
            fxmlLoader.setController(new EditCounterController(selectedCounter));
            Scene scene = new Scene(fxmlLoader.load(), 300, 300);
            stage.setTitle("Edit counter");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }

}
