package com.bigdata.tests;

import lombok.Builder;
import lombok.Data;

/** @Author huyi @Date 2021/4/14 22:49 @Description: 看门口工厂类 */
@Data
public class WatchDogFactory {

  public static Dog createDog(String name, WatchDogInf watchDogInf) {
    return Dog.builder().name(name).watchDogInf(watchDogInf).build();
  }

  /** 看门狗接口 */
  public interface WatchDogInf {
    void watch();
  }

  @Data
  @Builder
  public static class Dog {
    private String name;
    private WatchDogInf watchDogInf;

    public void run() {
      System.out.println("[" + name + "]watch-dog发现嫌疑人，执行逮捕");
      watchDogInf.watch();
    }
  }
}
