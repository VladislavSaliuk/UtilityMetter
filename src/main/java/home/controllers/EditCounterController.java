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

public class EditCounterController implements Initializable {
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button editCounterButton;
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
    private Meter editedMeter;
    public EditCounterController() {

    }
    public EditCounterController(Meter editedMeter) {
        this.editedMeter = editedMeter;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        meterDAO = new MeterDAO();
        metterNumberTextField.setText(editedMeter.getMeterNumber());
        dayTariffTextField.setText(String.valueOf(editedMeter.getDayTariffValue()));
        nightTariffTextField.setText(String.valueOf(editedMeter.getNightTariffValue()));
    }
    @FXML
    public void editCounter(ActionEvent event){
        Meter meter = new Meter();
        meter.setMeterID(editedMeter.getMeterID());
        meter.setMeterNumber(metterNumberTextField.getText());
        meter.setDayTariffValue(Double.parseDouble(dayTariffTextField.getText()));
        meter.setNightTariffValue(Double.parseDouble(nightTariffTextField.getText()));
        meterDAO.edit(meter);
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}
