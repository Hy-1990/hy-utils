package com.bigdata.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @Author huyi @Date 2020/10/27 9:26 @Description: 测试父类 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Father {
  private String name;
  private Integer age;
}
