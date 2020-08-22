package com.bigdata.hy_tools.stu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StuSet {
    public static final Set set = new HashSet();

    public static void task() {
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            task();
        };

        int threadNum = 10;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(set.size());
    }
}
