package com.bigdata.tests;

import cn.hutool.system.SystemUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/** @Author huyi @Date 2021/3/18 18:11 @Description: */
public class Test7 {
  public static void main(String[] args) {
    //
    IntStream.range(0, 20)
        .forEach(
            x ->
                new Thread(
                        () -> {
                          try {
                            System.out.println("线程" + x + "运行中");
                            TimeUnit.SECONDS.sleep(10 + new Random().nextInt(5));
                            System.out.println("线程" + x + "结束");
                          } catch (Exception e) {
                            e.printStackTrace();
                          }
                        })
                    .start());
    new Thread(
            () -> {
              while (true) {
                try {
                  TimeUnit.SECONDS.sleep(1);
                  System.out.println("当前线程数：" + SystemUtil.getTotalThreadCount());
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
  }
}
