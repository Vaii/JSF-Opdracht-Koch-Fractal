package calculate;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Vai on 3/22/17.
 */
public class RunnableEdge implements Runnable, Observer {
    public String getEdgeName() {
        return edgeName;
    }

    public void setEdgeName(String edgeName) {
        this.edgeName = edgeName;
    }

    private String edgeName;
    KochFractal k;
    KochManager kochManager;

    public RunnableEdge(String edgeName, KochManager kochManager){
        this.edgeName = edgeName;
        k = new KochFractal();
        this.kochManager = kochManager;
        k.addObserver(this);
    }
    @Override
    public void run() {
        if(edgeName.equalsIgnoreCase("left")){
            k.generateLeftEdge();
            if(kochManager.getCount() == 2){
                kochManager.getApplication().requestDrawEdges();
            }
            else{
                kochManager.finished();
            }


        }
        else if(edgeName.equalsIgnoreCase("bottom")){
            k.generateBottomEdge();
            if(kochManager.getCount() == 2 ){
                kochManager.getApplication().requestDrawEdges();
            }
            else{
                kochManager.finished();
            }
        }
        else if(edgeName.equalsIgnoreCase("right")){
            k.generateRightEdge();
            if(kochManager.getCount() == 2 ){
                kochManager.getApplication().requestDrawEdges();
            }
            else{
                kochManager.finished();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        kochManager.addEdges((Edge)arg);
    }
}
