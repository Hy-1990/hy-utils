package com.bigdata.tests;

import com.bigdata.hy_tools.entity.Data;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Program: hy-utils @ClassName: TestList @Author: huyi @Date: 2020-08-30
 * 15:52 @Description: @Version: V1.0
 */
public class TestList {
  public static void main(String[] args) {
    List<Data> list = new LinkedList<>();
    list.add(new Data("h1", 10));
    list.add(new Data("h2", 11));
    list.add(new Data("h3", 12));
    list.add(new Data("h4", 13));
    list.add(new Data("h1", 10));

    list.add(new Data("h5", 9));
    list = list.stream().sorted(Comparator.comparing(Data::getMoney)).collect(Collectors.toList());
    list.forEach(System.out::println);
    System.out.println("-----------------------------");
    Data data = list.get(0);
    if (data.getMoney() > 5) {
      list.remove(data);
    }
    list.forEach(System.out::println);
    System.out.println("-----------------------------");
    Data data1 = list.stream().filter(p -> p.getName().equals("h1")).findFirst().get();
    data1.setMoney(1000);
    list.forEach(System.out::println);
    System.out.println("-----------------------------");
    Optional<Data> data2 = list.stream().filter(p -> p.getName().equals("h6")).findFirst();
    data2.ifPresent(value -> System.out.println(value.getMoney()));
    System.out.println("-----------------------------");
    ConcurrentHashMap<String, Data> map =
        new ConcurrentHashMap<String, Data>(
            Arrays.asList(new Data("h7", 99)).stream()
                .collect(Collectors.toMap(Data::getName, p -> p)));
    System.out.println(map.toString());
    System.out.println("-----------------------------");
    List<Data> newList = list.stream().limit(3).collect(Collectors.toList());
    System.out.println(newList.toString());
    Map<Integer,List<Data>> map1 = new HashMap<>();
    map1.put(1,newList);
    System.out.println(map1.toString());
    List<Data> dataList = map1.get(1);
    dataList.add(new Data("h10",123));
    System.out.println(map1.toString());
    System.out.println("-----------------------------");
    Set<Integer> set = new HashSet<>();
    set.add(1);
    set.add(2);
    set.add(3);
    set.add(1);
    System.out.println(set.remove(5));
    System.out.println("-----------------------------");
    System.out.println(LocalDateTime.now().toString());
  }
}
