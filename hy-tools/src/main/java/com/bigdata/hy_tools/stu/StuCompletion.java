package com.bigdata.hy_tools.stu;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Program: hy-utils @ClassName: StuCompletion @Author: huyi @Date: 2020-08-25 23:42 @Description:
 * 完成的线程提前获取结果，优化！ 完成线程池 @Version: V1.0
 */
public class StuCompletion {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
    final List<Callable<Integer>> tasks =
        Arrays.asList(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(30);
                System.out.println("task 30 completed");
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return 30;
            },
            () -> {
              try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("task 10 completed");
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return 10;
            },
            () -> {
              try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println("task 20 completed");
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return 20;
            });

    tasks.forEach(completionService::submit);

    tasks.forEach(
        (x) -> {
          try {
            System.out.println(completionService.take().get());
          } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
          }
        });

    executorService.shutdown();
  }
}
