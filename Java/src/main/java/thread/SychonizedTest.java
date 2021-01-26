package thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SychonizedTest {
    private static Operation operation = new Operation();
    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(new A()).start();
            new Thread(new B()).start();
        }

    }

    static class Operation {
        private int sum = 0;
        private ReadWriteLock lock = new ReentrantReadWriteLock(true);

        public void increment(String name){
            lock.writeLock().lock();
            System.out.println("get write lock: " + name);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum++;
            System.out.println("sum: " + sum);
            lock.writeLock().unlock();
        }

        public void decrement() {
            lock.writeLock().lock();
            System.out.println("start decrement: ");
            sum--;
            lock.writeLock().unlock();
        }

        public int getSum(String name) {
            lock.readLock().lock();
            System.out.println("get read lock: " + name);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("getSum: " + name);
                return sum;
            }finally {
                lock.readLock().unlock();
            }
        }
    }

    static class A implements Runnable {

        @Override
        public void run() {
            operation.increment(Thread.currentThread().getName());
            //int sum = operation.getSum(Thread.currentThread().getName());
        }
    }

    static class B implements Runnable {

        @Override
        public void run() {
            //operation.increment(Thread.currentThread().getName());
            int sum = operation.getSum(Thread.currentThread().getName());
        }
    }
}
