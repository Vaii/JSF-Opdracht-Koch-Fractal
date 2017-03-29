package calculate;

import com.company.Observer;
import javafx.application.Platform;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.*;

public class KochManager {

    private JSF31KochFractalFX application;
    public List<Edge> edges = Collections.synchronizedList(new ArrayList<>());
    private TimeStamp ts = new TimeStamp();
    public int count = 0;
    ExecutorService pool = Executors.newFixedThreadPool(3);
    CallableEdge left = new CallableEdge(1);
    CallableEdge right = new CallableEdge(2);
    CallableEdge bottom = new CallableEdge(0);



    public KochManager(JSF31KochFractalFX application) {
        this.application = application;
        left.k.addObserver(left);
        right.k.addObserver(right);
        bottom.k.addObserver(bottom);

    }

    public void changeLevel(int nxt) throws InterruptedException, ExecutionException {
        edges.clear();
        ts.init();
        ts.setBegin("Begin");

        left.k.setLevel(nxt);
        right.k.setLevel(nxt);
        bottom.k.setLevel(nxt);

        try{

        Future<ArrayList<Edge>> futLeft = pool.submit(left);
        Future<ArrayList<Edge>> futRight = pool.submit(right);
        Future<ArrayList<Edge>> futBottom = pool.submit(bottom);


            edges.addAll(futLeft.get());
            edges.addAll(futRight.get());
            edges.addAll(futBottom.get());
        }

        catch(ExecutionException e){
            System.out.println(e.getMessage().toString());
        }



        ts.setEnd("Eind");

        application.setTextCalc(ts.toString());

        application.requestDrawEdges();

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

}
