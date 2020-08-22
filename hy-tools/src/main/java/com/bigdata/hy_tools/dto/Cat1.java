package com.bigdata.hy_tools.dto;


import lombok.Data;
import lombok.ToString;

/**
 * @Author huyi
 * @Date 2020/7/22 15:05
 * @Description: ceshi
 */
@Data
@ToString
public class Cat1 {
    private String name;
    private String age;

    public Cat1() {
    }

    public Cat1(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
