package com.bigdata.hy_tools.stu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/** @Author huyi @Date 2020/7/23 11:18 @Description: 学习阻塞队列 */
public class StuBlockingQuene {
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  private static class TestData {
    private Integer level;
    private String name;
  }

  public static void main(String[] args) throws InterruptedException {
    LinkedBlockingDeque<TestData> linkedBlockingDeque =
        new LinkedBlockingDeque<TestData>() {
          {
            put(new TestData(1, "小狸"));
            put(new TestData(3, "小张"));
            put(new TestData(2, "小美"));
          }
        };
    //    System.out.println(linkedBlockingDeque.peekFirst());
    //    System.out.println(linkedBlockingDeque.peekFirst());
    //    linkedBlockingDeque.forEach(x -> System.out.println(x));
    linkedBlockingDeque =
        new LinkedBlockingDeque<>(
            linkedBlockingDeque.stream()
                .sorted(Comparator.comparing(TestData::getLevel))
                .collect(Collectors.toList()));

    linkedBlockingDeque.forEach(System.out::println);
    System.out.println(linkedBlockingDeque.poll());
    System.out.println(111);
    linkedBlockingDeque.forEach(System.out::println);
  }
}
