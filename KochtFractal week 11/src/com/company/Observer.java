package com.company;

import calculate.Edge;

import java.net.SocketOption;
import java.net.SocketPermission;
import java.util.Observable;

/**
 * Created by Daniel on 15-3-2017.
 */
public class Observer implements java.util.Observer {
    @Override
    public void update(Observable o, Object arg) {
        Edge e = (Edge) arg;

        System.out.println("Edge1" +e.X1+ " "+ e.Y1 +" "+ e.X2+" " +e.Y2);

    }

}
