package multiThread;

import java.util.concurrent.*;

public class MultithreadingExample {

    // Shared resource
    private static int sharedCounter = 0;

    public static void main(String[] args) {
        // Creating a thread pool with fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submitting tasks to the thread pool
        executor.submit(new IncrementTask()); // Increment task
        executor.submit(new DecrementTask()); // Decrement task

        // Shutting down the thread pool
        executor.shutdown();
    }

    // Runnable task to increment sharedCounter
    static class IncrementTask implements Runnable {
        public void run() {
            synchronized (MultithreadingExample.class) {
                for (int i = 0; i < 5; i++) {
                    sharedCounter++;
                    System.out.println("Incremented: " + sharedCounter);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Runnable task to decrement sharedCounter
    static class DecrementTask implements Runnable {
        public void run() {
            synchronized (MultithreadingExample.class) {
                for (int i = 0; i < 5; i++) {
                    sharedCounter--;
                    System.out.println("Decremented: " + sharedCounter);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
