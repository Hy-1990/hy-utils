package com.bigdata.hy_tools.stu;

import org.springframework.util.StopWatch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author huyi
 * @Date 2020/9/1 15:00
 * @Description:
 */
public class StuStopWatch {
    public static void test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
    }

  public static void main(String[] args) throws InterruptedException {
      StopWatch stopWatch = new StopWatch();
      stopWatch.start("测试");
      test();
      stopWatch.stop();
      System.out.println(stopWatch.prettyPrint());
  }
}
