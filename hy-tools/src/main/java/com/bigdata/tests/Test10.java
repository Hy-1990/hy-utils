package com.bigdata.tests;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/** @Author huyi @Date 2021/4/20 10:30 @Description: */
public class Test10 {
  public static final ConcurrentHashMap<String, Thread> pool = new ConcurrentHashMap<>();

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    System.out.println(LocalDateTime.now());
    CompletableFuture<String> future1 =
        CompletableFuture.supplyAsync(new CallableDemo(), executorService);
    new Thread(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(3);
                Thread thread = pool.get("a");
                if (thread != null) {
                  thread.stop();
                  pool.remove("a");
                }
              } catch (Exception e) {
                System.out.println(e.getMessage());
              }
            })
        .start();
    try {
      String firstTaskResult = future1.get();
      System.out.println();
      new Thread(() -> {
        System.out.println(firstTaskResult);
      }).start();
    } catch (ExecutionException exception) {
      exception.printStackTrace();
      System.out.println("asdasd");
    }
    System.out.println(LocalDateTime.now() + "  " + pool.toString());
  }
}
