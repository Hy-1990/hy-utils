package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.entity.Data;
import log.HyLogger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuCountDownLatch @Author: huyi @Date: 2020-08-23
 * 01:47 @Description: @Version: V1.0 适用场景 多线程无返回值需要等待 线程结束
 */
public class StuCountDownLatch {
  public static void main(String[] args) throws InterruptedException {
    List<Data> initData =
        IntStream.rangeClosed(1, 10)
            .mapToObj((x) -> new Data("haha", x))
            .collect(Collectors.toList());
    final CountDownLatch countDownLatch = new CountDownLatch(initData.size());
    final Random random = new Random();

    initData.forEach(
        (y) ->
            new Thread(
                    () -> {
                      try {
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(10000));
                        System.out.print(Thread.currentThread().getName() + "-源数据：" + y.toString());
                        if (y.getMoney() > 5) {
                          y.setName("haha-vip");
                        }
                        System.out.println(
                            Thread.currentThread().getName() + "-修改信息后数据：" + y.toString());
                      } catch (InterruptedException e) {
                        HyLogger.logger().error(e.getMessage());
                      } finally {
                        countDownLatch.countDown();
                      }
                    })
                .start());
    countDownLatch.await();
    initData.forEach(System.out::println);
  }
}
