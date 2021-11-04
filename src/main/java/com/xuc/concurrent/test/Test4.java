package com.xuc.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import static com.xuc.concurrent.utils.ThreadUtil.sleep;

/**
 * @author xuchang
 * @date 2021/11/4
 */
@Slf4j(topic = "c.Test4")
public class Test4 {
    static final Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock) {
                log.debug("wait");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("wake up");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.debug("wait");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("wake up");
            }
        }, "t2").start();

        new Thread(() -> {
            sleep(2);
            synchronized (lock) {
                lock.notifyAll();
            }
        }, "t3").start();


    }
}
