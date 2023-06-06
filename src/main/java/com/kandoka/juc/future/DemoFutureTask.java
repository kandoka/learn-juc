package com.kandoka.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/6 17:05
 */
public class DemoFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask);
        t1.start();
        System.out.println(futureTask.get());
    }
}

class MyThread extends Thread implements Callable<String> {
    @Override
    public String call() {
        System.out.println("enter into call()");
        return "hello";
    }
}
