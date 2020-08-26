package com.bigdata.hy_tools.stu;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Program: hy-utils @ClassName: StuFIleStream @Author: huyi @Date: 2020-08-26
 * 23:40 @Description: @Version: V1.0
 */
public class StuFIleStream {
  public static void main(String[] args) throws IOException {
    Files.lines(
            Paths.get("C:\\Users\\yi\\IdeaProjects\\hy-utils\\hy-utils\\data\\test.txt"),
            Charset.forName("UTF-8"))
        .forEach(System.out::println);
  }
}
