package com.bigdata.tests;

import com.bigdata.hy_tools.utils.EmptyUtil;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/** @Author huyi @Date 2020/9/1 20:21 @Description: */
public class TestCompletableFuture {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
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

    List<String> result2 = new ArrayList<>();
    IntStream.range(0, 10)
        .forEach(
            (x) ->
                completionService.submit(
                    () -> {
                      try {
                        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                      } catch (InterruptedException e) {
                        e.printStackTrace();
                      }
                      return "haha-" + x;
                    }));
    while (true) {
      try {
        String a = completionService.poll().get();
        if (EmptyUtil.isEmpty(a)) {
          break;
        } else {
          System.out.println("完成一个！");
          result2.add(a);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    System.out.println(Joiner.on(",").join(result2));
    executorService.shutdown();
  }
}
