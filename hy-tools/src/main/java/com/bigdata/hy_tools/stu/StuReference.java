package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.entity.Data;
import log.HyLogger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuReference @Author: huyi @Date: 2020-08-22
 * 16:17 @Description: @Version: V1.0
 */
public class StuReference {
  private static final AtomicReference<Data> reference = new AtomicReference<>(new Data("haha", 0));

  public static void main(String[] args) {
    IntStream.range(1, 5)
        .forEach(
            (x) ->
                new Thread(
                        () -> {
                          while (true) {
                            final Data now = reference.get();
                            if (reference.compareAndSet(
                                now, new Data(now.getName(), now.getMoney() + 10))) {
                              HyLogger.logger().info("现在的价格：{}", reference.get());
                            }
                            try {
                              TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10000));
                            } catch (InterruptedException e) {
                              HyLogger.logger().error(e.getMessage());
                            }
                          }
                        })
                    .start());
  }
}
