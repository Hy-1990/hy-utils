package com.bigdata.hy_tools.service;

import com.bigdata.hy_tools.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyi
 * @date 2020/5/14 9:12 AM
 */
@Service
public class TestService {
    private int count = 0;

    public void test(Integer param) {
        count = param;
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + count);
    }

    public static String test1(String hostNameString) {
        String context = hostNameString.replaceAll("\\[", "").replaceAll("\\]", "").trim();
        String[] text = context.trim().split("=");
        String context1 = (text.length == 2 ? text[1].trim() : "");
        if (context1.split("\\.").length > 1) {
            return context1.split("\\.")[0];
        } else {
            return context1;
        }
    }

//    public static void main(String[] args) {
//        List<String> list1 = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        List<String> list3 = null;
//        list1.add("a");
//        list1.add("b");
//        list2.add("c");
//        System.out.println(TestService.getMerges(list1, list2).toString());
//        System.out.println(TestService.getMergesHigh(list1, list2, list3));
//    }

    public static List<String> getMerges(List<String> list1, List<String> list2) {
        List<String> list = new ArrayList<>();
        if (EmptyUtil.isEmpty(list1) && EmptyUtil.isEmpty(list2)) {
            return list;
        } else if (EmptyUtil.isEmpty(list1) && !EmptyUtil.isEmpty(list2)) {
            return list2;
        } else if (!EmptyUtil.isEmpty(list1) && EmptyUtil.isEmpty(list2)) {
            return list1;
        } else {
            if (list1.size() == 0 && list2.size() == 0) {
                return list;
            } else if (list1.size() > 0 && list2.size() == 0) {
                return list1;
            } else if (list1.size() == 0 && list2.size() > 0) {
                return list2;
            } else {
                list.addAll(list1);
                list.addAll(list2);
            }
        }
        return list;
    }

    public static List<String> getMergesHigh(List<String>... list) {
        List<String> list_result = new ArrayList<>();
        for (List<String> list1 : list) {
            if (EmptyUtil.isEmpty(list1) || list1.size() == 0) {
                continue;
            } else {
                list_result.addAll(list1);
            }
        }
        return list_result;
    }
}
