package com.xuc.concurrent;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test")
public class Test {
    public static void main(String[] args) {
        log.info("test");
        new Thread(() -> log.info("thread 1")).start();
        new Thread(() -> log.info("thread 2")).start();
    }
}
