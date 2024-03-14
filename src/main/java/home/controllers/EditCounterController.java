package home.controllers;

import database.DAO.meters.CounterDAO;
import database.entity.Counter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCounterController implements Initializable {
    @FXML
    AnchorPane scenePane;
    @FXML
    private Button editCounterButton;
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
    private Counter editedCounter;
    private Alert alert;
    public EditCounterController() {

    }
    public EditCounterController(Counter editedCounter) {
        this.editedCounter = editedCounter;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        counterDAO = new CounterDAO();
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please fill in all fields!");
        counterNumberTextField.setText(editedCounter.getCounterNumber());
        dayTariffTextField.setText(String.valueOf(editedCounter.getDayTariff()));
        nightTariffTextField.setText(String.valueOf(editedCounter.getNightTariff()));
    }
    @FXML
    public void editCounter(ActionEvent event){
        if(counterNumberTextField.getText().isEmpty() || dayTariffTextField.getText().isEmpty() || nightTariffTextField.getText().isEmpty()) {
            alert.show();
        } else {
            Counter counter = new Counter();
            counter.setCounterID(editedCounter.getCounterID());
            counter.setCounterNumber(counterNumberTextField.getText());
            counter.setDayTariff(Double.parseDouble(dayTariffTextField.getText()));
            counter.setNightTariff(Double.parseDouble(nightTariffTextField.getText()));
            counterDAO.edit(counter);
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
}
