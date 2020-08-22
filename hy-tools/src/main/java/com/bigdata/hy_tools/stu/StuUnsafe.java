package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.entity.Example;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static org.springframework.objenesis.instantiator.util.UnsafeUtils.getUnsafe;

/**
 * @Program: hy-utils @ClassName: StuUnsafe @Author: huyi @Date: 2020-08-22
 * 17:00 @Description: @Version: V1.0
 */
public class StuUnsafe {
  public static void main(String[] args) throws NoSuchFieldException {
    Example example = new Example();
    Unsafe unsafe = getUnsafe();
    System.out.println(example.getX());
    Field field = example.getClass().getDeclaredField("x");
    unsafe.putInt(example, unsafe.objectFieldOffset(field), 100000);
    System.out.println(example.getX());
  }
}
