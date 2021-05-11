package com.bigdata.tests;

import com.bigdata.hy_tools.utils.EmptyUtil;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/** @Author huyi @Date 2020/10/21 16:46 @Description: */
public class TestForMe2 {
  private static LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
  private static Lock lock = new ReentrantLock();
  private static String completed = "0";
  private static Monitor monitor = new Monitor();
  private static final Monitor.Guard INC_WHEN_LESS_10 =
      new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
          return completed.equals("1") || completed.equals("2");
        }
      };

  public static void handleMessage() {
    lock.lock();
    try {
      List<String> list = new ArrayList<>();
      while (true) {
        if (list.size() < 5){
            break;
        }
        IntStream.range(0, 5)
            .forEach(
                x -> {
                  String msg = messages.poll();
                  if (EmptyUtil.isNotEmpty(msg)) {
                    list.add(msg);
                  }
                });
        sendMsg(list);
        boolean success = monitor.enterWhen(INC_WHEN_LESS_10, 15, TimeUnit.SECONDS);
        if (success) {
          // ...
        } else {
          // ...
        }
      }
    } catch (Exception e) {

    } finally {
      lock.unlock();
    }
  }

  private static void sendMsg(List<String> messages) {
    messages.forEach(System.out::println);
    return;
  }

  public static void main(String[] args) {
    //    Snowflake snowflake = IdUtil.getSnowflake(1, 1);
    //    System.out.println(snowflake.nextId());
    //    System.out.println(LocalDate.now().toString());
    //    System.out.println(System.currentTimeMillis());
    //    System.out.println(11 * 3 / 2);
    //    System.out.println(EnumUtil.getFieldValues(SysUserStatusEnum.class,"code"));
    //    System.out.println(EnumUtil.contains(SysUserStatusEnum.class,"OPEN"));
    Lists.newArrayList(2).forEach(System.out::println);
  }
}
