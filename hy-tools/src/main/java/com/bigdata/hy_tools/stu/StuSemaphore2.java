package com.bigdata.hy_tools.stu;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * @Program: hy-utils @ClassName: StuSemaphore2 @Author: huyi @Date: 2020-08-23
 * 16:03 @Description: @Version: V1.0
 */
public class StuSemaphore2 {
  private static final Semaphore SEMAPHORE = new Semaphore(1, true);
  private static final ExecutorService POOL =
      Executors.newFixedThreadPool(10, new CustomizableThreadFactory("hy-"));
  private static final ConcurrentHashMap<String, Thread> TASKS = new ConcurrentHashMap<>();

  public static void main(String[] args) throws InterruptedException {
    Thread t1 =
        new Thread(
            () -> {
              try {
                SEMAPHORE.acquire();
                System.out.println(Thread.currentThread().getName() + "获得");
                TimeUnit.HOURS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } finally {
                SEMAPHORE.release();
              }
            },
            "T1");
    t1.start();

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Thread t2 =
        new Thread(
            () -> {
              try {
                SEMAPHORE.acquire();
                System.out.println(Thread.currentThread().getName() + "获得");
              } catch (InterruptedException e) {
                System.out.println("T2线程中止！");
              } finally {
                SEMAPHORE.release();
              }
            },
            "T2");
    t2.start();
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t2.interrupt();
    SEMAPHORE.acquire();
    System.out.println("主线程获得成功！");
  }
}
