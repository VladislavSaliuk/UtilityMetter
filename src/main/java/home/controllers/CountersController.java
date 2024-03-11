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
    private TableView countersTableView;

    @FXML
    private TableColumn<Meter, String> meterNumberTableColumn;

    @FXML
    private TableColumn<Meter, Double> dayTariffTableColumn;

    @FXML
    private TableColumn<Meter, Double> nightTariffTableColumn;

    @FXML
    private TableColumn<Meter, Date> dateTableColumn;
    private MeterDAO meterDAO;

    private ObservableList<Meter> observableList =  FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHeaderColumnStyle(meterNumberTableColumn);
        setHeaderColumnStyle(dayTariffTableColumn);
        setHeaderColumnStyle(nightTariffTableColumn);
        setHeaderColumnStyle(dateTableColumn);
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
        stage.setTitle("Utility metter");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    private void setHeaderColumnStyle(TableColumn<?, ?> column) {
        column.getStyleClass().add("countersTableColumnHeader");
    }
}
