package thread;

public class TaskQueueTest {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        //lock.wait();
    }

    interface Task {void run();}

    static class Queue {
        private static final int QUEUE_SIZE = 32;
        private Task[] tasks = new Task[QUEUE_SIZE];
        private int taskNum = 0;

        public synchronized void push(Task task) {
            tasks[taskNum++] = task;
        }
    }
}
