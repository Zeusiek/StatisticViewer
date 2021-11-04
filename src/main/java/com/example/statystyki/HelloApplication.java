package com.example.statystyki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Calculator calculator = new Calculator();
    VBox box;
    Scene s;
    Stage stage;
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        setNewScene();
        stage.setScene(s);
        stage.setTitle("Statystyki");
        stage.setResizable(false);
        stage.show();

    }
    protected void setNewScene(){
        box = new VBoxPlaceholder(calculator, this);
        s = new Scene(box, 1050,500);
        calculator.setData(new String[]{"Jeden","Dwa","Trzy", "cztert"},
                new long[]{1,2,3, 6});
        stage.setScene(s);
    }

    public static void main(String[] args) {
        launch();
    }
}