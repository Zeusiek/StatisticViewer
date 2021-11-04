package com.example.statystyki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Random;

public class Calculator {
    private long[] dataInt;
    private long maxNumb;
    private int sum = 400;
    private double[] percentTable;
    private String[] description;
    private VBox[] boxes;

    Calculator(){
        setData(new String[]{"Jeden","Dwa","Trzy"},
                new long[]{1,2,3}
        );
    }
    public void setData(String[] description, long[] data){
        if(description.length != data.length)
            throw new ArrayStoreException("Arrays are not the same length!");
        this.description = description;
        this.dataInt = data;
        maxNumb = Arrays.stream(dataInt).max().getAsLong();
        calculate();
        boxes = show();
    }

    private void calculate(){
        percentTable = new double[dataInt.length];
        for (int i = 0; i < dataInt.length; i++)
            percentTable[i] = (double) dataInt[i]/(double) maxNumb;

    }
    private VBox[] show(){
        Random r = new Random();
        boxes = new VBox[description.length];
        Font f = new Font("Arial", 10d);
        for (int i = 0; i < description.length; i++) {
            Text t = new Text(description[i]);
            t.setFont(f);
            boxes[i] = new VBox();
            boxes[i].setAlignment(Pos.CENTER);
            boxes[i].setBackground(new Background(
                    new BackgroundFill(
                            Color.color(r.nextDouble(),r.nextDouble(),r.nextDouble()),
                    CornerRadii.EMPTY, Insets.EMPTY))
            );
            boxes[i].setPrefSize(1000d/boxes.length, (percentTable[i]*400));
            boxes[i].setMaxHeight((percentTable[i]*400));
            boxes[i].setMinHeight((percentTable[i]*400));
        }
        return boxes;
    }
    public DataBox dataBox(String description){
        return new DataBox(boxes, description);
    }
    public DataBox dataBox(){
        return new DataBox(boxes, "TEST");
    }
}
