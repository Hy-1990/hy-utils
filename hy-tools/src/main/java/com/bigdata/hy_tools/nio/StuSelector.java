package com.bigdata.hy_tools.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Program: hy-utils @ClassName: StuSelector @Author: huyi @Date: 2020-09-30 01:06 @Description:
 * selector @Version: V1.0
 */
public class StuSelector {
  public static void main(String[] args) throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
    System.out.println("A is blocking=" + serverSocketChannel.isBlocking());
    serverSocketChannel.configureBlocking(false);
    System.out.println("B is blocking=" + serverSocketChannel.isBlocking());
    Selector selector = Selector.open();
    System.out.println("selector=" + selector);
    System.out.println("A serverSocketChannel.isRegistered=" + serverSocketChannel.isRegistered());
    SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    System.out.println("B serverSocketChannel.isRegistered=" + serverSocketChannel.isRegistered());
    serverSocketChannel.close();
  }
}
