package calculate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by Vai on 4/5/17.
 */
public class awaitGet implements Runnable {

    CountDownLatch latch;
    KochManager km;
    public awaitGet(CountDownLatch latch, KochManager km){
        this.latch = latch;
        this.km = km;
    }
    @Override
    public void run() {

        try{
            latch.await();
            km.finished();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
