package com.bigdata.hy_tools.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author huyi
 * @Date 2020/7/22 15:08
 * @Description: ceshi
 */
@Data
@ToString
public class Cat2 {
    private String name;
    private String age;
    private String type;

    public Cat2() {
    }

    public Cat2(String name, String age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }
}
