package com.kandoka.juc.interupt;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/8 18:08
 */
public class DemoInterrupt {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " is working: " + System.currentTimeMillis());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " start sleeping: " + System.currentTimeMillis());
                    break;
                }
            }
        }, "t1");
        t1.start();


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
    }
}
