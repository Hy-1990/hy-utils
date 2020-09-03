package com.bigdata.hy_tools.nio;

import java.nio.ByteBuffer;

/**
 * @Program: hy-utils @ClassName: StuBuffer @Author: huyi @Date: 2020-09-03 23:18 @Description:
 * buffer学习 @Version: V1.0
 */
public class StuBuffer {
  public static void main(String[] args) {
    byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14};
    ByteBuffer byteBuffer = ByteBuffer.allocate(10);
    int getArrayIndex = 0;
    while (getArrayIndex < bytes.length) {
      int readLength = Math.min(byteBuffer.remaining(), bytes.length - getArrayIndex);
      byteBuffer.put(bytes, getArrayIndex, readLength);
      byteBuffer.flip();
      byte[] getArray = byteBuffer.array();
      for (int i = 0; i < byteBuffer.limit(); i++) {
        System.out.print(getArray[i] + " ");
      }
      System.out.println();
      byteBuffer.clear();
      getArrayIndex++;
    }
    System.out.println("----------------------------------------");
    ByteBuffer byteBuffer1 = ByteBuffer.wrap(bytes);
    byte[] byteArrayOut = new byte[5];
    while (byteBuffer1.hasRemaining()) {
      int readLength = Math.min(byteBuffer1.remaining(), byteArrayOut.length);
      byteBuffer1.get(byteArrayOut, 0, readLength);
      System.out.println(byteBuffer1.position());
      for (int i = 0; i < readLength; i++) {
        System.out.print(byteArrayOut[i] + " ");
      }
      System.out.println();
    }
    System.out.println("----------------------------------------");
    ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes);
    while (byteBuffer2.hasRemaining()){
      System.out.println(byteBuffer2.get());
      System.out.println(byteBuffer2.position());
    }
  }
}
