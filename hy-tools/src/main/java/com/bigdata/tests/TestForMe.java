package com.bigdata.tests;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Program: hy-utils @ClassName: TestForMe @Author: huyi @Date: 2020-09-02
 * 00:11 @Description: @Version: V1.0
 */
public class TestForMe {
  public static void main(String[] args) {
    while (true) {
      System.out.println(ThreadLocalRandom.current().nextInt(10) + 1);
    }
  }
}
