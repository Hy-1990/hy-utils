package com.bigdata.hy_tools.stu;

import com.google.common.util.concurrent.Monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/** @Author huyi @Date 2020/8/24 20:01 @Description: */
public class TestMonitor {
  private static final Integer MAX = 20;

  public static void main(String[] args) {
    List<Integer> cul = new ArrayList<>();
    Monitor monitor = new Monitor();
    Monitor.Guard guard =
        new Monitor.Guard(monitor) {
          @Override
          public boolean isSatisfied() {
            return cul.size() <= MAX;
          }
        };
    IntStream.range(0, 10001)
        .forEach(
            (x) -> {
              if (monitor.enterIf(guard)) {
                try {
                  cul.add(x);
                } finally {
                  monitor.leave();
                }
              } else {
                System.out.println(cul.toString());
                cul.clear();
              }
            });
    System.out.println(cul.toString());
  }
}
