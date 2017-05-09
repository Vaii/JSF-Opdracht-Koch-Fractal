package calculate;

import com.company.Observer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.*;

public class KochManager {

    public JSF31KochFractalFX getApplication() {
        return application;
    }

    public void setApplication(JSF31KochFractalFX application) {
        this.application = application;
    }

    private JSF31KochFractalFX application;
    private ArrayList<Edge> edges = new ArrayList<>();

    public TimeStamp getTsReken() {
        return tsReken;
    }

    public void setTsReken(TimeStamp tsReken) {
        this.tsReken = tsReken;
    }

    private TimeStamp tsReken = new TimeStamp();
    private TimeStamp tsTeken = new TimeStamp();
    private int count = 0;
    private ExecutorService pool = Executors.newFixedThreadPool(3);
    private KochFractal koch;

    //three edge tasks
    private Task taskLeft = null;
    private Task taskBottom = null;
    private Task taskRight = null;


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
        this.koch = new KochFractal();

    }

    public int getLevel(){
        return koch.getLevel();
    }


    public void changeLevel(int nxt) throws InterruptedException {
        edges.clear();
        koch.setLevel(nxt);

        if(taskLeft != null && taskRight != null && taskBottom != null ){
            taskRight.cancel();
            taskBottom.cancel();
            taskLeft.cancel();
        }

        createAllTasks();
        startTasks();
    }

    public void startTasks(){

        Thread thLeft = new Thread(taskLeft);
        Thread thRight = new Thread(taskRight);
        Thread thBottom = new Thread(taskBottom);

        tsReken.init();
        tsReken.setBegin("StartReken");
        pool.submit(thLeft);
        pool.submit(thRight);
        pool.submit(thBottom);
    }

    public void drawEdges() {

        tsReken.setEnd("EindReken");
        application.setTextCalc(tsReken.toString());

        application.setTextCalc(tsReken.toString());

        tsTeken.init();
        application.clearKochPanel();
        application.setTextNrEdges(Integer.toString(koch.getNrOfEdges()));

        tsTeken.setBegin("Begin");

        for(Edge x : edges){
                application.drawEdge(x);
        }

        tsTeken.setEnd("Eind");
        application.setTextDraw(tsTeken.toString());

    }

    public void drawEdge(Edge e){
        application.drawWhiteEdge(e);
    }

    public synchronized void addEdge(Edge e){
        edges.add(e);
    }

    public synchronized void finished() throws ExecutionException, InterruptedException {

        count++;

        if(count >= 3){
            application.requestDrawEdges();
            setCount(0);
        }
    }

    public void createAllTasks(){


        tsReken.init();
        if(taskLeft != null){
            application.getLeftProgress().progressProperty().unbind();
            application.getProgressNrEdgesLeft().textProperty().unbind();
        }
        if(taskRight != null){
            application.getRightProgress().progressProperty().unbind();
            application.getProgressNrEdgesRight().textProperty().unbind();
        }
        if(taskBottom != null){
            application.getBottomProgress().progressProperty().unbind();
            application.getProgressNrEdgesBottom().textProperty().unbind();
        }

        taskLeft = new CalculateTask("Left", this);
        taskRight = new CalculateTask("Right", this);
        taskBottom = new CalculateTask("Bottom", this);

        application.getLeftProgress().setProgress(0);
        application.getLeftProgress().progressProperty().bind(taskLeft.progressProperty());
        application.getProgressNrEdgesLeft().textProperty().bind(taskLeft.messageProperty());

        application.getRightProgress().setProgress(0);
        application.getRightProgress().progressProperty().bind(taskRight.progressProperty());
        application.getProgressNrEdgesRight().textProperty().bind(taskRight.messageProperty());

        application.getBottomProgress().setProgress(0);
        application.getBottomProgress().progressProperty().bind(taskBottom.progressProperty());
        application.getProgressNrEdgesBottom().textProperty().bind(taskBottom.messageProperty());
    }

}
