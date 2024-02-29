package org.example.utilitymetter.initializators;

import javafx.scene.control.ComboBox;

import java.util.List;

public abstract class ComboBoxInitializer {

    protected ComboBox comboBoxForInitializing;

    public ComboBoxInitializer() {

    }
    public ComboBoxInitializer(ComboBox comboBoxForInitializing) {
        this.comboBoxForInitializing = comboBoxForInitializing;
    }

    public ComboBox getComboBoxForInitializing() {
        return comboBoxForInitializing;
    }

    public void setComboBoxForInitializing(ComboBox comboBoxForInitializing) {
        this.comboBoxForInitializing = comboBoxForInitializing;
    }
}
