package com.example.statystyki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

public class DataBox extends VBox {

    DataBox(VBox[] boxes, String description){
        HBox box = new HBox();

        VBox[] scaleBoxes = new VBox[21];
        for (int i = 0; i < scaleBoxes.length; i++){
            scaleBoxes[i] = new VBox(new Text(String.valueOf(i*5)));
            scaleBoxes[i].setAlignment(Pos.CENTER);
            scaleBoxes[i].setPrefSize(50,30);
            scaleBoxes[i].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        VBox scale = new VBox();
        scale.setAlignment(Pos.BOTTOM_LEFT);
        scale.getChildren().addAll(scaleBoxes);

        HBox h = new HBox(scale);
        h.getChildren().addAll(boxes);
        h.setPrefSize(1050,450);
        h.setBackground(new Background(new BackgroundFill(Color.color(0.2,0.2,0.2), CornerRadii.EMPTY, Insets.EMPTY)));
        h.setAlignment(Pos.BOTTOM_LEFT);
        box.getChildren().add(h);

        getChildren().addAll(new Text(description), box);
    }


}
