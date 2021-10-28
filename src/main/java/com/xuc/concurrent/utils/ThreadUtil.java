package com.xuc.concurrent.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author xuchang
 * @date 2021/10/28
 */
public class ThreadUtil {
    public static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
