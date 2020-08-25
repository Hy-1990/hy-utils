package com.bigdata.hy_tools.stu;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuCompletion2 @Author: huyi @Date: 2020-08-26
 * 00:19 @Description: @Version: V1.0
 */
public class StuCompletion2 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<String> future1 =
        CompletableFuture.supplyAsync(
            () -> {
              IntStream.range(0, 10)
                  .forEach(
                      (x) -> {
                        try {
                          TimeUnit.SECONDS.sleep(1);
                          System.out.println("result is " + x);
                        } catch (InterruptedException e) {
                          e.printStackTrace();
                        }
                      });
              return "tasks completed!";
            });
    System.out.println(future1.get());

    CompletableFuture future2 =
        CompletableFuture.runAsync(
            () -> {
              IntStream.range(0, 10)
                  .forEach(
                      (x) -> {
                        try {
                          TimeUnit.SECONDS.sleep(1);
                          System.out.println("result is " + x);
                        } catch (InterruptedException e) {
                          e.printStackTrace();
                        }
                      });
            });
  }
}
