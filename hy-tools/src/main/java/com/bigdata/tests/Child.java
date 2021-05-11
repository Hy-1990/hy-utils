package com.bigdata.tests;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** @Author huyi @Date 2020/10/27 9:27 @Description: 测试子类 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Child extends Father {
  private String money;

  public Child(String money) {
    super();
    this.money = money;
  }
}
