package com.bigdata.hy_tools.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Program: hy-utils
 * @ClassName: StuServerSocketChannelClient
 * @Author: huyi
 * @Date: 2020-09-28 00:58
 * @Description:
 * @Version: V1.0
 */
public class StuServerSocketChannelClient {
  public static void main(String[] args) throws IOException {
      Socket socket = new Socket("localhost",8888);
      OutputStream out = socket.getOutputStream();
      out.write("hahahaha".getBytes());
      socket.close();
  }
}
