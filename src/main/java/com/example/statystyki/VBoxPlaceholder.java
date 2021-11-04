package com.example.statystyki;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VBoxPlaceholder extends VBox {
    VBoxPlaceholder(Calculator calculator){
        super(new HBox(new Text("Dodaj nazwę")),calculator.dataBox());
    }
}
