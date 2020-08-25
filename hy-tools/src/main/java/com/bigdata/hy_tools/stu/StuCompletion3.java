package com.bigdata.hy_tools.stu;

import java.util.concurrent.*;

/**
 * @Program: hy-utils @ClassName: StuCompletion3 @Author: huyi @Date: 2020-08-26
 * 00:30 @Description: @Version: V1.0
 */
public class StuCompletion3 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    CompletableFuture<Integer> future1 =
        CompletableFuture.supplyAsync(
                () -> {
                  System.out.println(Thread.currentThread().getName());
                  return "Java";
                },
                executorService)
            .thenApplyAsync(
                v -> {
                  System.out.println(Thread.currentThread().getName());
                  return v.length();
                },
                executorService);
    System.out.println(future1.get());

    CompletableFuture.supplyAsync(
            () -> {
              System.out.println(Thread.currentThread().getName());
              return "Java";
            },
            executorService)
        .thenAccept(
            v -> {
              System.out.println(Thread.currentThread().getName());
              System.out.println("length " + v);
            })
        .thenRunAsync(
            () -> {
              System.out.println(Thread.currentThread().getName() + " finished");
            },
            executorService);

    CompletableFuture<String> future3 =
        CompletableFuture.supplyAsync(() -> "hello")
            .thenCombine(CompletableFuture.supplyAsync(() -> "world"), (s1, s2) -> s1 + s2);
    future3.thenApply(String::toUpperCase).thenAccept(System.out::println);

    CompletableFuture future4 =
        CompletableFuture.supplyAsync(
                () -> {
                  int a = Integer.parseInt("aaa");
                  return a;
                })
            .handle(
                (r, e) -> {
                  if (e != null) {
                    return "ERROR";
                  } else {
                    return r + "";
                  }
                })
            .thenAccept(System.out::println);

    executorService.shutdown();
  }
}
