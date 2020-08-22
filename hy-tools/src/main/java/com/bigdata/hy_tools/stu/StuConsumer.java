package com.bigdata.hy_tools.stu;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author huyi
 * @Date 2020/7/2014:30
 * @Description: 学习java8 消费方法
 */
public class StuConsumer {

    public static String getFunc(Integer num, Function<Integer, String> function) {
        return function.apply(num);
    }

    public static void main(String[] args) {
        Consumer<Integer> consumer = x -> {
            for (int i = 0; i < x; i++) {
                System.out.println(i);
            }
        };
//        consumer.accept(10);
        Consumer<Integer> consumer1 = x -> {
            int a = 0;
            for (int i = 0; i < x; i++) {
                a += i;
            }
            System.out.println(a + "");
        };
        consumer.andThen(consumer1).accept(10);

        Function<Integer,String> function = x -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < x; i++) {
                list.add(i);
            }
            return Joiner.on(",").join(list);
        };


        System.out.println(getFunc(10,x -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < x; i++) {
                list.add(i);
            }
            return Joiner.on(",").join(list);
        }));
    }
}
