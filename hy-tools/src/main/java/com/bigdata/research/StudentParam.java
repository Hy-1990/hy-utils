package com.bigdata.research;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** @Author huyi @Date 2021/2/6 15:35 @Description: 学生参数 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class StudentParam extends Param {
  private String teacher;
  private String label;
}
