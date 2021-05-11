package com.bigdata.hy_tools.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.io.IOException;

/** @Author huyi @Date 2021/1/14 20:25 @Description: 雪花算法工具类 */
public class SnowFlakeUtil {
  public static final Snowflake SNOWFLAKE = IdUtil.getSnowflake(1, 2);

  public static String getId() {
    return SNOWFLAKE.nextId() + "";
  }

  public static void main(String[] args) throws IOException {
    //    BufferedImage sourceImg =
    //        ImageIO.read(
    //            new URL("http://172.16.103.233/nfs/ss-img/a7957f3a504dfb83e2d0e0399e6ea0e5.png"));
    //    System.out.println(sourceImg.getWidth() + " " + sourceImg.getHeight());
    //    List<Data> list =
    //        new ArrayList<Data>() {
    //          {
    //            add(Data.builder().name("0").build());
    //            add(Data.builder().name("15000").build());
    //            add(Data.builder().name("3000").build());
    //          }
    //        };
    //    System.out.println(list.toString());
    //
    //    System.out.println(
    //        list.stream()
    //            .sorted(Comparator.comparing(x -> Integer.parseInt(x.getName())))
    //            .collect(Collectors.toList()));
    String a = null;
    System.out.println(a.startsWith("答题"));
  }
}
