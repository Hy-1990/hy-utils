package com.bigdata.hy_tools.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

/**
 * @Program: hy-utils @ClassName: StuServerSocketChannel @Author: huyi @Date: 2020-09-28
 * 00:50 @Description: ServerSocketChannel @Version: V1.0
 */
public class StuServerSocketChannel {
  public static void main(String[] args) throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    ServerSocket serverSocket = serverSocketChannel.socket();
    serverSocket.bind(new InetSocketAddress("localhost", 8888));
    Socket socket = serverSocket.accept();
    InputStream inputStream = socket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    char[] charArray = new char[1024];
    int readLength = inputStreamReader.read(charArray);
    while (readLength != -1) {
      String newString = new String(charArray, 0, readLength);
      System.out.println(newString);
      readLength = inputStreamReader.read(charArray);
    }
    inputStreamReader.close();
    inputStream.close();
    socket.close();
    serverSocket.close();
    serverSocketChannel.close();
  }
}
