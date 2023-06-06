package com.kandoka.juc.thread;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/6 15:48
 */
public class DemoThreadBase {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

        }, "t1");
        t1.start();
    }
}
