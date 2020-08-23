package com.bigdata.hy_tools.stu;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuPhaser @Author: huyi @Date: 2020-08-23
 * 21:01 @Description: @Version: V1.0
 */
public class StuPhaser {
  private static class MyPharser extends Phaser {
    private final Runnable runnable;

    private MyPharser(Runnable runnable) {
      this.runnable = runnable;
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
      this.runnable.run();
      return super.onAdvance(phase, registeredParties);
    }
  }

  public static void main(String[] args) {
    final Phaser phaser = new MyPharser(() -> System.out.println("all tasks completed work."));
    final Random random = new Random();
    IntStream.range(0, 10)
        .forEach(
            (x) ->
                new Thread(
                        () -> {
                          phaser.register();
                          try {
                            TimeUnit.SECONDS.sleep(random.nextInt(10));
                            System.out.println(Thread.currentThread().getName() + "完成任务！");
                            phaser.arriveAndAwaitAdvance();
                          } catch (InterruptedException e) {
                            e.printStackTrace();
                          }
                        },
                        "T-" + x)
                    .start());
  }
}
