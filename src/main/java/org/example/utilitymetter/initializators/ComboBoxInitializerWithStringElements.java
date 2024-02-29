package org.example.utilitymetter.initializators;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.List;

public class ComboBoxInitializerWithStringElements extends ComboBoxInitializer{
    private List<String>  stringElementsArray;

    public ComboBoxInitializerWithStringElements() {

    }
    public ComboBoxInitializerWithStringElements(ComboBox comboBoxForInitializing, List<String> stringElementsArray) {
        super(comboBoxForInitializing);
        this.stringElementsArray = stringElementsArray;
    }

    public void initializeComoBox(int startStringElement){
        this.comboBoxForInitializing.setValue(stringElementsArray.get(startStringElement));
        this.comboBoxForInitializing.setItems(FXCollections.observableArrayList(stringElementsArray));
    }
    public List<String> getStringElementsArray() {
        return stringElementsArray;
    }

    public void setStringElementsArray(List<String> stringElementsArray) {
        this.stringElementsArray = stringElementsArray;
    }
}
