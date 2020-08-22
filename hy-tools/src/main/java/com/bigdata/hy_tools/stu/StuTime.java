package com.bigdata.hy_tools.stu;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @Author huyi
 * @Date 2020/7/21 17:13
 * @Description: 学习time库
 */
public class StuTime {
    public static void main(String[] args) {
        System.out.println(LocalDate.MAX);
        System.out.println(LocalDate.now().toString());
        System.out.println(LocalTime.now().toString());
        System.out.println(LocalDate.of(2019,11,9));
        System.out.println(LocalTime.now().getMinute());


    }
}
