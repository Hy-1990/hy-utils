package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.entity.Data;
import log.HyLogger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuCyclicBarrier @Author: huyi @Date: 2020-08-23
 * 02:24 @Description: 循环屏障 @Version: V1.0 适用场景批量任务，任务同步完成各个阶段
 */
public class StuCyclicBarrier {
  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    List<Data> initData =
        IntStream.rangeClosed(1, 10)
            .mapToObj((x) -> new Data("haha", x))
            .collect(Collectors.toList());
    final CyclicBarrier cyclicBarrier = new CyclicBarrier(initData.size() + 1);
    final Random random = new Random();
    initData.forEach(
        (x) ->
            new Thread(
                    () -> {
                      try {
                        if (x.getMoney() > 5) {
                          x.setName("haha-vip");
                        }
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(10000));
                        System.out.println(Thread.currentThread().getName() + "修改用户权限成功！");
                        cyclicBarrier.await();
                        if (x.getMoney() > 0) {
                          x.setMoney(x.getMoney() + 10000);
                        }
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(10000));
                        System.out.println(Thread.currentThread().getName() + "用户充值成功！");
                        cyclicBarrier.await();
                      } catch (InterruptedException | BrokenBarrierException e) {
                        HyLogger.logger().error(e.getMessage());
                      }
                    })
                .start());
    cyclicBarrier.await();
    System.out.println("全部用户权限修改完成！");
    cyclicBarrier.await();
    System.out.println("全部用户充值完成！");
    initData.forEach(System.out::println);
  }
}
