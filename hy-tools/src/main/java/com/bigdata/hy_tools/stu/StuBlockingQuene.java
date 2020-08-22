package com.bigdata.hy_tools.stu;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author huyi
 * @Date 2020/7/23 11:18
 * @Description: 学习阻塞队列
 */
public class StuBlockingQuene {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<String>(){{
            put("a");
            put("b");
            put("c");
        }};
        System.out.println(linkedBlockingDeque.peekFirst());
        System.out.println(linkedBlockingDeque.peekFirst());
        linkedBlockingDeque.forEach(x -> System.out.println(x));
    }
}
