package com.bigdata.hy_tools.stu;

import org.joda.time.DateTime;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/** @Author huyi @Date 2020/8/17 9:11 @Description: JMH */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class StuJMH {
  static int millis = 24 * 3600 * 1000;

  public static void main(String[] args) throws Exception {
    Options options = new OptionsBuilder().include(StuJMH.class.getName()).forks(1).build();
    new Runner(options).run();
  }

  @Benchmark
  @Threads(5)
  public void runCalendar() {
    Calendar calendar = Calendar.getInstance();
  }

  @Benchmark
  @Threads(5)
  public void runJoda() {
    DateTime dateTime = new DateTime();
  }

  //
  @Benchmark
  @Threads(5)
  public void runSystem() {
    long result = System.currentTimeMillis() / millis;
  }
}
