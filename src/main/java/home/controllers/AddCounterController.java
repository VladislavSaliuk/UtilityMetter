package home.controllers;

import database.DAO.meters.MeterDAO;
import database.entity.Meter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCounterController implements Initializable {
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button addCounterButton;
    @FXML
    private TextField metterNumberTextField;
    @FXML
    private TextField dayTariffTextField;
    @FXML
    private TextField nightTariffTextField;
    private MeterDAO meterDAO;
    private String meterNumber;
    private double dayTariff;
    private double nightTariff;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        meterDAO = new MeterDAO();
    }
    @FXML
    public void addCounter(ActionEvent event){
        meterNumber = metterNumberTextField.getText();
        dayTariff = Double.parseDouble(dayTariffTextField.getText());
        nightTariff = Double.parseDouble(nightTariffTextField.getText());
        Meter meter = new Meter();
        meter.setMeterNumber(meterNumber);
        meter.setDayTariffValue(dayTariff);
        meter.setNightTariffValue(nightTariff);
        meterDAO.add(meter);
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

}
