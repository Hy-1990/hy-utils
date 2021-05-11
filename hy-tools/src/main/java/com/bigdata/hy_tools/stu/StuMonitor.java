package com.bigdata.hy_tools.stu;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Monitor;
import log.HyLogger;

import java.util.concurrent.TimeUnit;

/**
 * @Program: hy-utils @ClassName: StuMonitor @Author: huyi @Date: 2020-08-24
 * 01:27 @Description: @Version: V1.0
 */
public class StuMonitor {
  private static Monitor monitor = new Monitor();
  private static Integer x = 0;
  private static String name = "ja";
  private static final Integer MAX = 10;
  private static final Monitor.Guard INC_WHEN_LESS_10 =
      new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
          return x >= 10;
        }
      };
  private static final Monitor.Guard EQUAL_NAME =
      new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
          return name.equals("haha");
        }
      };

  public static void main(String[] args) throws InterruptedException {
    new Thread(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(20);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              name = "haha";
            })
        .start();
    HyLogger.logger().info("start");
    Stopwatch watch = Stopwatch.createStarted();
    boolean success = false;
    while (true) {
      try {
        if (monitor.enterIf(EQUAL_NAME)) {
          HyLogger.logger().info("name:{}", name);
          success = true;
          break;
        } else {
          if (watch.elapsed(TimeUnit.SECONDS) > 15) {
            break;
          }
          TimeUnit.SECONDS.sleep(1);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    watch.stop();
      HyLogger.logger().info("end-name:{}", name);
  }
}
