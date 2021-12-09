package com.xuc.netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author xuc
 * @date 2021/12/9
 */
@Slf4j
public class TestChannelFuture {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080));
        channelFuture.addListener((ChannelFutureListener) future -> {
            Channel channel = future.channel();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                log.debug("输入要发送的消息，输入q退出");
                String s = scanner.nextLine();
                if ("q".equals(s)) {
                    channel.close();
                    channel.closeFuture().addListener((ChannelFutureListener) f -> worker.shutdownGracefully());
                    break;
                }
                channel.writeAndFlush(s);
            }
        });
    }
}
