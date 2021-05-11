package com.bigdata.tests;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/** @Author huyi @Date 2021/2/9 14:56 @Description: */
public class Test5 {
  public static void main(String[] args) throws IOException {
    //
    String str =
        "{0}哈哈哈";
    System.out.println(MessageFormat.format(str,"10000"));
  }
}
