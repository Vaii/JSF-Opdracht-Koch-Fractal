package calculate;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Vai on 3/30/17.
 */
public class CallableEdge implements Observer, Callable {

    private KochFractal k;
    private ArrayList<Edge> edges;
    private String edgeSide;
    private CountDownLatch latch;

    public CallableEdge(String edgeSide, CountDownLatch latch){
        k = new KochFractal();
        this.edgeSide = edgeSide;
        edges = new ArrayList<>();
        k.addObserver(this);
        this.latch = latch;
    }

    public KochFractal getK() {
        return k;
    }

    public void setK(KochFractal k) {
        this.k = k;
    }

    public String getEdgeSide() {
        return edgeSide;
    }

    public void setEdgeSide(String edgeSide) {
        this.edgeSide = edgeSide;
    }

    @Override
    public void update(Observable o, Object arg) {
        edges.add((Edge)arg);
    }

    @Override
    public Object call() throws Exception {
        edges.clear();
        if(edgeSide.equals("Left")){
            k.generateLeftEdge();
            latch.countDown();
            return edges;
        }
        else if(edgeSide.equals("Bottom")){
            k.generateBottomEdge();
            latch.countDown();
            return edges;
        }
        else if(edgeSide.equals("Right")){
            k.generateRightEdge();
            latch.countDown();
            return edges;
        }
        return null;
    }
}
