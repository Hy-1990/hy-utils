package com.bigdata.hytest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

/** @Author huyi @Date 2020/9/18 12:39 @Description: 测试父类 */
@SpringBootTest
public class TestFather {
  private StopWatch stopWatch;

  @BeforeEach
  void setUp() {
    System.out.println("测试开始：" + System.currentTimeMillis());
    stopWatch = new StopWatch();
    stopWatch.start();
  }

  @AfterEach
  void tearDown() {
    System.out.println("测试结束：" + System.currentTimeMillis());
    stopWatch.stop();
    System.out.println(stopWatch.prettyPrint());
  }
}
