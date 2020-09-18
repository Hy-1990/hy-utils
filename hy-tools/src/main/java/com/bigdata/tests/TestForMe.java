package com.bigdata.tests;

import com.bigdata.hy_tools.entity.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Program: hy-utils @ClassName: TestForMe @Author: huyi @Date: 2020-09-02
 * 00:11 @Description: @Version: V1.0
 */
public class TestForMe {
  public static void main(String[] args) {
    //    while (true) {
    //      System.out.println(ThreadLocalRandom.current().nextInt(10) + 1);
    //    }
    //    List<Data> list =
    //        new ArrayList<Data>() {
    //          {
    //            add(new Data("hy1", 10));
    //            add(new Data("hy2", 11));
    //          }
    //        };
    //    list.forEach(System.out::println);
    //    list =
    //        list.stream()
    //            .peek(
    //                x -> {
    //                  if (x.getName().equals("hy2")) {
    //                    x.setMoney(10000);
    //                  }
    //                })
    //            .collect(Collectors.toList());
    //    list.forEach(System.out::println);
    //
    //    System.out.println(103455%60);
    //    System.out.println("a".contains("a"));
    //    System.out.println(LocalDate.parse("2020-09-21",
    // DateTimeFormatter.ofPattern("yyyy-MM-dd")).toEpochDay());
        System.out.println(903%60);
    //    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //    System.out.println(new Date());

    List<Data> list = new ArrayList<>(Arrays.asList(new Data("h1", 0), new Data("h2", 0)));
    list = list.stream().peek(x -> x.setMoney(100)).collect(Collectors.toList());
    System.out.println(list);

  }
}
