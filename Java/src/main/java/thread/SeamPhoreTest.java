package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SeamPhoreTest {

    public static void main(String[] args) {
        Semaphore semaphore;

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();

    }
}
