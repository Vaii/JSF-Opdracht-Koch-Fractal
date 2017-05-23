package com.company;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 *
 * @author Peter Boots
 */
public class Edge implements Serializable {
    public double X1, Y1, X2, Y2;
    public Color color;

    public Edge(double X1, double Y1, double X2, double Y2, Color color) {
        this.X1 = X1;
        this.Y1 = Y1;
        this.X2 = X2;
        this.Y2 = Y2;
        this.color = color;
    }

    public Edge(SerializableEdges se){
        this.X1 = se.X1;
        this.Y1 = se.Y1;
        this.X2 = se.X2;
        this.Y2 = se.Y2;
        this.color = new Color(se.r, se.g, se.b,1);
    }

    @Override
    public String toString() {
        return X1 + "," + Y1 + "," + X2 + "," + Y2 + "," + color;
    }
}
