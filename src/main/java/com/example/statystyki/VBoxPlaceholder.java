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
    private final Text text = new Text();
    private final TextField field = new TextField();
    VBoxPlaceholder(Calculator calculator, HelloApplication application){
        this.application = application;
        field.setMaxWidth(200);

        TextField dataFieldString = new TextField(calculator.getDataString());
        dataFieldString.setAlignment(Pos.CENTER);
        dataFieldString.setMinWidth(200);
        dataFieldString.setOpaqueInsets(new Insets(10,10,10,10));

        TextField dataFieldLong = new TextField(calculator.getDataLong());
        dataFieldLong.setAlignment(Pos.CENTER);
        dataFieldLong.setMinWidth(200);
        dataFieldLong.setOpaqueInsets(new Insets(10,10,10,10));



        Button button = new Button("Przelicz");
        button.setOnMouseClicked(v -> {
            try{
                dataFieldLong.setBackground(new Background(
                        new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                dataFieldString.setBackground(new Background(
                        new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                String[] strings = removeWhitespace(dataFieldString.getText().split(","));
                long[] longs = Arrays.stream(removeWhitespace(
                        dataFieldLong.getText().split(",")))
                        .mapToLong(Long::parseLong).toArray();
                calculator.setData(strings, longs);
                application.setAtr(field.getText());
                application.setNewScene();

            }catch (Exception ignore){
                dataFieldLong.setBackground(new Background(
                        new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                dataFieldString.setBackground(new Background(
                        new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });

        VBox headerBox = new VBox(new HBox(field, new Text("Etykiety (oddzielone przecinkiem):"),
                dataFieldString, new Text("Dane (oddzielone przecinkiem):"),
                dataFieldLong, button), text
        );
        HBox mainBox = new HBox(calculator.dataBox());
        mainBox.setAlignment(Pos.CENTER);
        getChildren().addAll(headerBox, mainBox);
    }

    private String[] removeWhitespace(String[] s){
        for (int i = 0; i < s.length; i++) {
            StringBuilder a = new StringBuilder();
            for (int j = 0; j < s[i].length(); j++) {
                if(Character.isDigit(s[i].charAt(j))) a.append(s[i].charAt(j));
                if(Character.isAlphabetic(s[i].charAt(j))) a.append(s[i].charAt(j));
            }
            s[i] = a.toString();
        }
        return s;
    }

    public void setText(String text) {
        this.field.setText(text);
        this.text.setText(text);
    }
}
