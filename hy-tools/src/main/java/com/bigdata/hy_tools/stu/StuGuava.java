package com.bigdata.hy_tools.stu;

import com.google.common.base.Joiner;
import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StuGuava {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("a");
        list1.add("b");

        list2.add("b");
        list1.add("c");

        list1.addAll(list2);
//        list1.forEach(System.out::println);

        Set set = new HashSet(list1);
        set.forEach(System.out::println);
        System.out.println(Joiner.on("|+|").join(set.iterator()));
        //同值获取 值队列
        HashMultimap<String, Integer> multimap = HashMultimap.create();
        multimap.put("a", 1);
        multimap.put("b", 2);
        multimap.put("c", 3);
        multimap.put("a", 4);
        System.out.println(multimap.get("a"));

        Multiset<String> multiset1 = HashMultiset.create();
        for (String str : list1) {
            multiset1.add(str);
        }
        multiset1.add("d",5);
        System.out.println(multiset1);
        multiset1.forEach(System.out::println);
        //反转
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("a","100");
        System.out.println(biMap.get("a"));
        System.out.println(biMap.inverse().get("100"));
        //两个元素标志一个元素  table
        Table<String,String,String> table = HashBasedTable.create();
        table.put("a","b","1");
        table.put("a","c","2");
        table.put("d","e","3");
        System.out.println(table.get("a","b"));
        System.out.println(table.row("a").get("c"));
        System.out.println(table.column("e").get("d"));


    }
}
