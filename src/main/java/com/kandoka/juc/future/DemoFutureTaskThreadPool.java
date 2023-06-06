package com.kandoka.juc.future;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/6 17:21
 */
public class DemoFutureTaskThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> t1 = getTask("1");
        FutureTask<String> t2 = getTask("2");
        FutureTask<String> t3 = getTask("3");

        threadPool.submit(t1);
        threadPool.submit(t2);
        threadPool.submit(t3);
        threadPool.shutdown();

        t1.get();
        t2.get();
        t3.get();

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }

    public static FutureTask<String> getTask(String name) {
        return new FutureTask<>(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + " over";
        });
    }
}