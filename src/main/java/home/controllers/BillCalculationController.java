package home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.MetterCalculator;

import java.net.URL;
import java.util.ResourceBundle;

public class BillCalculationController implements Initializable {
    @FXML
    private Slider markupSlider;

    @FXML
    private TextField dayConsumingTextField;

    @FXML
    private TextField nightConsumingTextField;

    @FXML
    private Label calculationResultLabel;

    @FXML
    private Label markupLabel;
    private MetterCalculator meterCalculator;
    private int markupValue;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        meterCalculator = new MetterCalculator();
        markupLabel.setText("Markup " + markupValue + "%");
    }

    @FXML
    public void calculateTotalBillAction(ActionEvent event) {
        if (dayConsumingTextField.getText().isEmpty() || nightConsumingTextField.getText().isEmpty()) {
            displayMessage("Please fill in all textfields!", Color.color(1,0,0));
        } else {
            try {
                calculateTotalBill();
            } catch (NumberFormatException e) {
                displayMessage("Please enter numeric values for energy consumption!",Color.color(1,0,0));
            }
        }
    }
    private void calculateTotalBill(){
        double dayTariffValue = 0.15;
        double nightTariffValue = 0.1;
        double consumedEnergyDuringDayPeriod = Double.parseDouble(dayConsumingTextField.getText());
        double consumedEnergyDuringNightPeriod = Double.parseDouble(nightConsumingTextField.getText());
        meterCalculator.setDayTariffValue(dayTariffValue);
        meterCalculator.setNightTariffValue(nightTariffValue);
        meterCalculator.setConsumedEnergyDuringDayPeriod(consumedEnergyDuringDayPeriod);
        meterCalculator.setConsumedEnergyDuringNightPeriod(consumedEnergyDuringNightPeriod);
        String resultMessage = "Your total bill count is " + meterCalculator.calculateTotalBill(markupValue) + " $";
        displayMessage(resultMessage,Color.color(0,0,0));
    }
    private void displayMessage(String message, Color fontColor) {
        calculationResultLabel.setTextFill(fontColor);
        calculationResultLabel.setAlignment(Pos.CENTER);
        calculationResultLabel.setText(message);
    }

    @FXML
    public void updateText(MouseEvent event) {
        int value = (int) markupSlider.getValue();
        markupValue = value;
        markupLabel.setText("Markup " + value + "%");
    }

}
