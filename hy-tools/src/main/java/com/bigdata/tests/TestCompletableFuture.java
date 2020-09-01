package com.bigdata.tests;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/** @Author huyi @Date 2020/9/1 20:21 @Description: */
public class TestCompletableFuture {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    List<CompletableFuture<String>> result = new ArrayList<>();
    IntStream.range(0, 10)
        .forEach(
            (x) -> {
              result.add(
                  CompletableFuture.supplyAsync(
                      () -> {
                        try {
                          TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        } catch (InterruptedException e) {
                          e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ":已完成!");
                        return "haha-" + x;
                      },
                      executorService));
            });
    List<String> hy = new ArrayList<>();
    result.forEach(
        x -> {
          try {
            hy.add(x.get());
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (ExecutionException e) {
            e.printStackTrace();
          }
        });
    System.out.println(Joiner.on(",").join(hy));
    executorService.shutdown();
  }
}
