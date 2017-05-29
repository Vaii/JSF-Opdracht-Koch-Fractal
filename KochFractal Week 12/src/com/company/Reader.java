package com.company;

import javafx.scene.paint.Color;
import timeutil.TimeStamp;

import java.io.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Daniel on 20-5-2017.
 */
public class Reader {
    FileInputStream fis ;
    ObjectInputStream ois ;
    ArrayList<Edge> edges2 = new ArrayList<>();
    TimeStamp ts = new TimeStamp();
    public ArrayList<Edge> getEdges2() {
        return edges2;
    }

    public void readFile(File file) throws IOException, ClassNotFoundException {
        ts.init();
        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);

        ts.setBegin("Begin read without buffer");
        ArrayList<SerializableEdges>edges = new ArrayList<>();
        edges = (ArrayList<SerializableEdges>) ois.readObject();
        ts.setEnd("End Read without buffer");
        System.out.println(ts.toString());

        for (SerializableEdges e : edges){
            Edge edge = new Edge(e);
            edges2.add(edge);
        }
    }

    public void readTextFile(File file) throws FileNotFoundException {
        ts.init();
        Scanner scanner = new Scanner(file);
        ts.setBegin("Start Text without buffer");
        try{
            while(scanner.hasNextLine()){
                String[]edgeinfo = scanner.next().split(",");
                Edge e = new Edge(Double.parseDouble(edgeinfo[0]), Double.parseDouble(edgeinfo[1]), Double.parseDouble(edgeinfo[2]), Double.parseDouble(edgeinfo[3]), Color.web(edgeinfo[4]));
                edges2.add(e);
            }
            ts.setEnd("End Text without buffer");
            System.out.println(ts.toString());
        }
        catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    public void readFileWithBuffer(File file) throws IOException, ClassNotFoundException {
        ts.init();
        fis = new FileInputStream(file);
        ois = new ObjectInputStream(new BufferedInputStream(fis));

        ts.setBegin("Begin read with buffer");
        ArrayList<SerializableEdges>edges = new ArrayList<>();
        edges = (ArrayList<SerializableEdges>) ois.readObject();
        ts.setEnd("End Read with buffer");
        System.out.println(ts.toString());

        for (SerializableEdges e : edges){
            Edge edge = new Edge(e);
            edges2.add(edge);
        }
    }

    public void readTextFileWithBuffer(File file) throws FileNotFoundException {
        ts = new TimeStamp();
        ts.init();
        Scanner scanner = new Scanner( new BufferedReader(new FileReader(file)));
        try{
            ts.setBegin("start Read Text with buffer");
            while(scanner.hasNextLine()){
                String[]edgeinfo = scanner.next().split(",");
                Edge e = new Edge(Double.parseDouble(edgeinfo[0]), Double.parseDouble(edgeinfo[1]), Double.parseDouble(edgeinfo[2]), Double.parseDouble(edgeinfo[3]), Color.web(edgeinfo[4]));
                edges2.add(e);
            }
        }
        catch (NoSuchElementException e){
            ts.setEnd("end Read Text with buffer");
            System.out.println("-->" + ts.toString());
            System.out.println(e.getMessage());
        }
    }
}
