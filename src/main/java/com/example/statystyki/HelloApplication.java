package com.example.statystyki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private final Calculator calculator = new Calculator();
    private VBoxPlaceholder box;
    private Scene s;
    private Stage stage;
    private String desc;
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

        desc = calculator.returnDescription();
        box = new VBoxPlaceholder(calculator, this, desc);

        s = new Scene(box, 1050,700);
        VBox g = new VBox();
        g.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        box.getChildren().add(g);
        stage.setScene(s);
    }

    public static void main(String[] args) {
        launch();
    }
}