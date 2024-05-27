package utils;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class CustomList<E> extends ArrayList<E> {

    @Override
    public Iterator<E> iterator() {
        return super.iterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return super.listIterator();
    }

//    public static void main(String[] args) {
//        Calendar calendarT = Calendar.getInstance(Locale.ROOT);
//        long endTime = calendarT.getTimeInMillis();
//        calendarT.set(Calendar.HOUR_OF_DAY, 0);
//        calendarT.set(Calendar.MINUTE, 0);
//        calendarT.set(Calendar.SECOND, 0);
//        Date startTime = calendarT.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(startTime));
//
//    }

    public static ExecutorService executor = new ThreadPoolExecutor(20, 40, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), r -> {
        Thread t = new Thread(r);
        //t.setDefaultUncaughtExceptionHandler((Thread thread, Throwable e) -> LOGGER.error("线程池中执行发生异常，异常为:", e));
        return t;
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<CompletableFuture<List<String>>> futures = new ArrayList<>();
        for(int i = 0 ; i < 10; i++) {
            CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(() -> {
                List<String> strList = new ArrayList<>();
                for(int j = 0 ; j < 5 ; j++) {
                    strList.add(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return strList;
            }, executor);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        List<String> newList = new ArrayList<>();
        for(CompletableFuture<List<String>> future : futures) {
            newList.addAll(future.get());
        }
        System.out.println(newList);
    }
}
