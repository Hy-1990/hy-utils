package com.bigdata.hy_tools.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.*;

/**
 * @author huyi
 * @date 2020/5/12 3:17 PM
 */
public class ParseUtil {
    public static String getResult(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group(matcher.groupCount()));
        }
//        System.out.println(matcher.groupCount());
//        String result = matcher.group();
        return "";
    }


    public static void main(String[] args) {
//      String result = ParseUtil.getResult("[1.3.6.1.2.1.2.1.0 = asd]", ".*=\\s*(.*)]");
        try {
            System.out.println("hah");
            String result = ParseUtil.getResult("123123123撒打算打算去闻气味asdas123123asd", "*");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(result);
        String[] testArray = {"a", "b", "c"};
        Arrays.asList(testArray).forEach((x) -> System.out.println(x));
        Iterator<String> iterable = Arrays.asList(testArray).iterator();
        while (iterable.hasNext()) {
            System.out.println(iterable.next());
        }
    }

}
