package com.bigdata.tests;

import cn.hutool.http.HttpUtil;
import com.google.common.base.Splitter;

import java.util.HashMap;
import java.util.Map;

/** @Author huyi @Date 2021/4/27 20:52 @Description: */
public class Test12 {
  public static void main(String[] args) {
    String text = "http://172.16.103.12/nfs/real2d/video_file/20210508143505395627_4713.png_373";
    System.out.println(Splitter.on(".").splitToList(text).get(Splitter.on(".").splitToList(text).size() - 1).contains("png"));
    String result = Splitter.on("nfs/").splitToList(text).get(Splitter.on("nfs/").splitToList(text).size() - 1);
    if (!"/".equals(result.substring(0,1))){
      result = "/" + result;
    }
    System.out.println(result);
  }
}
