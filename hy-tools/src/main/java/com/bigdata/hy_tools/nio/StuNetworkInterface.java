package com.bigdata.hy_tools.nio;

import java.net.InetAddress;
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
        Enumeration<InetAddress> enumerations = networkInterface.getInetAddresses();
        while (enumerations.hasMoreElements()) {
          InetAddress inetAddress = enumerations.nextElement();
          System.out.println("获取此IP地址的完全限定域名=" + inetAddress.getCanonicalHostName());
          System.out.println("获取此IP地址的主机名=" + inetAddress.getHostName());
          System.out.println("获取此IP地址的字符串=" + inetAddress.getHostAddress());
          System.out.print("getAddress返回此InetAddress对象的原始IP地址=");
          byte[] addressByte = inetAddress.getAddress();
          for (byte b : addressByte) {
            System.out.print(b + " ");
          }
          System.out.println();
        }
        System.out.println();
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }
}
