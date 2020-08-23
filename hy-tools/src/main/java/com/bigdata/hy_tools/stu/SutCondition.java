package com.bigdata.hy_tools.stu;

import log.HyLogger;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Program: hy-utils @ClassName: SutCondition @Author: huyi @Date: 2020-08-23
 * 23:09 @Description: @Version: V1.0
 */
public class SutCondition {
  private static Integer initData = 0;
  private static boolean dataUsed = false;
  private static final Lock LOCK = new ReentrantLock();
  private static final Condition CONDITION = LOCK.newCondition();
  private static Random random = new Random();
  private static ScheduledExecutorService scheduledExecutorService =
      Executors.newScheduledThreadPool(10, new CustomizableThreadFactory("hy-"));

  private static void change() {
    LOCK.lock();
    try {
      while (!dataUsed) {
        CONDITION.await();
      }
      TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
      initData += random.nextInt(10);
      dataUsed = false;
      System.out.println("更新数据:" + initData);
      CONDITION.signal();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();
    }
  }

  private static void use() {
    LOCK.lock();
    try {
      while (dataUsed) {
        CONDITION.await();
      }
      TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
      dataUsed = true;
      System.out.println("使用数据:" + initData);
      CONDITION.signal();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();
    }
  }

  public static void main(String[] args) {
    scheduledExecutorService.scheduleAtFixedRate(
        () -> {
          change();
        },
        0,
        1,
        TimeUnit.SECONDS);

    scheduledExecutorService.scheduleAtFixedRate(
        () -> {
          use();
        },
        0,
        1,
        TimeUnit.SECONDS);

  }
}
