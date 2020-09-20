package com.bigdata.hy_tools.nio;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Program: hy-utils @ClassName: StuNetworkInterface @Author: huyi @Date: 2020-09-20
 * 23:48 @Description: 网络接口 @Version: V1.0
 */
public class StuNetworkInterface {
  public static void main(String[] args) {
    try {
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        System.out.println("getName获得网络设备现实名称=" + networkInterface.getName());
        System.out.println("getDisplayName获得网络设备现实名称=" + networkInterface.getDisplayName());
        System.out.println("getIndex获得网络接口的索引=" + networkInterface.getIndex());
        System.out.println("isUp是否已经开启并运行=" + networkInterface.isUp());
        System.out.println("isLoopback是否为回调接口=" + networkInterface.isLoopback());
        System.out.println("getMTU获得最大传输单元=" + networkInterface.getMTU());
        System.out.println();
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }
}
