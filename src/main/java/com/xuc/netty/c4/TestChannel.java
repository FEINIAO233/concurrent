package com.xuc.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuc
 * @date 2021/12/13
 */
@Slf4j
public class TestChannel {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(
                        1024, 0, 4, 0, 0),
                new LoggingHandler(LogLevel.DEBUG)
        );
        send(channel, "Hello");
        send(channel, "worldddd");
        channel.flush();
    }

    static void send(EmbeddedChannel channel, String s) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        byte[] bytes = s.getBytes();
        int length = bytes.length;
        buffer.writeInt(length);
        buffer.writeBytes(bytes);
        channel.write(buffer);
    }
}
