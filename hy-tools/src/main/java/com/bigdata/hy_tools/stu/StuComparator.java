package com.bigdata.hy_tools.stu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author huyi
 * @Date 2020/7/22 9:00
 * @Description: 比较器学习
 */
public class StuComparator {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>() {{
            add(new Person("a", 10));
            add(new Person("b", 11));
            add(new Person("c", 100));
            add(new Person("d", 9));
            add(new Person("e", 1));
        }};

        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPrice() - o2.getPrice();
            }
        };
        Collections.sort(list, comparator);

        list.forEach(x -> System.out.println(x.toString()));

        List<Person> result = list.stream().sorted(Comparator.comparing(Person::getPrice).reversed())
                .collect(Collectors.toList());

        result.forEach(System.out::println);


    }
}

class Person {
    private String name;
    private int price;

    public Person(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
