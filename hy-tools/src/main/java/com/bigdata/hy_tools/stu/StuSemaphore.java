package com.bigdata.hy_tools.stu;

import log.HyLogger;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuSemaphore @Author: huyi @Date: 2020-08-23 14:09 @Description:
 * 信息量 @Version: V1.0
 */
public class StuSemaphore {
  private static final Random RANDOM = new Random();

  public static void main(String[] args) {
    final int max = 10;
    final LoginService loginService = new LoginService(max);
    IntStream.range(0, 20)
        .forEach(
            i ->
                new Thread(
                        () -> {
                          try {
                            //                          boolean login = loginService.login();
                            //                          if (!login) {
                            //
                            // System.out.println(Thread.currentThread().getName() + " is refused");
                            //                            return;
                            //                          }
                            loginService.loginA();

                            sleep();
                          } catch (Exception e) {
                            HyLogger.logger().error(e.getMessage());
                          } finally {
                            loginService.loginOut();
                          }
                        },
                        "User-" + i)
                    .start());
  }

  private static void sleep() {
    try {
      TimeUnit.SECONDS.sleep(RANDOM.nextInt(10));
    } catch (InterruptedException e) {
      HyLogger.logger().error(e.getMessage());
    }
  }

  private static class LoginService {
    private final Semaphore semaphore;

    private LoginService(int size) {
      this.semaphore = new Semaphore(size);
    }

    public boolean login() {
      boolean login = semaphore.tryAcquire();
      if (login) {
        System.out.println(Thread.currentThread().getName() + " login success");
      }
      return login;
    }

    public void loginA() throws InterruptedException {
      semaphore.acquire();
      System.out.println(Thread.currentThread().getName() + " login success");
    }

    public void loginOut() {
      semaphore.release();
      System.out.println(Thread.currentThread().getName() + " login out success");
    }
  }
}
