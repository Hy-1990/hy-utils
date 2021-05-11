package com.bigdata.hy_tools.stu;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/** @Author huyi @Date 2020/12/10 17:57 @Description: */
public class CountDownLatchStu {
  public static void main(String[] args) throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    IntStream.range(0, 1)
        .forEach(
            x -> {
              new Thread(
                      () -> {
                        try {
                          TimeUnit.SECONDS.sleep(5);
                          System.out.println(x + " ---> 结束");
                        } catch (InterruptedException e) {
                          e.printStackTrace();
                        } finally {
                          latch.countDown();
                        }
                      })
                  .start();
            });
    //    new Thread(
    //            () -> {
    //              try {
    //                System.out.println("boss start");
    //                latch.await();
    //              } catch (InterruptedException e) {
    //                e.printStackTrace();
    //              } finally {
    //                System.out.println("结束");
    //              }
    //            })
    //        .start();
    latch.await(3, TimeUnit.SECONDS);
    System.out.println(latch.getCount());
  }
}
