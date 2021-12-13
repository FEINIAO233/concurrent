package com.xuc.netty.c2;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuchang
 * @date 2021/12/9
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        DefaultEventLoopGroup executors = new DefaultEventLoopGroup();

        double v = Double.parseDouble("");
        System.out.println(v);
    }
}
