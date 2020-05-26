package com.bigdata.hy_tools.stu;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author huyi
 * @date 2020/5/21 10:18
 */
public class StuReflect {
    private String color;

    public String getColor() {
        return color;
    }

    public void a() {
        System.out.println("Trump is SB!");
    }

    //利用反射原理，修改类的私有成员变量值，如果有set方法，这东西没吊用
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<StuReflect> stuReflectClass = StuReflect.class;
        StuReflect stuReflect = stuReflectClass.newInstance();
        Field color = stuReflectClass.getDeclaredField("color");
        color.setAccessible(true);
        color.set(stuReflect, "green");
        System.out.println(stuReflect.getColor());

        Method method = stuReflectClass.getMethod("a");
        method.invoke(stuReflect);
    }
}
