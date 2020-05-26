package com.bigdata.hy_tools.stu;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author huyi
 * @date 2020/5/21 11:13
 */
public class StuTimer {
    //自定义定时器，delay 延时，perind 间隔，为线程操作，需要手动关闭通道！
    public static void main(String[] args) throws InterruptedException {
        Timer time = new Timer();

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Trump is SB!");
            }
        }, 0, 1000);
        Thread.sleep(12000);
        time.cancel();
        System.out.println("closed");
    }
}
