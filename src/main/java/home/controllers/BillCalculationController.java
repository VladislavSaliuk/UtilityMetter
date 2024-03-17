package home.controllers;

import database.DAO.history.HistoryDAO;
import database.entity.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.CountersComboBoxInitializer;
import logic.CounterCalculator;
import logic.TariffsOperator;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class BillCalculationController implements Initializable {
    @FXML
    private Slider markupSlider;
    @FXML
    private TextField dayConsumingTextField;
    @FXML
    private TextField nightConsumingTextField;
    @FXML
    private ComboBox<String> countersComboBox;
    @FXML
    private Label calculationResultLabel;
    @FXML
    private Label markupLabel;
    private CounterCalculator counterCalculator;
    private HistoryDAO historyDAO;
    private CountersComboBoxInitializer countersComboBoxInitializer;
    private TariffsOperator tariffsOperator;
    private int markupValue;
    private double totalBill;
    private ObservableList<String> comboBoxObservableList =  FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalBill = 0;
        counterCalculator = new CounterCalculator();
        historyDAO = new HistoryDAO();
        countersComboBoxInitializer = new CountersComboBoxInitializer();
        tariffsOperator = new TariffsOperator();
        markupLabel.setText("Markup " + markupValue + "%");
    }

    @FXML
    public void calculateTotalBillAction(ActionEvent event) {
        if (dayConsumingTextField.getText().isEmpty() || nightConsumingTextField.getText().isEmpty()) {
            displayMessage("Please fill in all textfields!", Color.RED);
            totalBill = 0;
        } else if(countersComboBox.getValue() == null){
            displayMessage("Please choose meter before calculation!", Color.RED);
            totalBill = 0;
        }else {
            try {
                calculateTotalBill();
            } catch (NumberFormatException e) {
                displayMessage("Please enter numeric values for energy consumption!",Color.RED);
                totalBill = 0;
            } catch (IllegalArgumentException e){
                displayMessage(e.getMessage(),Color.RED);
                totalBill = 0;
            }
        }
    }
    private void calculateTotalBill(){
        String countersComboBoxCurrentValue = countersComboBox.getValue();
        double dayTariffValue = tariffsOperator.getDayTariffValue(countersComboBoxCurrentValue);
        double nightTariffValue = tariffsOperator.getNightTariffValue(countersComboBoxCurrentValue);
        double consumedEnergyDuringDayPeriod = Double.parseDouble(dayConsumingTextField.getText());
        double consumedEnergyDuringNightPeriod = Double.parseDouble(nightConsumingTextField.getText());
        counterCalculator.setDayTariff(dayTariffValue);
        counterCalculator.setNightTariff(nightTariffValue);
        counterCalculator.setDayEnergyConsumption(consumedEnergyDuringDayPeriod);
        counterCalculator.setNightEnergyConsumption(consumedEnergyDuringNightPeriod);
        totalBill = counterCalculator.calculateTotalBill(markupValue);
        String resultMessage = String.format("Your total bill count is %.2f $", totalBill);
        displayMessage(resultMessage, Color.BLACK);
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
    @FXML
    public void addToHistory(ActionEvent event){
        if(totalBill == 0) {
            displayMessage("Please make calculation before adding!",Color.RED);
        } else {
            String currentMetterNumber = countersComboBox.getValue();
            double totalBill = counterCalculator.calculateTotalBill(markupValue);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(date);
            History history = new History();
            history.setCounterNumber(currentMetterNumber);
            history.setDayTariff(tariffsOperator.getDayTariffValue(countersComboBox.getValue()));
            history.setNightTariff(tariffsOperator.getNightTariffValue(countersComboBox.getValue()));
            history.setMarkup(markupValue);
            history.setTotalBill(totalBill);
            history.setPayDate(formattedDate);
            historyDAO.add(history);
            displayMessage("Succsesfully added!", Color.GREEN.darker());
        }
        totalBill = 0;
    }
    @FXML
    public void refreshCountersComboBox(MouseEvent event){
        comboBoxObservableList.clear();
        comboBoxObservableList.addAll(countersComboBoxInitializer.getMeterNumbers());
        countersComboBox.setItems(comboBoxObservableList);
    }

}
