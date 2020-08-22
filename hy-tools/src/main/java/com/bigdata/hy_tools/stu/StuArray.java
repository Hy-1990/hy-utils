package com.bigdata.hy_tools.stu;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author huyi
 * @date 2020/6/12 16:20
 */
public class StuArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,4};
        int[] nums2 = new int[]{0,5,6};

        List<Integer> list1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> list2 = IntStream.of(nums2).boxed().collect(Collectors.toList());
        list1.remove(list2);
        list1.addAll(list2);
        Collections.sort(list1);
        Integer[] nums = list1.toArray(new Integer[list1.size()]);
        Arrays.stream(nums).collect(Collectors.toList()).forEach((x) -> System.out.println(x));
        Collections.reverse(list1);
        nums = list1.toArray(new Integer[list1.size()]);
        Arrays.stream(nums).collect(Collectors.toList()).forEach((x) -> System.out.println(x));

        int a = 123;
        double result = Math.sqrt((double)a);
        System.out.println(result);
    }
}
