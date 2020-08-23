package com.bigdata.hy_tools.stu;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * @Program: hy-utils @ClassName: RequestLimiterHandle @Author: huyi @Date: 2020-08-24
 * 01:45 @Description: @Version: V1.0
 */
public class RequestLimiterHandle {
  @Data
  static class Request {
    private final int data;

    public Request(int data) {
      this.data = data;
    }
  }
  // 请求队列
  private final ConcurrentLinkedQueue<Request> bucket = new ConcurrentLinkedQueue<>();
  // 队列上限
  private static final int BUCKET_CAPACITY = 1000;
  // 漏桶下沿水流速度
  private final RateLimiter rateLimiter = RateLimiter.create(10.0D);
  // 请求监视器
  private final Monitor requestMoniter = new Monitor();
  // 处理监视器
  private final Monitor handleMoniter = new Monitor();

  public void submitRequest(int data) {
    this.submitRequest(new Request(data));
  }

  public void submitRequest(Request request) {
    // 请求监视器，创建监视向导，队列数据量小于上限
    if (requestMoniter.enterIf(requestMoniter.newGuard(() -> bucket.size() < BUCKET_CAPACITY))) {
      try {
        boolean result = bucket.offer(request);
        if (result) {
          System.out.println("成功向队列加入新的请求！" + Thread.currentThread() + " request:" + request);
        } else {
          System.out.println("加入新请求失败！");
        }
      } finally {
        requestMoniter.leave();
      }
    } else {
      // 队列已满
      System.out.println("请求队列已经上限，请稍后重试！");
    }
  }

  // 处理请求方法
  public void handleRequest(Consumer<Request> consumer) {
    if (handleMoniter.enterIf(handleMoniter.newGuard(() -> !bucket.isEmpty()))) {
      try {
        // 匀速处理
        rateLimiter.acquire();
        consumer.accept(bucket.poll());
      } finally {
        handleMoniter.leave();
      }
    }
  }
}
