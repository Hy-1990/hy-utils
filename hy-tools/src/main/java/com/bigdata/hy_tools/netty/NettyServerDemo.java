package com.bigdata.hy_tools.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import log.HyLogger;

/** @Author huyi @Date 2021/4/12 9:32 @Description: */
public class NettyServerDemo {

  public static void createServer() {
    // boss线程监听端口，worker线程负责数据读写
    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(boss, worker)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 100)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(
              new ChannelInitializer() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                  ChannelPipeline p = channel.pipeline();
                  p.addLast(new LoggingHandler(LogLevel.INFO));
                  // 字符串解码器
                  p.addLast(new StringDecoder());
                  // 字符串编码器
                  p.addLast(new StringEncoder());
                  p.addLast(new ServerHandle());
                }
              });
      ChannelFuture f = b.bind(18080).sync();
      f.channel()
          .closeFuture()
          .addListener(
              (ChannelFutureListener)
                  channelFuture ->
                      HyLogger.logger().info(channelFuture.channel().toString() + "链路关闭"))
          .sync();
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      boss.shutdownGracefully();
      worker.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    createServer();
  }
}

class ServerHandle extends SimpleChannelInboundHandler<String> {

  // 读取客户端发送的数据
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    System.out.println("client response :" + msg);
    ctx.channel().writeAndFlush("i am server !");

    //        ctx.writeAndFlush("i am server !").addListener(ChannelFutureListener.CLOSE);
  }

  // 新客户端接入
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelActive");
  }

  // 客户端断开
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelInactive");
  }

  // 异常
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // 关闭通道
    ctx.channel().close();
    // 打印异常
    cause.printStackTrace();
  }
}
