package com.bigdata.hy_tools.stu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author huyi
 * @Date 2020/7/21 16:27
 * @Description: 学习Optional
 */
public class StuOptional {
    public static void main(String[] args) {
        List<Demo> demos = new ArrayList<>();
        demos.add(new Demo("c","d"));
        demos.add(new Demo("a","b"));
        Optional<Demo> optionalDemo = demos.stream().filter((x) -> x.getName().equals("a")).findAny();
        if (optionalDemo.isPresent()){
            System.out.println(optionalDemo.get());
        }else {
            System.out.println("null");
        }
    }
}

class Demo {
    private String name;
    private String age;

    public Demo() {
    }

    public Demo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
