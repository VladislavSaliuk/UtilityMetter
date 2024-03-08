package home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.MetterCalculator;

import java.net.URL;
import java.util.ResourceBundle;

public class BillCalculationController implements Initializable {
    @FXML
    private TextField dayConsumingTextField;
    @FXML
    private TextField nightConsumingTextField;
    @FXML
    private Label calculationResultLabel;
    private MetterCalculator metterCalculator;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        metterCalculator = new MetterCalculator();
    }

    @FXML
    public void calculacteTotalBill(ActionEvent event){
        try {
            metterCalculator.setDayTariffValue(0.15);
            metterCalculator.setNightTariffValue(0.1);
            double consumedEnergyDuringDayPeriod = Double.parseDouble(dayConsumingTextField.getText());
            double consumedEnergyDuringNightPeriod = Double.parseDouble(nightConsumingTextField.getText());
            metterCalculator.setConsumedEnergyDuringDayPeriod(consumedEnergyDuringDayPeriod);
            metterCalculator.setConsumedEnergyDuringNightPeriod(consumedEnergyDuringNightPeriod);
            calculationResultLabel.setText("Your total bill count is " + metterCalculator.calculateTotalBill() + " $");
        } catch (NumberFormatException e) {
            calculationResultLabel.setText("Please enter numeric values for energy consumption.");
        }
    }
}
