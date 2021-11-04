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
    @Override
    public void start(Stage stage) {
        Calculator calculator = new Calculator();
        calculator.setData(
                new String[]{"test","test","test2","test2", "test3", "test4","test5", "test5","test5"},
                new long[]{79,3,9,60,92,43,41,32, 6}
        );
        box = new VBoxPlaceholder(calculator);
        s = new Scene(box, 1050,500);
        stage.setScene(s);
        stage.setTitle("Statystyki");
        stage.setResizable(false);
        stage.show();
    }
    private void setNewScene(){

    }

    public static void main(String[] args) {
        launch();
    }
}