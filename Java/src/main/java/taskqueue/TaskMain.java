package taskqueue;

public class TaskMain {
    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();
        for(int i = 0; i < 1; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(30);
                        taskQueue.offer("task" + finalI);
                        Thread.sleep(5000);
                    }catch (Exception e) {
                    }
                }
            }).start();
        }

        for(int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    taskQueue.poll();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
