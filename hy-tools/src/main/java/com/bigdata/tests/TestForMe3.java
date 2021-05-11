package com.bigdata.tests;

import com.alibaba.fastjson.JSONObject;
import com.bigdata.hy_tools.entity.MouthDetail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/** @Author huyi @Date 2020/10/27 9:27 @Description: */
public class TestForMe3 {
  public static void main(String[] args) throws ParseException {
    //    Father father = Father.builder().name("haha").age(100).build();
    //    Child child = new Child();
    //    BeanUtil.copyProperties(father,child);
    //    System.out.println(child.toString());
    //
    //    String a = "a";
    //    System.out.println(a == "a");
    //    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //    System.out.println(SDF.parse("2020-10-11 11:24:11"));
    //    String a = "";
    String response =
        "{\"1\": {\"phone\": \"sp\", \"start\": 0.0, \"end\": 0.05}, \"2\": {\"phone\": \"测\", \"start\": 0.05, \"end\": 0.25}, \"3\": {\"phone\": \"试\", \"start\": 0.25, \"end\": 0.57}, \"4\": {\"phone\": \"sp\", \"start\": 0.57, \"end\": 0.79}, \"5\": {\"phone\": \"看\", \"start\": 0.79, \"end\": 0.95}, \"6\": {\"phone\": \"看\", \"start\": 0.95, \"end\": 1.17}, \"7\": {\"phone\": \"字\", \"start\": 1.17, \"end\": 1.34}, \"8\": {\"phone\": \"幕\", \"start\": 1.34, \"end\": 1.53}, \"9\": {\"phone\": \"合\", \"start\": 1.53, \"end\": 1.74}, \"10\": {\"phone\": \"成\", \"start\": 1.74, \"end\": 2.01}, \"11\": {\"phone\": \"情\", \"start\": 2.01, \"end\": 2.27}, \"12\": {\"phone\": \"况\", \"start\": 2.27, \"end\": 2.56}, \"13\": {\"phone\": \"sp\", \"start\": 2.56, \"end\": 2.61}}";
    LinkedHashMap<String, JSONObject> oldMap =
        JSONObject.parseObject(response, new LinkedHashMap<String, JSONObject>().getClass());
    System.out.println(oldMap);
    List<String> list = new ArrayList<>();
    StringBuilder sentence = new StringBuilder();
    Double start = 0D;
    Double end = 0D;
    for (JSONObject value : oldMap.values()) {
      MouthDetail mouthDetail = JSONObject.toJavaObject(value, MouthDetail.class);

      if ("sp".equals(mouthDetail.getPhone()) && start.equals(0D) && end.equals(0D)) {
        continue;
      }
      if (!"sp".equals(mouthDetail.getPhone()) && start.equals(0D) && end.equals(0D)) {
        sentence.append(mouthDetail.getPhone());
        start = mouthDetail.getStart();
        continue;
      }
      if (!"sp".equals(mouthDetail.getPhone()) && !start.equals(0D)) {
        sentence.append(mouthDetail.getPhone());
        end = mouthDetail.getEnd();
        continue;
      }
      if ("sp".equals(mouthDetail.getPhone()) && !start.equals(0D) && !end.equals(0D)) {
        list.add(sentence.toString());
        sentence = new StringBuilder();
        start = 0D;
        end = 0D;
        continue;
      }
    }
    System.out.println(list);
  }
}
