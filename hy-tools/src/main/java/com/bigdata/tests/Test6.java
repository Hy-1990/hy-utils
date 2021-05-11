package com.bigdata.tests;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/** @Author huyi @Date 2021/3/4 14:55 @Description: */
public class Test6 {

  public static int[] randomCommon(int min, int max, int n) {
    if (n > (max - min + 1) || max < min) {
      return null;
    }
    int[] result = new int[n];
    int count = 0;
    while (count < n) {
      int num = (int) (Math.random() * (max - min)) + min;
      boolean flag = true;
      for (int j = 0; j < n; j++) {
        if (num == result[j]) {
          flag = false;
          break;
        }
      }
      if (flag) {
        result[count] = num;
        count++;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    //    System.out.println(IntStream.of(randomCommon(12, 13,
    // 1)).boxed().collect(Collectors.toList()));
    //    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, -3, 4, 5, 6));
    //    System.out.println(list.stream().min(Integer::compare).get());
    //    System.out.println(list.stream().sorted().collect(Collectors.toList()));
    //    Map<Integer, Data> map = new HashMap<>();
    //    map.put(1, Data.builder().money(100).build());
    //    map.put(2, Data.builder().money(200).build());
    //    System.out.println(JSON.toJSONString(map));
    //    String url = "http://baidu.com//1#haha";
    //    System.out.println(URLUtil.encode(url));
    ConcurrentHashMap<Integer, String> a = new ConcurrentHashMap<>();
    new Thread(
            () -> {
              while (true) {
                try {
                  TimeUnit.SECONDS.sleep(2);
                  synchronized (a) {
                    a.clear();
                    IntStream.range(0, 3 + new Random().nextInt(3))
                        .forEach(x -> a.put(x, "haha-" + x));
                    System.out.println("加载:" + a.toString());
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
    new Thread(
            () -> {
              while (true) {
                try {
                  TimeUnit.SECONDS.sleep(1);
                  synchronized (a) {
                    System.out.println("探针:" + a.toString());
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
  }
}
