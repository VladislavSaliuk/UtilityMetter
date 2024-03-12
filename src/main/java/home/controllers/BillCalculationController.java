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
import logic.MetersComboBoxInitializer;
import logic.MetterCalculator;
import logic.TariffsOperator;

import java.net.URL;
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
    private ComboBox<String> metersComboBox;
    @FXML
    private Label calculationResultLabel;
    @FXML
    private Label markupLabel;
    private MetterCalculator meterCalculator;
    private HistoryDAO historyDAO;
    private MetersComboBoxInitializer metersComboBoxInitializer;
    private TariffsOperator tariffsOperator;
    private int markupValue;
    private double totalBill;
    private ObservableList<String> comboBoxObservableList =  FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalBill = 0;
        meterCalculator = new MetterCalculator();
        historyDAO = new HistoryDAO();
        metersComboBoxInitializer = new MetersComboBoxInitializer();
        tariffsOperator = new TariffsOperator();
        markupLabel.setText("Markup " + markupValue + "%");
        comboBoxObservableList.addAll(metersComboBoxInitializer.getMeterNumbers());
        metersComboBox.setItems(comboBoxObservableList);
    }

    @FXML
    public void calculateTotalBillAction(ActionEvent event) {
        if (dayConsumingTextField.getText().isEmpty() || nightConsumingTextField.getText().isEmpty()) {
            displayMessage("Please fill in all textfields!", Color.RED);
            totalBill = 0;
        } else if(metersComboBox.getValue() == null){
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
        String meterComboBoxCurrentValue = metersComboBox.getValue();
        double dayTariffValue = tariffsOperator.getDayTariffValue(meterComboBoxCurrentValue);
        double nightTariffValue = tariffsOperator.getNightTariffValue(meterComboBoxCurrentValue);
        double consumedEnergyDuringDayPeriod = Double.parseDouble(dayConsumingTextField.getText());
        double consumedEnergyDuringNightPeriod = Double.parseDouble(nightConsumingTextField.getText());
        meterCalculator.setDayTariff(dayTariffValue);
        meterCalculator.setNightTariff(nightTariffValue);
        meterCalculator.setDayEnergyConsumption(consumedEnergyDuringDayPeriod);
        meterCalculator.setNightEnergyConsumption(consumedEnergyDuringNightPeriod);
        totalBill = meterCalculator.calculateTotalBill(markupValue);
        String resultMessage = String.format("Your total bill count is %.2f $",totalBill);
        displayMessage(resultMessage,Color.BLACK);
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
            String currentMetterNumber = metersComboBox.getValue();
            double totalBill = meterCalculator.calculateTotalBill(markupValue);
            Date date = new Date();
            History history = new History(currentMetterNumber, totalBill, date.toString());
            historyDAO.add(history);
            displayMessage("Succsesfully added!", Color.GREEN.darker());
        }
    }


}
