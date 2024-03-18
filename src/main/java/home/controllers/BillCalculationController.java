package home.controllers;

import database.DAO.history.HistoryDAO;
import database.DAO.meters.CounterDAO;
import database.entity.Counter;
import database.entity.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.CounterCalculator;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class BillCalculationController implements Initializable {
    @FXML
    private TextField dayConsumingTextField;
    @FXML
    private TextField nightConsumingTextField;
    @FXML
    private ComboBox<Counter> countersComboBox;
    @FXML
    private Label calculationResultLabel;
    private CounterCalculator counterCalculator;
    private HistoryDAO historyDAO;
    private CounterDAO counterDAO;
    private double currentTotalBill;
    private static final double DAY_TARIFF = 0.15;
    private static final double NIGHT_TARIFF = 0.1;
    private ObservableList<Counter> countersObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentTotalBill = 0;
        counterCalculator = new CounterCalculator();
        historyDAO = new HistoryDAO();
        counterDAO = new CounterDAO();
        loadCountersComboBox();
    }

    @FXML
    public void handleCalculateTotalBill(ActionEvent event) {
        if (dayConsumingTextField.getText().isEmpty() || nightConsumingTextField.getText().isEmpty()) {
            displayMessage("Please fill in all text fields!", Color.RED);
            currentTotalBill = 0;
        } else if (countersComboBox.getValue() == null) {
            displayMessage("Please choose a meter before calculation!", Color.RED);
            currentTotalBill = 0;
        } else {
            try {
                calculateTotalBill();
            } catch (NumberFormatException e) {
                displayMessage(e.getMessage(), Color.RED);
                currentTotalBill = 0;
            } catch (IllegalArgumentException e) {
                displayMessage(e.getMessage(), Color.RED);
                currentTotalBill = 0;
            }
        }
    }

    @FXML
    public void handleAddToHistory(ActionEvent event) {
        if (currentTotalBill == 0) {
            displayMessage("Please make calculation before adding!", Color.RED);
        } else {
            Counter selectedCounter = countersComboBox.getValue();
            selectedCounter.setCurrentDayConsumptionValue(Double.parseDouble(dayConsumingTextField.getText()));
            selectedCounter.setCurrentNightConsumptionValue(Double.parseDouble(nightConsumingTextField.getText()));
            counterDAO.edit(selectedCounter);
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(currentDate);
            History history = new History();
            history.setCounterNumber(selectedCounter.getCounterNumber());
            history.setCurrentDayConsumingValue(selectedCounter.getCurrentDayConsumptionValue());
            history.setCurrentNightConsumingValue(selectedCounter.getCurrentNightConsumptionValue());
            history.setTotalBill(currentTotalBill);
            history.setPayDate(formattedDate);
            historyDAO.add(history);
            displayMessage("Successfully added to history!", Color.GREEN.darker());
        }
        currentTotalBill = 0;
    }

    @FXML
    public void handleRefreshCountersComboBox(MouseEvent event) {
        loadCountersComboBox();
    }

    private void calculateTotalBill() {
        double currentDayConsumption = Double.parseDouble(dayConsumingTextField.getText());
        double currentNightConsumption = Double.parseDouble(nightConsumingTextField.getText());
        counterCalculator.setCounter(countersComboBox.getValue());
        counterCalculator.setDayTariff(DAY_TARIFF);
        counterCalculator.setNightTariff(NIGHT_TARIFF);
        counterCalculator.setCurrentDayEnergyConsumption(currentDayConsumption);
        counterCalculator.setCurrentNightEnergyConsumption(currentNightConsumption);
        currentTotalBill = counterCalculator.calculateTotalBill();
        displayMessage("Your total bill is " + currentTotalBill + " $ ", Color.BLACK);
    }

    private void displayMessage(String message, Color fontColor) {
        calculationResultLabel.setTextFill(fontColor);
        calculationResultLabel.setAlignment(Pos.CENTER);
        calculationResultLabel.setText(message);
    }

    private void loadCountersComboBox() {
        countersObservableList.clear();
        countersObservableList.addAll(counterDAO.getItems());
        countersComboBox.setItems(countersObservableList);
    }
}
