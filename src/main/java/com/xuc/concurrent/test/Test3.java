package com.xuc.concurrent.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xuchang
 * @date 2021/10/27
 */

public class Test3 implements Runnable {
    private String filename;

    public Test3(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        int i = 0;
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        List<Thread> list = new ArrayList<>();
        for (String filename : a) {
            Test3 dr = new Test3(filename);
            Thread t = new Thread(dr);
            t.setPriority(++i);
            list.add(t);
        }
        long s = System.currentTimeMillis();
        list.forEach(t -> {
            long start = new Date().getTime();
            t.start();
            System.out.println("线程" + t + " 共 " + (System.currentTimeMillis() - start) + "ms");
        });
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("共消耗 " + (System.currentTimeMillis() - s) + "ms");
    }
}

