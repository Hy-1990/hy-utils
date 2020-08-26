package com.bigdata.hy_tools.stu;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import log.HyLogger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/** @Author huyi @Date 2020/8/13 14:58 @Description: */
public class StuThreadPools {
  private ScheduledExecutorService executorService =
      Executors.newSingleThreadScheduledExecutor(new CustomizableThreadFactory("hy-"));
  private TimeLimiter timeLimiter =
      SimpleTimeLimiter.create(
          Executors.newFixedThreadPool(5, new CustomizableThreadFactory("hyfix-")));
  private LinkedBlockingQueue<String> basket = new LinkedBlockingQueue<String>(1000000);
  private Random random = new Random();
  private String[] strings = new String[] {"a", "b", "c", "d"};
  private final AtomicBoolean isInterrupted = new AtomicBoolean();

  public void addBasket(String phone) {
    basket.add(phone);
  }

  public void init() {
    HyLogger.logger().info("启动引擎！");
    executorService.scheduleAtFixedRate(
        () -> {
          try {
            timeLimiter.runWithTimeout(
                () -> {
                  if (!basket.isEmpty()) {
                    HyLogger.logger().info("该次结果:{}", Joiner.on(",").skipNulls().join(basket));
                    basket.clear();
                  }
                },
                1,
                TimeUnit.MILLISECONDS);

          } catch (TimeoutException e) {
            HyLogger.logger().error("限时任务超时！");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        },
        0,
        10,
        TimeUnit.SECONDS);
  }

  public void init1() {
    Executors.newSingleThreadExecutor(new CustomizableThreadFactory("hytest-"))
        .submit(
            new Runnable() {
              @Override
              public void run() {
                while (true) {
                  addBasket(strings[random.nextInt(strings.length)]);
                  try {
                    Thread.sleep(10);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              }
            });
  }

  public static void main(String[] args) {
    StuThreadPools stuThreadPools = new StuThreadPools();
    stuThreadPools.init();
    stuThreadPools.init1();
  }
}
