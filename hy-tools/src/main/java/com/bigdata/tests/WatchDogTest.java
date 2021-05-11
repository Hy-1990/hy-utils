package com.bigdata.tests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** @Author huyi @Date 2021/4/15 13:48 @Description: */
public class WatchDogTest {
  public static ConcurrentHashMap<String, Thread> consoleThreadMap =
      new ConcurrentHashMap<String, Thread>();

  public static void main(String[] args) throws InterruptedException {
    System.out.println(LocalDateTime.now());
    CountDownLatch latch = new CountDownLatch(1);
    ThreadPoolManager.getInstance().addExecuteTask(ThreadDemo.builder().serial("hahaha").build());
    ThreadPoolManager.getInstance()
        .addExecuteTask(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(5);
                consoleThreadMap.get("hahaha").interrupt();
                System.out.println(LocalDateTime.now());
                System.out.println(ThreadPoolManager.getInstance().getCompletedTaskCount());
                latch.countDown();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });
    latch.await();
    TimeUnit.SECONDS.sleep(10);
    System.out.println(LocalDateTime.now() + consoleThreadMap.toString());
    System.out.println(ThreadPoolManager.getInstance().getCompletedTaskCount());
    ThreadPoolManager.getInstance().shutdown();
  }

  @Data
  @Builder
  public static class ThreadDemo implements Runnable {
    private String serial;

    @Override
    public void run() {
      try {
        consoleThreadMap.put(serial, Thread.currentThread());
        TimeUnit.SECONDS.sleep(10);
        System.out.println(serial);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        consoleThreadMap.remove(serial); // 执行完成后，将线程从hashmap中移除
        System.out.println("abc");
      }
    }
  }
}
