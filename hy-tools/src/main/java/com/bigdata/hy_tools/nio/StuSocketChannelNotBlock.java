package com.bigdata.hy_tools.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Program: hy-utils @ClassName: StuSocketChannelNotBlock @Author: huyi @Date: 2020-09-30
 * 00:49 @Description: 非阻塞通道 @Version: V1.0
 */
public class StuSocketChannelNotBlock {
  public static void main(String[] args) throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
    SocketChannel socketChannel = serverSocketChannel.accept();
    ByteBuffer byteBuffer = ByteBuffer.allocate(2);
    int readLength = socketChannel.read(byteBuffer);
    while (readLength != -1) {
      String newString = new String(byteBuffer.array());
      System.out.println(newString);
      byteBuffer.flip();
      readLength = socketChannel.read(byteBuffer);
    }
    socketChannel.close();
    serverSocketChannel.close();
  }
}
