package com.bigdata.hy_tools.stu;

import log.HyLogger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuStampedLock @Author: huyi @Date: 2020-08-24
 * 00:54 @Description: @Version: V1.0
 */
public class StuStampedLock {
  private static final StampedLock STAMPED_LOCK = new StampedLock();
  private static Integer data = 0;
  private static final Random RANDOM = new Random();

  private static void write() {
    long stamped = STAMPED_LOCK.writeLock();
    try {
      data++;
    } finally {
      STAMPED_LOCK.unlockWrite(stamped);
    }
  }

  private static void read() {
    long stamped = STAMPED_LOCK.readLock();
    try {
      HyLogger.logger().info("Read is : {}", data);
    } finally {
      STAMPED_LOCK.unlockRead(stamped);
    }
  }

  private static void readOptimistic() {
    long stamped = STAMPED_LOCK.tryOptimisticRead();
    System.out.println("aaaa " + stamped);
    if (!STAMPED_LOCK.validate(stamped)) {
      stamped = STAMPED_LOCK.readLock();
      System.out.println(stamped);
      try {
        HyLogger.logger().info("Read Optimistic is : {}", data);
      } finally {
        STAMPED_LOCK.unlockRead(stamped);
      }
    }
  }

  public static void main(String[] args) {
    IntStream.range(0, 10)
        .forEach(
            (x) ->
                new Thread(
                        () -> {
                          while (true) {
                            try {
                              TimeUnit.SECONDS.sleep(RANDOM.nextInt(10));
                              write();
                            } catch (Exception e) {
                              e.printStackTrace();
                            }
                          }
                        })
                    .start());
//    new Thread(
//            () -> {
//              while (true) {
//                try {
//                  TimeUnit.SECONDS.sleep(1);
//                  read();
//                } catch (Exception e) {
//                  e.printStackTrace();
//                }
//              }
//            })
//        .start();

    new Thread(
            () -> {
              while (true) {
                try {
                  TimeUnit.SECONDS.sleep(1);
                  readOptimistic();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
  }
}
