package com.bigdata.hy_tools.utils;

import java.util.regex.*;

/**
 * @author huyi
 * @date 2020/5/12 3:17 PM
 */
public class ParseUtils {
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
//      String result = ParseUtils.getResult("[1.3.6.1.2.1.2.1.0 = asd]", ".*=\\s*(.*)]");
        String result = ParseUtils.getResult("123123123撒打算打算去闻气味asdas123123asd", "[\\d+]");
        System.out.println(result);
    }

}
