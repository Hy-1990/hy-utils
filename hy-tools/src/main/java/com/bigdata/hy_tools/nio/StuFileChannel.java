package com.bigdata.hy_tools.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Program: hy-utils @ClassName: StuFileChannel @Author: huyi @Date: 2020-09-07
 * 23:41 @Description: @Version: V1.0
 */
public class StuFileChannel {
  private static FileOutputStream fosRef;
  private static FileChannel fileChannel;

  public static void main(String[] args) throws IOException, InterruptedException {
    fosRef =
        new FileOutputStream(
            new File("C:\\Users\\yi\\IdeaProjects\\hy-utils\\hy-utils\\data\\test1.txt"));
    fileChannel = fosRef.getChannel();
    IntStream.range(0, 10)
        .forEach(
            (x) -> {
              new Thread(
                      () -> {
                        try {
                          ByteBuffer buffer = ByteBuffer.wrap("abcde\r\n".getBytes());
                          fileChannel.write(buffer);
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                      })
                  .start();

              new Thread(
                      () -> {
                        try {
                          ByteBuffer byteBuffer = ByteBuffer.wrap("我是中国人".getBytes());
                          fileChannel.write(byteBuffer);
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                      })
                  .start();
            });
      TimeUnit.SECONDS.sleep(3);
      fileChannel.close();
      fosRef.close();
  }
}
