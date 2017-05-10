package com.company;

import calculate.KochFractal;
import calculate.KochManager;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Observer observer = new Observer();
        KochFractal kochFractal = new KochFractal();

        kochFractal.addObserver(observer);

        kochFractal.setLevel(2);
        kochFractal.generateBottomEdge();
        kochFractal.generateLeftEdge();
        kochFractal.generateRightEdge();


    }
}
