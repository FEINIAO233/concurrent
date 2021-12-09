package com.xuc.concurrent.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static com.xuc.concurrent.utils.ThreadUtil.sleep;

/**
 * @author xuc
 * @date 2021/11/24
 */
@Slf4j(topic = "c.Test7")
public class Test7 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            service.submit(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sleep(2);
                log.info("end");
                semaphore.release();
            });
        }
        service.shutdown();
    }
}
