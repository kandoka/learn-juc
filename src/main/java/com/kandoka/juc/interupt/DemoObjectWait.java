package com.kandoka.juc.interupt;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/8 19:56
 */
public class DemoObjectWait {
    public static void main(String[] args) throws InterruptedException {
        Object lockObject = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockObject) {
                    System.out.println("t1 get in");
                    try {
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1 get out");
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockObject) {
                    System.out.println("t2 get in");
                    lockObject.notify();
                    System.out.println("t2 get out");
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
