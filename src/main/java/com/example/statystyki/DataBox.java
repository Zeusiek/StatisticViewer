package com.example.statystyki;

import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

import java.util.Arrays;

public class DataBox extends VBox {
    DataBox(VBox[] boxes, Calculator c){
        HBox box = new HBox();

        setAlignment(Pos.CENTER_LEFT);
        Dimension2D d = new Dimension2D(1050, c.getSum()+50);

        int selfMod = (c.getMod() != 0 ? c.getMod() : 100);

        VBox[] scaleBoxes = switch (selfMod){
            case 30 -> new VBox[4];
            case 50 -> new VBox[6];
            case 70 -> new VBox[8];
            default -> new VBox[11];
        };
        int scales = (int) d.getHeight()/scaleBoxes.length;

        Border b =new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY,
                new BorderWidths(2),
                Insets.EMPTY)
        );
        for (int i = scaleBoxes.length-1; i >= 0; i--){
            scaleBoxes[i] = new VBox(new Text(String.valueOf((selfMod-(i*10)))+'%'));
            scaleBoxes[i].setAlignment(Pos.BOTTOM_CENTER);
            scaleBoxes[i].setPrefSize(50,scales);
            scaleBoxes[i].setMaxSize(50,scales);
            scaleBoxes[i].setMinSize(50,scales);
            scaleBoxes[i].setBorder(b);
        }
        VBox scale = new VBox(scaleBoxes);
        scale.setBackground(new Background(new BackgroundFill(
                Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        scale.setAlignment(Pos.BOTTOM_CENTER);
        scale.setStyle("-fx-border-style: none none none none; " +
                "-fx-border-width: 1; -fx-border-color: black;"
        );
        Button btn = new Button("Generuj raport");
        btn.setOnMouseClicked(v -> c.generateReport());
        Button genFile = new Button("Generuj plik");
        genFile.setOnMouseClicked(v -> c.generateFile());

        VBox dataBox = new VBox(new Text("DANE:\n" + c.data()));
        dataBox.setMinSize(220,250);
        dataBox.setMaxSize(220,250);
        VBox.setMargin(dataBox, new Insets(10,10,10,10));
        dataBox.getChildren().addAll(btn, genFile);

        HBox h = new HBox(scale);
        h.getChildren().addAll(boxes);
        h.setPrefSize(d.getWidth(),d.getHeight());
        h.setBackground(new Background(new BackgroundFill(
                Color.color(0.2,0.2,0.2), CornerRadii.EMPTY, Insets.EMPTY))
        );
        h.setAlignment(Pos.BOTTOM_LEFT);
        box.getChildren().addAll(dataBox,h);

        getChildren().addAll(box);
        setAlignment(Pos.CENTER);
    }
}
