package com.bigdata.hy_tools.stu;

import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

public class StuConcurrentSkipListSet {
    public static final ConcurrentSkipListSet set = new ConcurrentSkipListSet();

    public static void task(String cul) {
        for (int i = 0; i < 10; i++) {
            set.add(cul + i);
            printAll();
        }
    }

    public static void printAll() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            stringBuffer.append(iterator.next() + ",");
        }
        System.out.println(stringBuffer.toString());
    }

    public static void main(String[] args) {
        Runnable runnable1 = () -> {
            task("a");
        };

        Runnable runnable2 = () -> {
            task("b");
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();


    }
}
