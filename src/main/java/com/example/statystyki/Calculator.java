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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Calculator {
    private long[] dataLong;
    private double[] percentTable;
    private double[] copyPercent;
    private String[] description;
    private VBox[] boxes;
    private int mod = 0;
    private boolean canRaport = false;
    private final HelloApplication application;

    private final int sum = 600;
    Calculator(HelloApplication application){
        this.application = application;
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

        percentTable = new double[dataLong.length];
        long uberSum = Arrays.stream(dataLong).sum();
        for (int i = 0; i < dataLong.length; i++)
            percentTable[i] = (double) dataLong[i]/(double) uberSum;
        try{
            copyPercent = Arrays.stream(percentTable).toArray();
            double maxD = Arrays.stream(percentTable).max().getAsDouble();

            if(maxD <= 0.30){
                for (int i = 0; i < percentTable.length; i++) copyPercent[i] *= 2.7;
                mod = 30;
            } else if(maxD <= 0.50) {
                for (int i = 0; i < percentTable.length; i++) copyPercent[i] *= 1.8;
                mod = 50;
            } else if(maxD <= 0.70){
                for (int i = 0; i < percentTable.length; i++) copyPercent[i] *= 1.3;
                mod = 70;
            }else{
                for (int i = 0; i < percentTable.length; i++) copyPercent[i] *= 0.99;
            }
        }catch (NoSuchElementException ignore){ }
    }
    private VBox[] show(){
        if(description.length == 0)
            return new VBox[]{new VBox()};
        canRaport = true;
        Random r = new Random();
        Font f = new Font("Arial", 20);
        boxes = new VBox[description.length];
        for (int i = 0; i < description.length; i++) {
            Text t = new Text(description[i] + '\n' + dataLong[i] + "\n" + String.format("%.2f",percentTable[i]*100)+"%");
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
            boxes[i].setPrefSize(1000d/boxes.length, (copyPercent[i]* sum));
            boxes[i].setMaxHeight((copyPercent[i]* sum));
            boxes[i].setMinHeight((copyPercent[i]* sum));
        }
        return boxes;
    }
    public String data(){
        if(dataLong.length == 0) return "";
        StringBuilder builder = new StringBuilder();
        long[] sorted = Arrays.stream(dataLong).sorted().toArray();

        builder.append("Średnia: ").append(Arrays.stream(dataLong).average().getAsDouble())
                .append("\nMaksymalna wartość: ").append(Arrays.stream(dataLong).max().getAsLong())
                .append("\nMinimalna wartość: ").append(Arrays.stream(dataLong).min().getAsLong())
                .append("\nSuma: ").append(Arrays.stream(dataLong).sum());

        if(sorted.length > 1){
            builder.append("\nMediana: ");
            if(sorted.length % 2 == 0)
                builder.append(((double) sorted[(sorted.length-1)/2] +
                        (double)sorted[(sorted.length-1)/2+1])/2);
            else builder.append(sorted[(sorted.length)/2]);
        }
        builder.append("\nZbiór wartości: ").append(Arrays.toString(dataLong));
        return builder.toString();
    }

    public void generateReport(){
        if(!canRaport) return;
        File file = new File("Raport-" + application.getAtr()+".txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(data());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateFile(){
        if(!canRaport) return;
        File file = new File(application.getAtr()+".txt");
        try {
            FileWriter writer = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < dataLong.length; i++)
                builder.append(description[i])
                        .append(":")
                        .append(dataLong[i])
                        .append("\n");
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public DataBox dataBox(){
        return new DataBox(boxes,  this);
    }

    public int getSum() {
        return sum;
    }
    public String getDataLong(){
        return Arrays.toString(dataLong).substring(1,Arrays.toString(dataLong).length()-1);
    }
    public String getDataString(){
        return Arrays.toString(description).substring(1,Arrays.toString(description).length()-1);
    }
    public int getMod() {
        return mod;
    }

}
