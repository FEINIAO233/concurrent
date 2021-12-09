package com.xuc.netty.c2;

import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuchang
 * @date 2021/12/9
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        log.debug("{}", NettyRuntime.availableProcessors());
    }
}
