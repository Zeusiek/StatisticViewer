package com.example.statystyki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Arrays;

public class VBoxPlaceholder extends VBox {
    private final HelloApplication application;
    TextField field;

    VBoxPlaceholder(Calculator calculator, HelloApplication application, String s){
        field = new TextField(s);
        this.application = application;
        field.setMaxWidth(200);

        TextField dataFieldString = new TextField(calculator.getDataString());
        dataFieldString.setAlignment(Pos.CENTER);
        dataFieldString.setMinWidth(200);

        TextField dataFieldLong = new TextField(calculator.getDataLong());
        dataFieldLong.setAlignment(Pos.CENTER);
        dataFieldLong.setMinWidth(200);

        VBox whiteBox = new VBox();
        whiteBox.setMinSize(20,20);
        VBox whiteBox1 = new VBox();
        whiteBox1.setMinSize(20,20);


        Button button = new Button("Przelicz");
        button.setOnMouseClicked(v -> {
            try{
                dataFieldLong.setBackground(new Background(
                        new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                dataFieldString.setBackground(new Background(
                        new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                calculator.setData(
                        dataFieldString.getText().split(","),
                        Arrays.stream(
                                        dataFieldLong
                                                .getText()
                                                .split(","))
                                .mapToLong(Long::parseLong)
                                .toArray()
                );
                application.setNewScene();

            }catch (Exception ignored){
                dataFieldLong.setBackground(new Background(
                        new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                dataFieldString.setBackground(new Background(
                        new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });

        HBox headerBox = new HBox(field, whiteBox,
                new Text("Etykiety (oddzielone przecinkiem):"),
                dataFieldString, whiteBox1,
                new Text("Dane (oddzielone przecinkiem):"),
                dataFieldLong, button
        );

        HBox mainBox = new HBox(calculator.dataBox(field.getText()));
        mainBox.setAlignment(Pos.CENTER);
        getChildren().addAll(headerBox, mainBox);
    }
    public TextField getField(){
        return field;
    }
}
