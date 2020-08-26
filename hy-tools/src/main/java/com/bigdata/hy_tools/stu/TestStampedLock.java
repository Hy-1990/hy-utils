package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.utils.EmptyUtil;
import log.HyLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: TestStampedLock @Author: huyi @Date: 2020-08-27
 * 00:10 @Description: @Version: V1.0
 */
public class TestStampedLock {
  private static final StampedLock STAMPED_LOCK = new StampedLock();
  private static List<Integer> list = new ArrayList<>();
  private static final Random RANDOM = new Random();

  private static void write() {
    long stamped = STAMPED_LOCK.writeLock();
    try {
      int a = RANDOM.nextInt(1000000);
      if (EmptyUtil.isEmpty(list)) {
        list.add(a);
      } else {
        list.add(a);
        list = list.stream().sorted().collect(Collectors.toList());
      }
      System.out.println("************-" + Thread.currentThread().getName() + ": write is " + a);
    } finally {
      STAMPED_LOCK.unlockWrite(stamped);
    }
  }

  private static void read() {
    long stamped = STAMPED_LOCK.readLock();
    try {
      System.out.println(
          "##############-" + Thread.currentThread().getName() + ": read is " + list);
    } finally {
      STAMPED_LOCK.unlockRead(stamped);
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
                              TimeUnit.SECONDS.sleep(x + 2);
                            } catch (InterruptedException e) {
                              e.printStackTrace();
                            }
                            write();
                          }
                        })
                    .start());
    IntStream.range(0, 1)
        .forEach(
            (x) ->
                new Thread(
                        () -> {
                          while (true) {
                            try {
                              TimeUnit.MILLISECONDS.sleep(1000);
                            } catch (InterruptedException e) {
                              e.printStackTrace();
                            }
                            read();
                          }
                        })
                    .start());
  }
}
