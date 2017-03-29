package calculate;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Vai on 3/22/17.
 */
public class RunnableEdge implements Runnable, Observer {
    String edgeName;
    KochFractal k;
    KochManager kochManager;

    public RunnableEdge(String edgeName, KochManager kochManager){
        this.edgeName = edgeName;
        k = new KochFractal();
        this.kochManager = kochManager;
    }
    @Override
    public void run() {
        if(edgeName.equalsIgnoreCase("left")){
            k.generateLeftEdge();
            kochManager.count++;
        }
        else if(edgeName.equalsIgnoreCase("bottom")){
            k.generateBottomEdge();
            kochManager.count++;
        }
        else if(edgeName.equalsIgnoreCase("right")){
            k.generateRightEdge();
            kochManager.count++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        kochManager.edges.add((Edge)arg);
    }
}
