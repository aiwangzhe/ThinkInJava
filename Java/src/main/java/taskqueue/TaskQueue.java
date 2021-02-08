package taskqueue;

import java.util.Queue;

public class TaskQueue {
    private static final int MAX_SIZE = 10;
    private String[] tasks = new String[MAX_SIZE];
    private Object lock = new Object();
    private int size = 0;

    public void offer(String task) {
        synchronized (lock) {
            if (size >= MAX_SIZE) {
                System.out.println("wait for queue not full!");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("offer has locK: " + Thread.holdsLock(lock));
            System.out.println("insert task" + task);
            tasks[size++] = task;
            System.out.println("notify time: " + System.currentTimeMillis());
            //lock.notify();
            //System.out.println("offer has locK: " + Thread.holdsLock(lock));
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("i have released lock: " + System.currentTimeMillis());
    }

    public String poll() {
        String task = null;
        synchronized (lock) {
            if (size <= 0) {
                System.out.println("wait for queue not empty!");
                try {
                    lock.wait();
                    System.out.println("i have got the lock! " + System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("poll has locK: " + Thread.holdsLock(lock));
            task = tasks[--size];
        }
        System.out.println("poll task: " + task);
        return task;
    }
}
