package com.bigdata.hytest.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/** @Author huyi @Date 2020/9/18 12:33 @Description: 测试重试工具 */
@Service
public class TestRetryService {

  private static int cul = 0;

  @Retryable(
      value = Exception.class,
      maxAttempts = 3,
      backoff = @Backoff(delay = 5000, multiplier = 1.5))
  public void printMsg() throws Exception {
    System.out.println("printMsg调用时间:" + LocalTime.now());
    if (cul < 2) {
      cul++;
      throw new Exception("调用失败！");
    }
    System.out.println("调用成功");
  }

  @Recover
  public void recover(Exception e) {
    System.out.println("回调方法执行!!!");
  }
}
