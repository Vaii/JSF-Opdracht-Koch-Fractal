package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welk level?");
        int level = scanner.nextInt();

        KochFractal koch = new KochFractal();
        koch.setLevel(level);

    }
}
