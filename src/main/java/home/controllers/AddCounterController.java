package home.controllers;

import database.DAO.meters.CounterDAO;
import database.entity.Counter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCounterController implements Initializable {
    @FXML
    private AnchorPane scenePane;
    @FXML
    private TextField counterNumberTextField;
    @FXML
    private TextField dayTariffTextField;
    @FXML
    private TextField nightTariffTextField;
    private CounterDAO counterDAO;
    private String counterNumber;
    private double dayTariff;
    private double nightTariff;
    private Alert alert;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please fill in all fields!");
        counterDAO = new CounterDAO();
    }
    @FXML
    public void addCounter(ActionEvent event){
        if (counterNumberTextField.getText().isEmpty() || dayTariffTextField.getText().isEmpty() || nightTariffTextField.getText().isEmpty()) {
            alert.show();
        } else {
            counterNumber = counterNumberTextField.getText();
            dayTariff = Double.parseDouble(dayTariffTextField.getText());
            nightTariff = Double.parseDouble(nightTariffTextField.getText());
            Counter counter = new Counter();
            counter.setCounterNumber(counterNumber);
            counter.setDayTariff(dayTariff);
            counter.setNightTariff(nightTariff);
            counterDAO.add(counter);
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

}
