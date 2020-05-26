package com.bigdata.hy_tools.stu;

/**
 * @author huyi
 * @date 2020/5/21 11:49
 */
public class StuThread {
    public static void test(String a, String b) {
        System.out.println(a + "," + b);
    }

    public static void main(String[] args) {
        String h = "Trump";
        String g = "SB";
        new Thread(() -> StuThread.test(h, g)).start();
    }
}
