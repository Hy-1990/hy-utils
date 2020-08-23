package com.bigdata.hy_tools.stu;

import com.google.common.util.concurrent.Monitor;

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
      monitor.enterWhen(INC_WHEN_LESS_10);
      try {
        x++;
        System.out.println(Thread.currentThread() + "x = " + x);
      } finally {
        monitor.leave();
      }
    }
  }
}
