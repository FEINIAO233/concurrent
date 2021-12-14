package com.xuc.netty.c4

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.http.*
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import mu.KotlinLogging

/**
 * @author xuchang
 * @date 2021/12/14
 */
private val logger = KotlinLogging.logger {}

fun main() {
    ServerBootstrap()
        .group(NioEventLoopGroup())
        .channel(NioServerSocketChannel::class.java)
        .childHandler(object : ChannelInitializer<NioSocketChannel>() {
            override fun initChannel(ch: NioSocketChannel) {
                ch.pipeline().addLast(LoggingHandler(LogLevel.DEBUG))
                ch.pipeline().addLast(HttpServerCodec())
                ch.pipeline().addLast(object : SimpleChannelInboundHandler<HttpRequest>() {
                    override fun channelRead0(ctx: ChannelHandlerContext, msg: HttpRequest) {
                        logger.debug(msg.uri())
                        val response = DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK)
                        val bytes = "<h1>Hello, World</h1>".toByteArray()
                        response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, bytes.size)
                        response.content().writeBytes(bytes)
                        ctx.writeAndFlush(response)
                    }
                })
            }
        })
        .bind(8080)
}

