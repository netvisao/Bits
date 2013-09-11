import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: Amine
 * Date: 9/10/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayMultiThreadAccess {


    private List<String> strings = new ArrayList<String>();
    private Random rnd = new Random();
    private int numWorkers;
    private CountDownLatch countDownLatch;


    public static void main(String[] args) {
       ArrayMultiThreadAccess target = new ArrayMultiThreadAccess(4, 25);

        try {
            target.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void await() throws InterruptedException {
        countDownLatch.await();
    }

    public ArrayMultiThreadAccess(int numWorkers, long timeToFillUp) {
        this.numWorkers = numWorkers;
        countDownLatch = new CountDownLatch(numWorkers);

        long t = System.currentTimeMillis() + timeToFillUp;

        while (System.currentTimeMillis() < t) {
            strings.add("" + Math.abs(rnd.nextInt()));
            Thread.currentThread().yield();
        }

        System.out.println("Filled up array w/ " + strings.toArray().length + " items.");

        for (int i = 0; i < numWorkers; i++) {
            new Thread(new WorkerThread(i)).start();
        }


    }

    class WorkerThread implements Runnable {

        private int threadId;
        private int workedOn = 0;

        private WorkerThread() { throw new IllegalStateException(); }

        WorkerThread(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {

            String[] arr = {};
            arr = strings.toArray(arr);

            for (int i = threadId; i < arr.length; i = i + numWorkers) {
               System.out.println("Thread " + threadId + " " + arr[i]);
                workedOn++;
            }

            System.out.println("Thread " + threadId + " processed " + workedOn + " recs.");
            countDownLatch.countDown();

        }
    }

}
