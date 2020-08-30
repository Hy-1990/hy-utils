package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.entity.Data;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Program: hy-utils @ClassName: StuMapStream @Author: huyi @Date: 2020-08-28
 * 22:24 @Description: @Version: V1.0
 */
public class StuMapStream {
  public static void main(String[] args) {
    List<Data> list =
        new ArrayList<Data>() {
          {
            add(new Data("huyi", 10));
            add(new Data("haha", 101));
            add(new Data("haha", 33));
          }
        };

    Map<String, Integer> map =
        list.stream()
            .collect(
                Collectors.toMap(
                    Data::getName,
                    Data::getMoney,
                    (v1, v2) -> {
                      return v1 + v2;
                    }));
    map.forEach((k, v) -> System.out.println(k + ":" + v));

    Map<Boolean, List<Data>> map1 =
        list.stream().collect(Collectors.partitioningBy(p -> p.getMoney() > 50));
    map1.forEach((k, v) -> System.out.println(k + ":" + v.toString()));

    Map<String, List<Data>> map2 = list.stream().collect(Collectors.groupingBy(Data::getName));
    map2.forEach((k, v) -> System.out.println(k + ":" + v.toString()));
  }
}
