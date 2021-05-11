package com.bigdata.research;

import java.util.function.Function;

/** @Author huyi @Date 2021/2/6 15:32 @Description: 调度 */
public abstract class Dispatch<T extends Param> {
  public abstract String get(Function<String, T> function);
}
