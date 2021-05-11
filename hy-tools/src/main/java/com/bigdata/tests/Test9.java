package com.bigdata.tests;

/** @Author huyi @Date 2021/3/25 16:53 @Description: */
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

/** 音视频转单声道16位16K赫兹小端点pcm音频 */
public class Test9 {

  public static void main(String[] args) throws Exception {
    String path = "C:\\Users\\huyi\\Desktop\\1.wav"; // 待转音视频
    File file = new File("C:\\Users\\huyi\\Desktop\\out.pcm"); // 目标文件

    OutputStream os = new BufferedOutputStream(new FileOutputStream(file));

    FFmpegFrameGrabber frameGrabber = FFmpegFrameGrabber.createDefault(path);
    frameGrabber.setSampleRate(8000); // 8K赫兹采样率
    frameGrabber.setAudioChannels(1); // 单声道
    frameGrabber.start();

    Frame frame;
    Buffer buffer;
    short[] shorts;
    byte[] bytes;

    System.out.println("开始转换文件");
    while ((frame = frameGrabber.grabSamples()) != null) {
      if (frame.samples == null) {
        continue;
      }
      buffer = frame.samples[0];

      shorts = new short[buffer.limit()];
      ((ShortBuffer) buffer).get(shorts);
      bytes = shortArr2byteArr(shorts, buffer.limit());

      os.write(bytes);
    }

    os.close(); // 关闭写文件
    frameGrabber.close(); // 直接关闭拉流
    System.out.println("转换结束");
  }

  /**
   * 8位字节数组转16位字节数组，也就是16位的采样位深，转小端点字节数组
   *
   * @param shortArr
   * @param shortArrLen
   * @return
   */
  private static byte[] shortArr2byteArr(short[] shortArr, int shortArrLen) {
    byte[] byteArr = new byte[shortArrLen * 2];
    ByteBuffer.wrap(byteArr).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shortArr);
    return byteArr;
  }
}
