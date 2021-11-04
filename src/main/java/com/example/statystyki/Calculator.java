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
import java.util.Random;

public class Calculator {
    private long[] dataLong;
    private long maxNumb;
    private int sum = 550;
    private long uberSum = 0;
    private double[] percentTable;
    private String[] description;
    private VBox[] boxes;
    private String atr = "Nazwa";

    public String returnDescription(){
        return atr;
    }
    public String getDataLong(){
        return Arrays.toString(dataLong).substring(1,Arrays.toString(dataLong).length()-1);
    }
    public String getDataString(){
        return Arrays.toString(description).substring(1,Arrays.toString(description).length()-1);
    }

    Calculator(){
        setData(new String[]{}, new long[]{});
    }
    public void setData(String[] description, long[] data){
        if(description.length != data.length)
            throw new ArrayStoreException("Arrays are not the same length!");
        this.description = description;
        this.dataLong = data;
        calculate();
        boxes = show();
    }

    private void calculate(){
        if(dataLong.length == 0) maxNumb = 1;
        else maxNumb = Arrays.stream(dataLong).max().getAsLong();
        percentTable = new double[dataLong.length];
        uberSum = Arrays.stream(dataLong).sum();
        for (int i = 0; i < dataLong.length; i++)
            percentTable[i] = (double) dataLong[i]/(double) uberSum;

    }
    private VBox[] show(){
        if(description.length == 0)
            return new VBox[]{new VBox()};
        Random r = new Random();
        Font f = new Font("Arial", 20);
        boxes = new VBox[description.length];
        for (int i = 0; i < description.length; i++) {
            Text t = new Text(description[i] + '\n' + dataLong[i]);
            t.setFont(f);
            boxes[i] = new VBox(t);
            boxes[i].setAlignment(Pos.CENTER);
            boxes[i].setBackground(new Background(new BackgroundFill(
                            Color.rgb(
                                    r.nextInt(100)+150,
                                    r.nextInt(100)+150,
                                    r.nextInt(100)+150),
                    CornerRadii.EMPTY, Insets.EMPTY))
            );
            boxes[i].setPrefSize(1000d/boxes.length, (percentTable[i]*sum));
            boxes[i].setMaxHeight((percentTable[i]*sum));
            boxes[i].setMinHeight((percentTable[i]*sum));
        }
        return boxes;
    }
    public DataBox dataBox(String description){
        atr = description;
        return new DataBox(boxes, description);
    }
    public DataBox dataBox(){
        return new DataBox(boxes, "TEST");
    }
}
