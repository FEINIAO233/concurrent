package com.xuc.concurrent.test;

/**
 * @author xuchang
 * @date 2021/10/26
 */
public class Test2 {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
