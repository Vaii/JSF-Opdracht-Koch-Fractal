package com.company;

import timeutil.TimeStamp;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Daniel on 20-5-2017.
 */
public class Writer implements Observer {

    ArrayList<Edge> Edges = new ArrayList<>();
    ArrayList<SerializableEdges> EdgesToSerialize = new ArrayList<>();
    TimeStamp ts = new TimeStamp();

    public void write() throws IOException {
        ts.init();
        EdgesToSerialize.clear();
        EdgesToSerialize = createdata();

        FileOutputStream fos = new FileOutputStream("kochfr.ser");
        ObjectOutputStream ous = new ObjectOutputStream(fos);

        ts.setBegin("write start without buffer");
        ous.writeObject(EdgesToSerialize);
        ts.setEnd("write end without buffer");
        System.out.println(ts.toString());
    }

    public void writeText() throws IOException {

        ts.init();
        EdgesToSerialize = createdata();
        FileWriter fw = new FileWriter("kochfr.txt");
        PrintStream ps = new PrintStream("kochfr.txt");

        ts.setBegin("writetext start without buffer");
        for(Edge e : Edges){
            //fw.write(e.toString() + "\n");
            ps.println(e.toString());
        }
        ts.setEnd("writetext end without buffer");
        System.out.println(ts.toString());
    }

    public void writeWithBuffer() throws IOException {
        ts.init();
        EdgesToSerialize.clear();
        EdgesToSerialize = createdata();

        FileOutputStream fos = new FileOutputStream("kochfr.ser");
        ObjectOutputStream ous = new ObjectOutputStream( new BufferedOutputStream(fos));
        ts.setBegin("start write with buffer");
        ous.writeObject(EdgesToSerialize);
        ts.setEnd("end write with buffer");

        System.out.println(ts.toString());
    }

    public void writeTextWithBuffer() throws IOException {
        ts.init();
        EdgesToSerialize = createdata();
        FileWriter fw = new FileWriter("kochfr.txt");
        PrintWriter pw = new PrintWriter( new BufferedWriter(fw));
        ts.setBegin("start write text with buffer");
        for(Edge e : Edges){
            //fw.write(e.toString() + "\n");
            pw.println(e.toString());
        }
        ts.setEnd("end write text with buffer");
        System.out.println(ts.toString());
    }

    public void writeToMappedFile() throws IOException {
        RandomAccessFile ras;
        ras = new RandomAccessFile("mappedkochfr.ser","rw");
        FileChannel fc = ras.getChannel();
        MappedByteBuffer out;
        out = fc.map(FileChannel.MapMode.READ_WRITE,0,8*7*Edges.size());

        for(Edge e : Edges){
            out.putDouble(e.X1);
            out.putDouble(e.Y1);
            out.putDouble(e.X2);
            out.putDouble(e.Y2);
            out.putDouble(e.color.getRed());
            out.putDouble(e.color.getGreen());
            out.putDouble(e.color.getBlue());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Edges.add((Edge)arg);
    }

    public ArrayList<SerializableEdges> createdata(){
        ArrayList<SerializableEdges>Edgedata = new ArrayList<>();
        for (Edge e : Edges){
            SerializableEdges edgedata = new SerializableEdges(e);
            Edgedata.add(edgedata);
        }
        return Edgedata;
    }
}
