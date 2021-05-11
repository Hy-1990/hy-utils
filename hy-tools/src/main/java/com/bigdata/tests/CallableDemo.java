package com.bigdata.tests;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/** @Author huyi @Date 2021/4/20 10:29 @Description: */
public class CallableDemo implements Supplier<String> {

  @Override
  public String get() {
    Test10.pool.put("a", Thread.currentThread());
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Test10.pool.remove("a");
    return "hhahahhh";
  }
}
