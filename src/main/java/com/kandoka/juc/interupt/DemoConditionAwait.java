package com.kandoka.juc.interupt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/8 20:07
 */
public class DemoConditionAwait {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println("t1 get in");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("t1 get out");
            }
        });
        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t2 get in");
                condition.signal();
            } finally {
                lock.unlock();
                System.out.println("t2 get out");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
