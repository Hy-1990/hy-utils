package com.bigdata.hy_tools.stu;

import com.bigdata.hy_tools.dto.Cat1;
import com.bigdata.hy_tools.dto.Cat2;
import utilss.BeanUtil;

/**
 * @Author huyi
 * @Date 2020/7/22 14:59
 * @Description: 学习属性拷贝
 */
public class StuCopy {
    public static void main(String[] args) {
        Cat1 cat1 = new Cat1("1", "2");
        Cat2 cat2 = BeanUtil.copyProperties(Cat2.class, cat1);
        System.out.println(cat2);
    }
}
