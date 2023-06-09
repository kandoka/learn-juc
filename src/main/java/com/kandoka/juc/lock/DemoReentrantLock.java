package com.kandoka.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/8 17:13
 */
public class DemoReentrantLock {

    public static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
    }
}
