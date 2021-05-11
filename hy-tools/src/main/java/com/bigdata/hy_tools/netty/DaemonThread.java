package com.bigdata.hy_tools.netty;

import java.util.concurrent.TimeUnit;

/** @Author huyi @Date 2021/4/12 10:37 @Description: 守护线程 */
public class DaemonThread {
  public static void main(String[] args) throws InterruptedException {
    Thread thread =
        new Thread(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
              } catch (Exception exception) {
                exception.printStackTrace();
              }
            },
            "Daemon-1");
    thread.setDaemon(false);
    thread.start();
    TimeUnit.SECONDS.sleep(5);
    System.out.println("系统退出");
  }
}
