package calculate;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;

/**
 * Created by Vai on 3/29/17.
 */
public class CallableEdge implements Callable<ArrayList<Edge>>, Observer {

    private ArrayList<Edge> edges;
    public KochFractal k;
    private int edgeSide;

    public CallableEdge(int edgeSide)
    {
        edges = new ArrayList<>();
        k = new KochFractal();
        this.edgeSide = edgeSide;
    }


    @Override
    public ArrayList<Edge> call() throws Exception {

        edges.clear();

        if(edgeSide == 0){
            k.generateBottomEdge();

            return edges;
        }
        else if(edgeSide == 1){
            k.generateLeftEdge();
            return edges;
        }
        else if(edgeSide == 2){
            k.generateRightEdge();
            return edges;
        }

        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        edges.add((Edge)arg);
    }
}
