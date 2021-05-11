package com.bigdata.tests;

import ai.guiji.vtesdk.common.TaskType;
import ai.guiji.vtesdk.handle.VteConnection;
import ai.guiji.vtesdk.pojos.params.AdvancedParam;
import ai.guiji.vtesdk.pojos.params.Param;
import ai.guiji.vtesdk.pojos.params.TtsTaskParam;
import com.alibaba.fastjson.JSON;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/** @Author huyi @Date 2021/4/21 0:20 @Description: */
public class Test11 {
  public static <T> T getName(Function<String, T> function, Class<T> clazz) {
    String a = "a1";
    return function.apply(a);
  }

  public static <T> T getName1(Supplier<T> supplier, Class<T> clazz) {
    String a = "a1";
    return supplier.get();
  }

  public static void test1() {
    VteConnection vteConnection =
        new VteConnection().setIp("172.16.103.12").setPort(80).setGroupName("ovs").setPollTime(1);
    TtsTaskParam ttsTaskParam =
        new TtsTaskParam()
            .setTtsEngine("tts:215")
            .setVolume(0)
            .setSoundCode("2")
            .setSpeechRate(1)
            .setContentList(Collections.singletonList("who are you? i am lilei"));
    Param param = new Param().setTtsTaskParam(ttsTaskParam).setType(TaskType.TTS.getCode());
    AdvancedParam advancedParam = new AdvancedParam().addParam(0, param);
    String url =
        vteConnection.sync(
            JSON.toJSONString(advancedParam),
            taskVo1 -> {
              System.out.println(JSON.toJSONString(taskVo1));
              return taskVo1.getRootTask().getTtsUrl();
            },
            String.class,
            1,
            TimeUnit.MINUTES);
    System.out.println(url);
  }

  public static void test2(String code) {
    VteConnection vteConnection =
        new VteConnection().setIp("172.16.103.12").setPort(80).setGroupName("xec");
    System.out.println(JSON.toJSONString(vteConnection.query(code)));
  }

  public static void main(String[] args) {
    test2("23123");
  }

}
