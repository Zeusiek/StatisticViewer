package com.example.statystyki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

import java.util.Arrays;

public class DataBox extends VBox {

    DataBox(VBox[] boxes, String description){
        HBox box = new HBox();

        VBox[] scaleBoxes = new VBox[11];
        for (int i = scaleBoxes.length-1; i >= 0; i--){
            scaleBoxes[i] = new VBox(new Text(String.valueOf((100-(i*10)))+'%'));
            scaleBoxes[i].setAlignment(Pos.BOTTOM_CENTER);
            scaleBoxes[i].setPrefSize(50,55);
            scaleBoxes[i].setMaxSize(50,55);
            scaleBoxes[i].setMinSize(50,55);
            scaleBoxes[i].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        VBox scale = new VBox(scaleBoxes);
        scale.setAlignment(Pos.BOTTOM_CENTER);

        HBox h = new HBox(scale);
        h.getChildren().addAll(boxes);
        h.setPrefSize(1050,500);
        h.setBackground(new Background(new BackgroundFill(
                Color.color(0.2,0.2,0.2), CornerRadii.EMPTY, Insets.EMPTY))
        );
        h.setAlignment(Pos.BOTTOM_LEFT);
        box.getChildren().add(h);

        getChildren().addAll(new Text(description), box);
        setAlignment(Pos.CENTER);
    }


}
