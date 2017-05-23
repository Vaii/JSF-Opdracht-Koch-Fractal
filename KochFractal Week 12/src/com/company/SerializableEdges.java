package com.company;

import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by Daniel on 20-5-2017.
 */
public class SerializableEdges implements Serializable {

    public double X1, Y1, X2, Y2;
    public double r,g,b;

    public SerializableEdges(Edge e){
        this.X1 = e.X1;
        this.Y1 = e.Y1;
        this.X2 = e.X2;
        this.Y2 = e.Y2;

        this.r = e.color.getRed();
        this.g = e.color.getGreen();
        this.b = e.color.getBlue();
    }

    @Override
    public String toString(){
        return X1 +" "+ Y1 +" "+ X2 +" "+ Y2 +" "+ r +" "+ g +" "+ b ;
    }
}
