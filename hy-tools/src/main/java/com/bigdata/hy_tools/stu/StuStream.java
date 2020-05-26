package com.bigdata.hy_tools.stu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author huyi
 * @date 2020/5/21 11:07
 */
public class StuStream {
    //使用filter和py3一样 里面是判断类型lambda
    public static void main(String[] args) {
        long count = IntStream.rangeClosed(1, 1000).filter(x -> x % 2 == 0).sum();
        System.out.println(count);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        List<String> list1 = new ArrayList<>();
        list1 = list.stream().map(x -> x + " OK").collect(Collectors.toList());
        System.out.println(String.join(",", list1));
    }
}
