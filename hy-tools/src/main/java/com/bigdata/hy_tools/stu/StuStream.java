package com.bigdata.hy_tools.stu;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author huyi
 * @date 2020/5/21 11:07
 */
public class StuStream {
  // 使用filter和py3一样 里面是判断类型lambda
  public static void main(String[] args) {
    long count = IntStream.rangeClosed(1, 1000).filter(x -> x % 2 == 0).sum();
    System.out.println(count);
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("c");
    List<String> list1 = new ArrayList<>();
    list1 = list.stream().map(x -> x + " OK").collect(Collectors.toList());
    System.out.println(String.join(",", list1));

    String a = "67|66|65";
    List<Integer> list2 =
        Splitter.on("|").splitToList(a).stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    System.out.println(list2);

    System.out.println("----------------------------------");

    Stream.of(1, 2, 3, 4, 3, 2, 1).distinct().forEach(System.out::println);

    System.out.println("----------------------------------");

    int result =
        IntStream.range(0, 10)
            .peek(System.out::println)
            .map(i -> i * 2)
            .peek(System.out::println)
            .filter(x -> x > 10)
            .peek(System.out::println)
            .sum();
    System.out.println(result);
    System.out.println("----------------------------------");
    List<Integer> list3 =
        Stream.of(1, 2, 3, 9, 10, 20, 6, 5, 8, 39, 40, 76, 54, 12)
            .sorted(Comparator.reverseOrder())
            .limit(10)
            .collect(Collectors.toList());
    System.out.println(list3);
    Optional<Integer> first = list3.stream().findFirst();
    System.out.println(first.get());
    System.out.println(list3.stream().allMatch(x -> x > 20));
  }
}
