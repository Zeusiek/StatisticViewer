package com.example.statystyki;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VBoxPlaceholder extends VBox {
    private HelloApplication application;
    VBoxPlaceholder(Calculator calculator, HelloApplication application){
        this.application = application;
        TextField field = new TextField("Dodaj nazwę");
        field.setMaxWidth(200);
        Button button = new Button("Przelicz");
        button.setOnMouseClicked(v -> application.setNewScene());
        HBox headerBox = new HBox(field, button);

        HBox mainBox = new HBox(calculator.dataBox());
        getChildren().addAll(headerBox, mainBox);
    }
}
