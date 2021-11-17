package com.example.statystyki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VBoxPlaceholder extends VBox {
    private final HelloApplication application;
    private final Text text = new Text();
    private final TextField field = new TextField();
    private final FileChooser fileChooser = new FileChooser();
    private final Calculator calculator;
    private Button button1 = new Button("Dodaj plik");
    VBoxPlaceholder(Calculator calculator, HelloApplication application){
        this.calculator = calculator;
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


        button1.setOnMouseClicked(v -> {
            setFile();
        });

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
                calculate(strings,longs, field.getText());

            }catch (Exception ignore){
                dataFieldLong.setBackground(new Background(
                        new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                dataFieldString.setBackground(new Background(
                        new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });

        VBox headerBox = new VBox(new HBox(field, new Text("Etykiety (oddzielone przecinkiem):"),
                dataFieldString, new Text("Dane (oddzielone przecinkiem):"),
                dataFieldLong, button, button1), text
        );
        HBox mainBox = new HBox(calculator.dataBox());
        mainBox.setAlignment(Pos.CENTER);
        getChildren().addAll(headerBox, mainBox);
    }

    /** Format pliku:
     * var:name\n
     * ........
     * var:name\n
     */
    private void setFile(){
        fileChooser.setTitle("Dodaj plik");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
        File file = fileChooser.showOpenDialog(application.getStage());
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> longs = new ArrayList<>();
        String title;
        try {
            Scanner s = new Scanner(file);
            title = file.getName().substring(0, file.getName().length()-4);
            while (s.hasNextLine()){
                String ab = s.nextLine();
                if(ab.length()<3) continue;
                String[] a = ab.split(":");
                strings.add(a[0]);
                longs.add(a[1]);
            }

            String[] a = removeWhitespace(strings.toArray(new String[0]));
            String[] b = removeWhitespace(longs.toArray(new String[0]));
            calculate(a, Arrays.stream(b).mapToLong(Long::parseLong).toArray(), title);
            s.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }

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

    private void calculate(String[] strings, long[] longs, String title){
        calculator.setData(strings, longs);
        application.setAtr(title);
        application.setNewScene();
    }
    public void setText(String text) {
        this.field.setText(text);
        this.text.setText(text);
    }
}
