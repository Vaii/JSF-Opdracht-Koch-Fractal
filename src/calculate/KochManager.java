package calculate;

import com.company.Observer;
import javafx.application.Platform;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class KochManager {

    public JSF31KochFractalFX getApplication() {
        return application;
    }

    public void setApplication(JSF31KochFractalFX application) {
        this.application = application;
    }

    private JSF31KochFractalFX application;
    private ArrayList<Edge> edges = new ArrayList<>();
    private TimeStamp ts = new TimeStamp();
    private int count = 0;
    RunnableEdge left = new RunnableEdge("left", this);
    RunnableEdge right = new RunnableEdge("Right", this);
    RunnableEdge bottom = new RunnableEdge("Bottom", this);

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public synchronized void addEdges(Edge e){
        edges.add(e);
    }

    public KochManager(JSF31KochFractalFX application) {
        this.application = application;
    }

    public void changeLevel(int nxt) throws InterruptedException {
        edges.clear();
        setCount(0);
        ts.init();
        ts.setBegin("Begin");

        left.k.setLevel(nxt);
        right.k.setLevel(nxt);
        bottom.k.setLevel(nxt);

        Thread t1 = new Thread(left);
        Thread t2 = new Thread(right);
        Thread t3 = new Thread(bottom);

        t1.setName(left.getEdgeName());
        t2.setName(right.getEdgeName());
        t3.setName(bottom.getEdgeName());

        t1.start();
        t2.start();
        t3.start();

        ts.setEnd("Eind");

        application.setTextCalc(ts.toString());


    }

    public void drawEdges() {
        ts.init();
        application.clearKochPanel();
        application.setTextNrEdges(Integer.toString(left.k.getNrOfEdges()));

        ts.setBegin();

        for(Edge x : edges){
                application.drawEdge(x);
        }

        ts.setEnd();
        application.setTextDraw(ts.toString());

    }

    public synchronized void finished(){
        count++;
    }

}
