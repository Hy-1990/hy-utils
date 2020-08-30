package com.bigdata.hy_tools.stu;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Program: hy-utils @ClassName: MetricsExample @Author: huyi @Date: 2020-08-28
 * 23:34 @Description: @Version: V1.0
 */
public class MetricsExample {
  private static final MetricRegistry REGISTRY = new MetricRegistry();
  private static final Meter REQUEST_METER = REGISTRY.meter("tags");
  private static final Meter sizeMeter = REGISTRY.meter("volume");

  public static void main(String[] args) {
    ConsoleReporter reporter =
        ConsoleReporter.forRegistry(REGISTRY)
            .convertRatesTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES)
            .build();
    reporter.start(10, TimeUnit.SECONDS);
    while (true) {
      upload(new byte[current().nextInt(1000)]);
      randomSleep();
    }
  }

  private static void upload(byte[] request) {
    REQUEST_METER.mark();
    sizeMeter.mark(request.length);
  }
  private static void randomSleep(){
    try{
      TimeUnit.SECONDS.sleep(current().nextInt(10));
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}
