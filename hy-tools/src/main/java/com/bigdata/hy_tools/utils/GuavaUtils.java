package com.bigdata.hy_tools.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.*;
import java.util.stream.Collectors;

public class GuavaUtils {
    public static void main(String[] args) {
        //splitter 切割工具方法类的使用
        Set set = new HashSet();
        String aa = "今天，我加入，了，硅基智能 ，是吧";
        List<String> list = Splitter.on("，").trimResults().omitEmptyStrings().splitToList(aa);
        list.forEach(set::add);
        System.out.println(list.toString());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        String bb = "11,100 ,200";
        List<Integer> list1 = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(bb).stream()
                .map(Integer::parseInt).collect(Collectors.toList());
        list1.forEach(System.out::println);

        String ee = "a=c,b=d";
        Map<String,String> map = Splitter.on(",").trimResults().withKeyValueSeparator("=").split(ee);
        System.out.println(map.toString());

        //joiner 合并工具类的使用
        String cc = Joiner.on(", ").appendTo(new StringBuilder(), "a", "b").toString();
        String dd = String.join(",", list);
        System.out.println(cc);
        System.out.println(dd);

    }
}
