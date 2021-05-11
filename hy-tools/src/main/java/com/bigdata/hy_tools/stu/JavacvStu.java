package com.bigdata.hy_tools.stu;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.opencv.highgui.HighGui.waitKey;

/** @Author huyi @Date 2021/3/23 10:09 @Description: javacv学习 */
public class JavacvStu {
  public static void main(String[] args) {
    // 读取原始图片
    Mat image = imread("C:\\Users\\huyi\\Desktop\\12.png");
    if (image.empty()) {
      System.err.println("加载图片出错，请检查图片路径！");
      return;
    }
    // 显示图片
    imshow("显示原始图像", image);

    // 无限等待按键按下
    waitKey(0);
  }
}
