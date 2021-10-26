package com.xuc.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 66483
 */
@Slf4j(topic = "c.Test")
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("test");
        new Thread(() -> log.info("thread 1")).start();
        new Thread(() -> log.info("thread 2")).start();
        FutureTask<Integer> task = new FutureTask<>(() -> {
            Thread.sleep(3000);
            return 100;
        });
        new Thread(task).start();

        System.out.println(task.get());

    }
}
