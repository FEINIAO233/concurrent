package com.xuc.netty.c4;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * REDIS 协议
 * * 代表数组 3代表数组内元素个数 (例如 set name xuc)
 * $ 代表字符串命令 3代表命令长度(例如get，set)
 * @author xuchang
 * @date 2021/12/14
 */
@Slf4j
public class RedisClient {
    public static void main(String[] args) {
        final byte[] line = {'\r', '\n'};
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                set(ctx);
                                get(ctx);
                            }

                            private void set(ChannelHandlerContext ctx) {
                                ByteBuf buffer = ctx.alloc().buffer();
                                buffer.writeBytes("*3".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("$3".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("set".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("$4".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("name".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("$3".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("xuc".getBytes());
                                buffer.writeBytes(line);
                                ctx.writeAndFlush(buffer);
                            }

                            private void get(ChannelHandlerContext ctx) {
                                ByteBuf buffer = ctx.alloc().buffer();
                                buffer.writeBytes("*2".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("$3".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("get".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("$4".getBytes());
                                buffer.writeBytes(line);
                                buffer.writeBytes("name".getBytes());
                                buffer.writeBytes(line);
                                ctx.writeAndFlush(buffer);
                            }
                        });
                    }
                })
                .connect(new InetSocketAddress("localhost", 6379));
    }
}
