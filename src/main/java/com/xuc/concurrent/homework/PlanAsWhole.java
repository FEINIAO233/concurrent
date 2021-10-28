package com.xuc.concurrent.homework;

import lombok.extern.slf4j.Slf4j;

import static com.xuc.concurrent.utils.ThreadUtil.sleep;

/**
 * @author xuchang
 * @date 2021/10/28
 */
@Slf4j(topic = "c.PlanAsWhole")
public class PlanAsWhole {
    public static void main(String[] args) {
        thread();
    }

    private static void single() {
        log.debug("洗水壶");
        sleep(1);
        log.debug("烧开水");
        sleep(15);
        log.debug("洗茶壶");
        sleep(1);
        log.debug("洗茶杯");
        sleep(2);
        log.debug("拿茶叶");
        sleep(1);

    }

    private static void thread() {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            sleep(1);
            log.debug("烧开水");
            sleep(15);
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                log.debug("洗茶壶");
                sleep(1);
                log.debug("洗茶杯");
                sleep(2);
                log.debug("拿茶叶");
                sleep(1);
                t1.join();
                log.debug("泡茶");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t2.start();
    }
}
