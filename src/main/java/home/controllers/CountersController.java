package home.controllers;

import database.DAO.meters.MeterDAO;
import database.entity.Meter;
import home.UtilityMetterApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


public class CountersController implements Initializable {
    @FXML
    private TableView<Meter> countersTableView;

    @FXML
    private TableColumn<Meter, String> meterNumberTableColumn;

    @FXML
    private TableColumn<Meter, Double> dayTariffTableColumn;

    @FXML
    private TableColumn<Meter, Double> nightTariffTableColumn;
    private MeterDAO meterDAO;
    private ObservableList<Meter> observableList =  FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        meterDAO = new MeterDAO();
        observableList.addAll(meterDAO.getItems());
        meterNumberTableColumn.setCellValueFactory(new PropertyValueFactory<Meter, String>("meterNumber"));
        dayTariffTableColumn.setCellValueFactory(new PropertyValueFactory<Meter, Double>("dayTariffValue"));
        nightTariffTableColumn.setCellValueFactory(new PropertyValueFactory<Meter,Double>("nightTariffValue"));
        countersTableView.setItems(observableList);
    }

    @FXML
    public void addCounterButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(UtilityMetterApplication.class.getResource("add-counter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        stage.setTitle("Add meter");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void refreshTable(ActionEvent event){
        observableList =  FXCollections.observableArrayList(meterDAO.getItems());
        countersTableView.setItems(observableList);
    }
    @FXML
    public void clearTable(ActionEvent event){
        countersTableView.getItems().clear();
        meterDAO.clear();
    }
    @FXML
    public void deleteCounter(ActionEvent event){
        Meter selectedMeter = countersTableView.getSelectionModel().getSelectedItem();
        countersTableView.getItems().remove(selectedMeter);
        meterDAO.delete(selectedMeter.getMeterID());
    }
    @FXML
    public void editCounter(ActionEvent event) throws IOException {
        Meter selectedMeter = countersTableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(UtilityMetterApplication.class.getResource("edit-counter.fxml"));
        fxmlLoader.setController(new EditCounterController(selectedMeter));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        stage.setTitle("Edit meter");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
