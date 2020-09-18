package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.entity.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Program: hy-utils @ClassName: StuStreamCollectorsReducing @Author: huyi @Date: 2020-08-28
 * 22:56 @Description: @Version: V1.0
 */
public class StuStreamCollectorsReducing {
  public static void main(String[] args) {
    List<Data> list =
        new ArrayList<Data>() {
          {
            add(new Data("h1", 9));
            add(new Data("h2", 80));
            add(new Data("h3", 60));
          }
        };
    Optional<Data> data =
        list.stream()
            .collect(Collectors.reducing((d1, d2) -> d1.getMoney() > d2.getMoney() ? d1 : d2));
    Integer dataSize = list.stream().mapToInt(x -> x.getMoney()).sum();
    System.out.println(dataSize);
    System.out.println(data.get().toString());

    long sum = Stream.iterate(0L, l -> l + 1L).limit(10_000_000).parallel().reduce(0L, Long::sum);
    System.out.println(sum);
  }
}
