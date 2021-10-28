package com.xuc.concurrent.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xuchang
 * @date 2021/10/28
 */
@Slf4j(topic = "c.TestState")
public class TestState {
    @SneakyThrows
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("t1 run");
        }, "t1");

        t1.start();

        Thread t2 = new Thread(() -> {
            while (true) {

            }
        }, "t2");

        t2.start();

        Thread t3 = new Thread(() -> {
            synchronized (TestState.class) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t3.start();

        Thread t4 = new Thread(() -> {

        }, "t4");

        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");

        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (TestState.class) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t6");
        t6.start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("t1:{}", t1.getState());
        log.debug("t2:{}", t2.getState());
        log.debug("t3:{}", t3.getState());
        log.debug("t4:{}", t4.getState());
        log.debug("t5:{}", t5.getState());
        log.debug("t6:{}", t6.getState());
    }
}
