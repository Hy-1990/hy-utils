package com.bigdata.tests;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.URLUtil;

import java.util.ArrayList;
import java.util.List;

/** @Author huyi @Date 2021/3/19 11:46 @Description: */
public class Test8 {
  public interface SomeInf {
    void fun();
  }

  public static void main(String[] args) throws InterruptedException {
    //    ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, List<Integer>>> groupProcessorPool =
    //        new ConcurrentHashMap<>();
    //    ConcurrentHashMap<Integer, List<Integer>> map = new ConcurrentHashMap<>();
    //    map.put(1, new ArrayList<>(Arrays.asList(1, 2, 3)));
    //    groupProcessorPool.put(1, map);
    //    System.out.println(groupProcessorPool);
    //    groupProcessorPool.get(1).get(1).add(4);
    //    System.out.println(groupProcessorPool);

    //    Map<Integer,List<Integer>> map = new HashMap<>();
    //    map.put(1,Arrays.asList("haha"))
    //    Map<Integer,String> map = new HashMap<>();
    //    map.put(1,"haha1");
    //    map.put(2,"haha2");
    //    map.put(3,"haha3");
    //    List<Integer> list = new ArrayList<>(Arrays.asList(1,2));

    //
    // map.keySet().stream().filter(list::contains).collect(Collectors.toSet()).forEach(map::remove);
    //    System.out.println(map);
    //    ConcurrentHashMap<Integer, ConcurrentHashMap<String, AtomicInteger>> map =
    //        new ConcurrentHashMap<>();
    //    ConcurrentHashMap<String, AtomicInteger> map1 = new ConcurrentHashMap<>();
    //    map1.put("haha", new AtomicInteger(0));
    //    map.put(1,map1);
    //    System.out.println(map);
    //    ConcurrentHashMap<String, AtomicInteger> map2 = map.get(1);
    //    map2.get("haha").incrementAndGet();
    //    System.out.println(map);

    //        ConcurrentLinkedDeque<Integer> taskQueue = new ConcurrentLinkedDeque<>();
    //        taskQueue.add(1);
    //        taskQueue.add(2);
    //        taskQueue.add(3);
    //        taskQueue.add(4);
    //        taskQueue.add(5);
    //        taskQueue.add(6);
    //        taskQueue.add(7);
    //        while (taskQueue.size() > 0){
    //          System.out.println(taskQueue.poll());
    //        }
    //        System.out.println(taskQueue.poll());

    //    ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
    //    map.keySet().forEach(System.out::println);

    //    CountDownLatch latch = new CountDownLatch(3);
    //    IntStream.range(0, 3)
    //        .forEach(
    //            x ->
    //                new Thread(
    //                        () -> {
    //                          try {
    //                            TimeUnit.SECONDS.sleep(x + 3);
    //                            System.out.println("haha" + x);
    //                          } catch (Exception e) {
    //                            e.printStackTrace();
    //                          } finally {
    //                            latch.countDown();
    //                          }
    //                        })
    //                    .start());
    //    latch.await();
    //    System.out.println("结束");

    //    Map<Integer, String> map = new HashMap<>();
    //    map.put(1, "haha");
    //    map.put(2, "hahah");
    //    System.out.println(
    //        JSON.toJSONString(
    //            TestEntity.builder().map(map).name("aa").build(),
    //            SerializerFeature.WriteNonStringKeyAsString));

    //    String url =
    // "/real2d/video_file/681022276904296448_1_b12cface-d808-429c-8792-6e996a459c36.mp4";
    //    List<String> paths = Splitter.on("/").splitToList(url);
    //    System.out.println(
    //        Joiner.on("/")
    //            .join(
    //                paths.stream()
    //                    .filter(x -> !x.equals(paths.get(paths.size() - 1)))
    //                    .collect(Collectors.toList())));
    //    System.out.println(Splitter.on(".").splitToList(paths.get(paths.size() - 1)).get(0));
    //    System.out.println(
    //        Joiner.on("/")
    //                .join(
    //                    paths.stream()
    //                        .filter(x -> !x.equals(paths.get(paths.size() - 1)))
    //                        .collect(Collectors.toList()))
    //            + "/"
    //            + Splitter.on(".").splitToList(paths.get(paths.size() - 1)).get(0)
    //            + ".srt");

    //    long timestamp = 19123;
    //    Date date = new Date(timestamp);
    //    DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
    //    formatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    //    String dateFormatted = formatter.format(date);
    //    System.out.println(dateFormatted);
    //
    //    Map<String, String> map = new HashMap<>();
    //    map.put("0", "haha-0");
    //    map.put("2", "haha-2");
    //    map.put("1", "haha-1");
    //    map.put("-1", "haha--1");
    //    System.out.println(map);
    //    for (String key :
    //        map.keySet().stream()
    //            .sorted(Comparator.comparing(Convert::toInt))
    //            .collect(Collectors.toCollection(LinkedHashSet::new))) {
    //      System.out.println(map.get(key));
    //    }
    //    System.out.println(Joiner.on("").join(map.keySet()));
    //    String param = "{0:\"572457141097738251\"}";
    //    Map<Integer, String> map = JSONObject.parseObject(param, Map.class);
    //    System.out.println(map);

    //    String param= "{\"action\": \"update\", \"data\": \"inference time :
    // 0.8756604194641113\"}";
    //    JSONObject result = JSON.parseObject(param);
    //    System.out.println("progress".equals(result.getString("data")));

    //    Data8 data8 =
    //        Data8.builder()
    //            .someInf(
    //                () -> System.out.println("haha"))
    //            .build();
    //    data8.getSomeInf().fun();

//    String aa = "[[aa]]+aaaaaa";
//    List<String> matchSign1 = ReUtil.findAll("\[\[(.*?)\]\]", aa, 1, new ArrayList<String>());
//    matchSign1.forEach(System.out::println);
    String a = "{\"action\":\"start\",\"extParams\":\"flv\",\"jobID\":\"580474581685645533_cf3bbd54-7d9a-4e9b-a7c7-82a07b7ea0d2\",\"jobType\":\"picinpic\",\"mainVideo\":\"http://172.16.103.233/nfs//vte/video/2dr/580474494595117276/580474494595117276.flv\",\"mediaList\":[{\"movie\":\"http://172.16.103.233:80/nfs/video-server/png/啥都没干.png\",\"overlayX\":0,\"overlayY\":0,\"scaleX\":721,\"scaleY\":306,\"time\":\"0,15400\"}],\"ratio\":1,\"resolutionX\":720,\"resolutionY\":1280,\"seq\":0}";
    System.out.println(a);
  }
}
