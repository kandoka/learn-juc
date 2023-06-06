package com.kandoka.juc.thread;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/6 16:55
 */
public class DemoDeamonThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " running...");
            }
        }, "t1");
        t1.setDaemon(true);
        t1.start();

        System.out.println(Thread.currentThread().getName() + " end");
    }
}
