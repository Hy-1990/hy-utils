package com.bigdata.hy_tools.stu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuRateLimiter @Author: huyi @Date: 2020-08-24
 * 02:02 @Description: @Version: V1.0
 */
public class StuRateLimiter {
  private static final AtomicInteger DATA = new AtomicInteger(0);
  private static final RequestLimiterHandle HANDLE = new RequestLimiterHandle();

  public static void main(String[] args) {
    IntStream.range(0, 10)
        .forEach(
            (x) ->
                new Thread(
                        () -> {
                          while (true) {
                            HANDLE.submitRequest(DATA.getAndIncrement());
                            try {
                              TimeUnit.MILLISECONDS.sleep(100);
                            } catch (InterruptedException e) {
                              e.printStackTrace();
                            }
                          }
                        })
                    .start());

    IntStream.range(0, 10)
        .forEach(
            (x) ->
                new Thread(
                        () -> {
                          while (true) {
                            HANDLE.handleRequest(System.out::println);
                          }
                        })
                    .start());
  }
}
