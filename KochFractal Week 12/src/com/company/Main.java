package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welk level?");
        int level = scanner.nextInt();

        KochFractal koch = new KochFractal();
        koch.setLevel(level);

        koch.generateBottomEdge();
        koch.generateLeftEdge();
        koch.generateRightEdge();

        FileOutputStream fos = new FileOutputStream("kochfr.ser");
        ObjectOutputStream ous = new ObjectOutputStream(fos);
        ous.writeObject(koch);

    }
}
