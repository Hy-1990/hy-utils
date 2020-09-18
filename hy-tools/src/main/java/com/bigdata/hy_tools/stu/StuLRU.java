package com.bigdata.hy_tools.stu;

import java.util.LinkedHashMap;

/**
 * @author huyi
 * @date 2020/5/21 09:34
 */
public class StuLRU {
  public static void main(String[] args) {
    // LRU 缓存机制  类似python3 deque
    LinkedHashMap linkedHashMap = new LinkedHashMap(16, 0.75f, true);
    linkedHashMap.put("a", "haha1");
    linkedHashMap.put("b", "haha2");
    linkedHashMap.put("c", "haha3");
    linkedHashMap.put("d", "haha4");

    linkedHashMap.forEach((k, v) -> System.out.println(k + "," + v));

    linkedHashMap.get("a");
    System.out.println("----------------");

    linkedHashMap.get("b");
    System.out.println("----------------");

    linkedHashMap.forEach((k, v) -> System.out.println(k + "," + v));
    System.out.println(
        linkedHashMap.keySet().stream().map(x -> linkedHashMap.get(x)).findFirst().get());
    linkedHashMap.forEach((k, v) -> System.out.println(k + "," + v));
  }
}
