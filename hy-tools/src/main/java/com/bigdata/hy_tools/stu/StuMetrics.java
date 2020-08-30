package com.bigdata.hy_tools.stu;

import com.codahale.metrics.*;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.health.jvm.ThreadDeadlockHealthCheck;

import java.util.concurrent.*;

/**
 * @Program: hy-utils @ClassName: StuMetrics @Author: huyi @Date: 2020-08-29
 * 00:00 @Description: @Version: V1.0
 */
public class StuMetrics extends HealthCheck {
  private static final MetricRegistry REGISTRY = new MetricRegistry();

  private static final HealthCheckRegistry HEALTH_CHECK_REGISTRY = new HealthCheckRegistry();

  private static final BlockingDeque<Long> QUEUE = new LinkedBlockingDeque<>();
  private static final ConsoleReporter REPORTER =
      ConsoleReporter.forRegistry(REGISTRY)
          .convertDurationsTo(TimeUnit.SECONDS)
          .convertRatesTo(TimeUnit.SECONDS)
          .build();
  private static final ExecutorService POOL = Executors.newCachedThreadPool();

  private static Counter counter = REGISTRY.counter("queue-size", Counter::new);

  private static Timer timer = REGISTRY.timer("request-time", Timer::new);

  public static void main(String[] args) {
    REGISTRY.register(
        MetricRegistry.name(StuMetrics.class, "queue-content"), (Gauge<String>) QUEUE::toString);
    // 健康检查
    HEALTH_CHECK_REGISTRY.register("test-metrics", new StuMetrics());
    HEALTH_CHECK_REGISTRY.register("thread-dead-lock", new ThreadDeadlockHealthCheck());

    REGISTRY.gauge("TEST-HY", () -> HEALTH_CHECK_REGISTRY::runHealthChecks);

    REPORTER.start(1, TimeUnit.SECONDS);

    POOL.execute(
        () -> {
          while (true) {
            Timer.Context context = timer.time();
            randomSleep();
            QUEUE.add(System.nanoTime());
            counter.inc();
            context.close();
          }
        });
    POOL.execute(
        () -> {
          while (true) {
            Timer.Context context = timer.time();
            randomSleep();
            if (QUEUE.poll() != null) {
              counter.dec();
            }
            context.close();
          }
        });
  }

  private static void randomSleep() {
    try {
      TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Result check() throws Exception {
    if (test() > 0) {
      return Result.healthy("test is success!");
    } else {
      return Result.unhealthy("test is fail");
    }
  }

  private static Integer test() {
    int a = ThreadLocalRandom.current().nextInt(10) - 5;
    return a;
  }
}
