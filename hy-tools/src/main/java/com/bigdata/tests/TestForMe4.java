package com.bigdata.tests;

import log.HyLogger;

import java.time.LocalDate;

/** @Author huyi @Date 2021/1/7 9:13 @Description: */
public class TestForMe4 {
  public static void main(String[] args) {
    //    double a = 1;
    //    double b = 100;
    //    while (a <= 100){
    //      double c = Math.ceil(30 + (a / b) * 40);
    //      System.out.println((int) c);
    //      a++;
    //    }
    String url = "flv_663257050326900736.mp4";
    System.out.println(url.substring(url.lastIndexOf(".")));
    LocalDate start = LocalDate.of(2021, 1, 10);
    LocalDate end = LocalDate.of(2038, 6, 8);
    System.out.println(end.toEpochDay() - start.toEpochDay());

    try {
      String a = null;
      System.out.println(a.startsWith("haha"));
    } catch (Exception throwable) {
      HyLogger.logger().error("hy->{}", throwable.getMessage());
    }
  }
}
