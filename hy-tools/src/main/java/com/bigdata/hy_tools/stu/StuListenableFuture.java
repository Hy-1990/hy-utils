package com.bigdata.hy_tools.stu;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Program: hy-utils @ClassName: StuListenableFuture @Author: huyi @Date: 2020-08-25
 * 22:49 @Description: ListenableFuture @Version: V1.0
 */
public class StuListenableFuture {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    ListeningExecutorService listeningExecutorService =
        MoreExecutors.listeningDecorator(executorService);
    ListenableFuture<String> listenableFuture =
        listeningExecutorService.submit(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(10);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return "I am the result";
            });

    //    listenableFuture.addListener(
    //        () -> {
    //          System.out.println("the task completed");
    //          try {
    //            System.out.println("result:" + listenableFuture.get());
    //          } catch (Exception e) {
    //            e.printStackTrace();
    //          } finally {
    //            listeningExecutorService.shutdown();
    //          }
    //        },
    //        listeningExecutorService);

    Futures.addCallback(
        listenableFuture,
        new FutureCallback<String>() {
          @Override
          public void onSuccess(@Nullable String s) {
            System.out.println("Task completed and result:" + s);
            listeningExecutorService.shutdown();
          }

          @Override
          public void onFailure(Throwable throwable) {
            throwable.printStackTrace();
          }
        },
        listeningExecutorService);

  }
}
