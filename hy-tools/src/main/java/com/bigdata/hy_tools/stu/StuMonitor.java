package com.bigdata.hy_tools.stu;

import com.google.common.util.concurrent.Monitor;

import java.util.concurrent.TimeUnit;

/**
 * @Program: hy-utils @ClassName: StuMonitor @Author: huyi @Date: 2020-08-24
 * 01:27 @Description: @Version: V1.0
 */
public class StuMonitor {
  private static Monitor monitor = new Monitor();
  private static Integer x = 0;
  private static final Integer MAX = 10;
  private static final Monitor.Guard INC_WHEN_LESS_10 =
      new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
          return x < 10;
        }
      };

  public static void main(String[] args) throws InterruptedException {
    while (true) {
      boolean ready = monitor.enterWhen(INC_WHEN_LESS_10, 10, TimeUnit.SECONDS);
      try {
        x++;
        System.out.println(Thread.currentThread() + "x = " + x);
      } finally {
        if (!ready) {
          System.out.println("oooo");
          break;
        } else {
          monitor.leave();
        }
      }

      System.out.println("hahaha");
      //      if(x == 10){
      //          x = 0;
      //      }
    }
  }
}
