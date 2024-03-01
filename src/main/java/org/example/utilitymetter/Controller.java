package org.example.utilitymetter;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.utilitymetter.initializators.ComboBoxInitializer;
import org.example.utilitymetter.initializators.ComboBoxInitializerWithStringElements;

import java.util.List;

public class Controller {
    @FXML
    private Button addToHistoryButton;

    @FXML
    private Button calculateBillButton;

    @FXML
    private TextField currentIndicatorTextField;

    @FXML
    private TextField previousIndicatorTextField;

    @FXML
    private TextField tariffTextField;

    @FXML
    private ComboBox<String> utilityComboBox;
    private ComboBoxInitializerWithStringElements comboBoxInitializer;

    @FXML
    void initialize(){

    }

}