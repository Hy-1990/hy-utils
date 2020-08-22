package com.bigdata.hy_tools.stu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @author huyi
 * @date 2020/5/21 10:08
 */
public class StuList {
  private String name;
  private int age;

  public StuList(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "StuList{" + "name='" + name + '\'' + ", age=" + age + '}';
  }

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("c");

    list.forEach((x) -> System.out.println(x));

    list.removeIf(x -> x.equals("c"));

    list.forEach((x) -> System.out.println(x));

    String result = String.join(",", list);
    System.out.println(result);

    List<StuList> stuLists = new ArrayList<>();
    stuLists.add(new StuList("a", 1));
    stuLists.add(new StuList("b", 2));
    stuLists.add(new StuList("c", 3));
    stuLists.add(new StuList("d", 4));

    // 排序方法的使用
    List<StuList> results = new ArrayList<>();
    results =
        stuLists.stream()
            .sorted(
                Comparator.comparing(StuList::getAge).reversed().thenComparing(StuList::getName))
            .collect(Collectors.toList());
    results.forEach((x) -> System.out.println(x.toString()));

    // 排序数组
    int[] ints = {1, 2, 7, 3, 9, 0, 4, 5};
    ints = Arrays.stream(ints).sorted().toArray();
    IntStream.of(ints).boxed().collect(Collectors.toList()).forEach(System.out::println);
    //        DoubleStream.of(new double[]{0.1, 0.2, 0.3}).toArray();

  }
}
