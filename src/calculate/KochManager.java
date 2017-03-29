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

    private JSF31KochFractalFX application;
    public List<Edge> edges = Collections.synchronizedList(new ArrayList<>());
    private TimeStamp ts = new TimeStamp();
    public int count = 0;
    RunnableEdge left = new RunnableEdge("left", this);
    RunnableEdge right = new RunnableEdge("Right", this);
    RunnableEdge bottom = new RunnableEdge("Bottom", this);


    public KochManager(JSF31KochFractalFX application) {
        this.application = application;
        right.k.addObserver(right);
        left.k.addObserver(left);
        bottom.k.addObserver(bottom);

    }

    public void changeLevel(int nxt) throws InterruptedException {
        edges.clear();
        ts.init();
        ts.setBegin("Begin");

        left.k.setLevel(nxt);
        right.k.setLevel(nxt);
        bottom.k.setLevel(nxt);

        Thread t1 = new Thread(left);
        Thread t2 = new Thread(right);
        Thread t3 = new Thread(bottom);

        t1.setName("Left");
        t2.setName("Right");
        t3.setName("Bottom");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(this.count);

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
