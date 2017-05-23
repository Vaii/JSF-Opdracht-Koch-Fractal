package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Writer writer = new Writer();
        KochFractal koch = new KochFractal();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welk level?");
        int level = scanner.nextInt();

        koch.addObserver(writer);
        koch.setLevel(level);

        koch.generateBottomEdge();
        koch.generateLeftEdge();
        koch.generateRightEdge();

        // writer.write();
         writer.writeText();
        // writer.writeWithBuffer();
        // writer.writeTextWithBuffer();
    }
}
