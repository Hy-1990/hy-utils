package com.bigdata.hy_tools.stu;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** @Author huyi @Date 2020/8/6 18:00 @Description: 了解 parallel */
public class StuParallelStream {
  public static void main(String[] args) {
    //
    Random random = new Random();
    List<Long> longs =
        new ArrayList<Long>() {
          {
            for (int i = 0; i < 10000000; i++) {
              //
              add(random.nextLong());
            }
          }
        };
    Long start = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    longs.parallelStream().forEach(System.out::println);
    Long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    System.out.println(end - start);
  }
}
